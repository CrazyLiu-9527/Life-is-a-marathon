在 Plumbr 从事 GC 暂停检测相关功能的工作时，我被迫用自己的方式，通过大量文章、书籍和演讲来介绍我所做的工作。在整个过程中，经常对 Minor、Major、和 Full GC 事件的使用感到困惑。这也是我写这篇博客的原因，我希望能清楚地解释这其中的一些疑惑。

文章要求读者熟悉 JVM 内置的通用垃圾回收原则。堆内存划分为 Eden、Survivor 和 Tenured/Old 空间，代假设和其他不同的 GC 算法超出了本文讨论的范围。

![img](https://pic2.zhimg.com/80/v2-b9098cc1afa9f9106e04f17925c2c525_720w.jpg)


Minor GC、Major GC和Full GC之间的区别

### Minor GC

从年轻代空间（包括 Eden 和 Survivor 区域）回收内存被称为 Minor GC。这一定义既清晰又易于理解。但是，当发生Minor GC事件的时候，有一些有趣的地方需要注意到：

1. 当 JVM 无法为一个新的对象分配空间时会触发 Minor GC，比如当 Eden 区满了。所以分配率越高，越频繁执行 Minor GC。
2. 内存池被填满的时候，其中的内容全部会被复制，指针会从0开始跟踪空闲内存。Eden 和 Survivor 区进行了标记和复制操作，取代了经典的标记、扫描、压缩、清理操作。所以 Eden 和 Survivor 区不存在内存碎片。写指针总是停留在所使用内存池的顶部。
3. 执行 Minor GC 操作时，不会影响到永久代。从永久代到年轻代的引用被当成 GC roots，从年轻代到永久代的引用在标记阶段被直接忽略掉。
4. 质疑常规的认知，所有的 Minor GC 都会触发“全世界的暂停（stop-the-world）”，停止应用程序的线程。对于大部分应用程序，停顿导致的延迟都是可以忽略不计的。其中的真相就 是，大部分 Eden 区中的对象都能被认为是垃圾，永远也不会被复制到 Survivor 区或者老年代空间。如果正好相反，Eden 区大部分新生对象不符合 GC 条件，Minor GC 执行时暂停的时间将会长很多。

所以 Minor GC 的情况就相当清楚了——每次 Minor GC 会清理年轻代的内存。

### Major GC vs Full GC

大家应该注意到，目前，这些术语无论是在 JVM 规范还是在垃圾收集研究论文中都没有正式的定义。但是我们一看就知道这些在我们已经知道的基础之上做出的定义是正确的，Minor GC 清理年轻带内存应该被设计得简单：

- Major GC 是清理永久代。
- Full GC 是清理整个堆空间—包括年轻代和永久代。

很不幸，实际上它还有点复杂且令人困惑。首先，许多 Major GC 是由 Minor GC 触发的，所以很多情况下将这两种 GC 分离是不太可能的。另一方面，许多现代垃圾收集机制会清理部分永久代空间，所以使用“cleaning”一词只是部分正确。

这使得我们不用去关心到底是叫 Major GC 还是 Full GC，大家应该关注当前的 GC 是否停止了所有应用程序的线程，还是能够并发的处理而不用停掉应用程序的线程。

这种混乱甚至内置到 JVM 标准工具。下面一个例子很好的解释了我的意思。让我们比较两个不同的工具 Concurrent Mark 和 Sweep collector (-XX:+UseConcMarkSweepGC)在 JVM 中运行时输出的跟踪记录。

第一次尝试通过 [jstat](https://links.jianshu.com/go?to=http%3A%2F%2Fdocs.oracle.com%2Fjavase%2F8%2Fdocs%2Ftechnotes%2Ftools%2Funix%2Fjstat.html) 输出：

![img](https://pic3.zhimg.com/80/v2-af751f9876a4c5e04e3fe303dba8cc05_720w.jpg)

这个片段是 JVM 启动后第17秒提取的。基于该信息，我们可以得出这样的结果，运行了12次 Minor GC、2次 Full GC，时间总跨度为50毫秒。通过 [jconsole](https://links.jianshu.com/go?to=http%3A%2F%2Fdocs.oracle.com%2Fjavase%2F7%2Fdocs%2Ftechnotes%2Fguides%2Fmanagement%2Fjconsole.html) 或者 [jvisualvm](https://links.jianshu.com/go?to=http%3A%2F%2Fdownload.oracle.com%2Fjavase%2F6%2Fdocs%2Ftechnotes%2Ftools%2Fshare%2Fjvisualvm.html) 这样的基于GUI的工具你能得到同样的结果。

![img](https://pic4.zhimg.com/80/v2-f1ecc2e6e1905ccbc66b233e4f6a97b6_720w.jpg)

在点头同意这个结论之前，让我们看看来自同一个 JVM 启动收集的垃圾收集日志的输出。显然- XX ： + PrintGCDetails 告诉我们一个不同且更详细的故事：

基于这些信息，我们可以看到12次 Minor GC 后开始有些和上面不一样了。没有运行两次 Full GC，这不同的地方在于单个 GC 在永久代中不同阶段运行了两次：

- 最初的标记阶段，用了0.0041705秒也就是4ms左右。这个阶段会暂停“全世界（ stop-the-world）”的事件，停止所有应用程序的线程，然后开始标记。
- 并行执行标记和清洗阶段。这些都是和应用程序线程并行的。
- 最后 Remark 阶段，花费了0.0462010秒约46ms。这个阶段会再次暂停所有的事件。
- 并行执行清理操作。正如其名，此阶段也是并行的，不会停止其他线程。

所以，正如我们从垃圾回收日志中所看到的那样，实际上只是执行了 Major GC 去清理老年代空间而已，而不是执行了两次 Full GC。

如果你是后期做决 定的话，那么由 jstat 提供的数据会引导你做出正确的决策。它正确列出的两个暂停所有事件的情况，导致所有线程停止了共计50ms。但是如果你试图优化吞吐量，你会被误导的。清 单只列出了回收初始标记和最终 Remark 阶段，jstat的输出看不到那些并发完成的工作。

### 结论

考虑到这种情况，最好避免以 Minor、Major、Full GC 这种方式来思考问题。而应该监控应用延迟或者吞吐量，然后将 GC 事件和结果联系起来。

随着这些 GC 事件的发生，你需要额外的关注某些信息，GC 事件是强制所有应用程序线程停止了还是并行的处理了部分事件。