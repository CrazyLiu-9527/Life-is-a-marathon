## 引言

之前在一个公众号一个妹子分享了自己的面试经历，关于 Hystrix 的问题先后在美团、滴滴、京东都有提到，汇总后两点：

1. Hystrix主要功能覆盖考察。
2. Hystrix工作中使用经验考察。

Hystrix语义为“豪猪”，它后背带刺儿且具有自我保护的能力，这是不是就很好理解它的功能了。虽然我没有直接使用过Hystrix，但是类似的同样功能的框架功能和原理都大同小异，所以我决定针对 Hystrix 单独拆分开讲解。

同时我觉得Hystrix中有很多**设计思想非常优秀**，非常**值得我们学习**，学习这些设计思想，你可以从更高维度去思考如何让系统更加稳定。



### 1.面试官：能简单介绍下Hystrix有哪些功能吗？

**问题分析：**

了解Hystrix的功能，同时也能从Hystrix优秀的设计理念中得到架构设计方面的启发。

**我：**

我在项目里使用到，系统在 Hystrix 的保护下，可以长期处于高可用的状态，常用的功能有以下几点：

1.1 fail-fast（快速失败）

Hystrix设计中提供了 fail-fast（快速失败）和快速恢复机制。

**Tip：**

不知道之前你是否了解过fail-fast机制，或者面试Java基础的时候，HashMap 中的 Iterator 迭代器，Iterator的设计就是 fail-fast 的，**快速失败（fail—fast）**是Java集合中的一种机制， 在用迭代器遍历一个集合对象时，如果遍历过程中对集合对象的内容进行了修改（增加、删除、修改），则会抛出Concurrent Modification Exception。

我第一次学习 HashMap 并不是很懂 fail-fast，觉得快速失败只是应用在Java集合类中，防止Java非线程安全集合的并发操作，学习使用 Hystrix 后，原来快速失败机制还可以应用在系统架构设计中，对无法及时处理的请求快速失败（fail-fast），降低系统负载，而不是排队。

1.2 Fallback优雅降级机制

Fallback 字面意思是遇到Fall就启动back，了解到Fallback的机制后，我马上在项目中用起来。

看真实例子：

```
 @Override



    @Degrade(key = "getOrderByParamFromES", fallBackMethod = "getOrderByParamFromMysql")



    public OrderResult getOrderByParamFromES(OrderSearchParam param) {



        //走ES查询



        ......



        return OrderResult;



    }



 



		//fallBack后调用getOrderByParamFromMysql方法



 		public OrderResult getOrderByParamFromMysql(OrderSearchParam param) {



        //走mysql查询



        ......



        return OrderResult;



    }



 



 
```

代码解释：

fallBackMethod = "getOrderByParamFromMysql"就是在ES查询故障失败后，系统自动降级调getOrderByParamFromMysql方法，走mysql查询，正常情况下，getOrderByParamFromMysql是不会被调用的，除非Fall。

1.3 线程/信号量隔离机制

**线程隔离**：

请求会根据自己的key获取对应线程池中的线程执行，动态设置线程池参数，这样自然地将不同的请求隔离开来，支持异步来提高接口性能。不同请求直接不影响，例如service1请求缓慢，但是service2和service3还是可以正常工作，缺点就是线程切换影响性能。

**信号量隔离**：

一个请求中访问了service1、service2、service3，其中service1请求超时，将导致整个信号量一直不释放，其他请求一直无法接受。

对于延迟小的请求（例如访问缓存或者本地访问数据库）来说，线程池带来的开销是非常高的，你可以考虑采用其他方法，例如非阻塞信号量（不支持超时）来实现依赖服务的隔离。但绝大多数情况下，Netflix 更偏向于使用线程池来隔离依赖服务，因为其带来的额外开销可以接受，并且能支持包括超时在内的所有功能。



### 2.面试官：刚刚说到线程隔离，那实际使用中是否打开超时线程中断开关？

**问题分析：**

考察实际使用经验，根据线程本身的特点，线程超时，如果不及时中断，会浪费线程资源。

**我：**

一般情况下我们会打开超时中断开关，目的是及时释放线程资源。

通过hystrix.command.default.execution.isolation.thread.interruptOnTimeout = true 设置。

但是如果是写数据库命令，或者记录关键日志命令的情况下，需要命令执行完毕情况，可关闭超时中断。

**（面试官点头满意，相信我确实有Hystrix的维护经验）**



### 3.面试官：那你是如何估计线程池大小的？

**我：**

要正确设置线程池的大小，需要分析所部署系统的CPU个数、内存大小、任务类型（计算密集、IO密集等），对于计算密集型任务，线程池大小和CPU个数相近通常能实现最优利用率，对于IO密集型任务，线程池的最优大小的计算公式：线程池大小=CPU个数* (1 + 任务等待时间/ 任务处理时间)。



## 深入分析



### Hystrix历史

![图片描述](aHR0cHM6Ly9pbWcubXVrZXdhbmcuY29tLzVlMzI2MWUzMDAwMWNkZDIxMzg0MDUxMC5wbmc)
Hystrix源自Netflix API团队于2011年开始的项目。2012年，Hystrix不断发展和成熟，Netflix内部的许多团队都采用了它。如今，每天在Netflix上通过Hystrix执行数百亿个线程隔离和数千亿个信号量隔离的调用。这极大地提高了正常运行时间和弹性。

在高并发访问下，系统所依赖的服务的稳定性对系统的影响非常大，依赖有很多不可控的因素，比如网络连接变慢，资源突然繁忙，暂时不可用，服务脱机等。我们要构建稳定、可靠的分布式系统，就必须要有这样一套容错方法。



### Hystrix的主要功能特性：

1. 熔断器机制：熔断器可以理解成保险丝，项目里使用**Hystrix Command**，当 Hystrix Command请求后，如果服务失败数量超过一定比例(比如默认50%)，断路器自动熔断，该服务将进入熔断状态，后续请求都会进入fallback。
2. 降级机制：通过**fallbackMethod**注解，当请求后端服务出现异常的时候, 为了避免影响到其他业务逻辑，可以使用fallback方法指定的方法快速返回，或启用“备胎方案”。
3. 环境隔离：包括线程隔离和信号量隔离。
4. cache：Hystrix支持将一个请求结果缓存起来，下一个具有相同key的请求将直接从缓存中取出结果，减少请求开销。



### Hystrix Demo

通过一个demo快速理解Hystrix fallback 的使用

```
@Service



public class OrderQueryService {



     /**



     * 订单查询接口



     */



    @HystrixCommand(fallbackMethod = "queryOrderBack")



    public List<Order> queryOrderFromRedis(String userId) {



      



      // todo  reids查询逻辑



      



      return orderlist;



    }



    



     /**



     * 订单查询接口失败降级方案



     */



    @SuppressWarnings("unused")



    private String queryOrderBack(String userId) {



      // todo  如，走ES查询逻辑  或者 直接提示用户“请稍后再试”



      // todo 通知维护人员处理故障



      return "";



    }



    



}
```

代码解释:

程序正常时，查询订单服务是走queryOrderFromRedis方法的逻辑，当queryOrderFromRedis方法抛出异常，根据设定的异常比例，或者指定哪个异常，达到阈值触法fallback开关，程序切换到queryOrderBack，设置程序走ES查询逻辑 或者 直接提示用户“请稍后再试”，根据业务自行设置。



### 哪些情况下会触发fallback？

| Failure Type         | Exception class           | Exception.cause                        | 触发fallback |
| :------------------- | :------------------------ | :------------------------------------- | :----------- |
| FAILURE              | `HystrixRuntimeException` | underlying exception (user-controlled) | YES          |
| SEMAPHORE_REJECTED   | `HystrixRuntimeException` | `j.l.RuntimeException`                 | YES          |
| SHORT_CIRCUITED      | `HystrixRuntimeException` | `j.l.RuntimeException`                 | YES          |
| THREAD_POOL_REJECTED | `HystrixRuntimeException` | `j.u.c.RejectedExecutionException`     | YES          |
| TIMEOUT              | `HystrixRuntimeException` | `j.u.c.TimeoutException`               | YES          |

- FAILURE：任意RuntimeException异常都可以激活fallback。
- THREAD_POOL_REJECTED：并发执行的任务数超过线程池和队列之和时，也就是Hystrix的线程隔离机制。
- SEMAPHORE_REJECTED：类似 THREAD_POOL_REJECTED ，当服务的并发数大于信号量阈值时将进入fallback。比如配置程序执行并发数不能大于3，由于信号量隔离下无论调用哪种命令执行方法，Hystrix都不会创建新线程执行`run()/construct()`，所以调用程序需要自己创建多个线程来模拟并发调用`execute()`，最后看到一旦并发线程>3，后续请求都进入fallback。
- SHORT_CIRCUITED：在一定时间内，用户请求超过一定的比例失败时，如超时，异常，线程并发达到限定最大值等，断路器都会打开；短路器打开后所有请求直接走fallback，可以通过。circuitBreakerErrorThresholdPercentage方法设置百分比，默认是50。
- TIMEOUT：即超时请求。



### 附录：Hystrix策略配置

```
/* --------------统计相关------------------*/ 



// 统计滚动的时间窗口,默认:5000毫秒（取自circuitBreakerSleepWindowInMilliseconds）   



private final HystrixProperty metricsRollingStatisticalWindowInMilliseconds;   



// 统计窗口的Buckets的数量,默认:10个,每秒一个Buckets统计   



private final HystrixProperty metricsRollingStatisticalWindowBuckets; // number of buckets in the statisticalWindow   



// 是否开启监控统计功能,默认:true   



private final HystrixProperty metricsRollingPercentileEnabled;   



/* --------------熔断器相关------------------*/ 



// 熔断器在整个统计时间内是否开启的阀值，默认20。也就是在metricsRollingStatisticalWindowInMilliseconds（默认10s）内至少请求20次，熔断器才发挥起作用   



private final HystrixProperty circuitBreakerRequestVolumeThreshold;   



// 熔断时间窗口，默认:5秒.熔断器中断请求5秒后会进入半打开状态,放下一个请求进来重试，如果该请求成功就关闭熔断器，否则继续等待一个熔断时间窗口



private final HystrixProperty circuitBreakerSleepWindowInMilliseconds;   



//是否启用熔断器,默认true. 启动   



private final HystrixProperty circuitBreakerEnabled;   



//默认:50%。当出错率超过50%后熔断器启动



private final HystrixProperty circuitBreakerErrorThresholdPercentage;  



//是否强制开启熔断器阻断所有请求,默认:false,不开启。置为true时，所有请求都将被拒绝，直接到fallback 



private final HystrixProperty circuitBreakerForceOpen;   



//是否允许熔断器忽略错误,默认false, 不开启   



private final HystrixProperty circuitBreakerForceClosed; 



/* --------------信号量相关------------------*/ 



//使用信号量隔离时，命令调用最大的并发数,默认:10   



private final HystrixProperty executionIsolationSemaphoreMaxConcurrentRequests;   



//使用信号量隔离时，命令fallback(降级)调用最大的并发数,默认:10   



private final HystrixProperty fallbackIsolationSemaphoreMaxConcurrentRequests; 



/* --------------其他------------------*/ 



//使用命令调用隔离方式,默认:采用线程隔离,ExecutionIsolationStrategy.THREAD   



private final HystrixProperty executionIsolationStrategy;   



//使用线程隔离时，调用超时时间，默认:1秒   



private final HystrixProperty executionIsolationThreadTimeoutInMilliseconds;   



//线程池的key,用于决定命令在哪个线程池执行   



private final HystrixProperty executionIsolationThreadPoolKeyOverride;   



//是否开启fallback降级策略 默认:true   



private final HystrixProperty fallbackEnabled;   



// 使用线程隔离时，是否对命令执行超时的线程调用中断（Thread.interrupt()）操作.默认:true   



private final HystrixProperty executionIsolationThreadInterruptOnTimeout; 



// 是否开启请求日志,默认:true   



private final HystrixProperty requestLogEnabled;   



//是否开启请求缓存,默认:true   



private final HystrixProperty requestCacheEnabled; // Whether request caching is enabled



//请求合并是允许的最大请求数,默认: Integer.MAX_VALUE   



private final HystrixProperty maxRequestsInBatch;   



//批处理过程中每个命令延迟的时间,默认:10毫秒   



private final HystrixProperty timerDelayInMilliseconds;   



//批处理过程中是否开启请求缓存,默认:开启   



private final HystrixProperty requestCacheEnabled; 



/* 配置线程池大小,默认值10个 */ 



private final HystrixProperty corePoolSize; 



/* 配置线程值等待队列长度,默认值:-1 建议值:-1表示不等待直接拒绝,测试表明线程池使用直接决绝策略+ 合适大小的非回缩线程池效率最高.所以不建议修改此值。 当使用非回缩线程池时，queueSizeRejectionThreshold,keepAliveTimeMinutes 参数无效 */



private final HystrixProperty maxQueueSize; 
```



### 其他常用限流降级组件

1. Sentinel：阿里巴巴集团内部基础技术模块，覆盖了所有的核心场景。Sentinel 也因此积累了大量的流量归整场景以及生产实践。2018 年，Sentinel 开源，并持续演进。
2. Resilience4j：Resilience4j 也是一个轻量级的容错组件，其灵感来自于 Hystrix，但主要为 Java 8 和函数式编程所设计。轻量级体现在其只用 Vavr库（前身是 Javaslang），没有任何外部依赖。而 Hystrix 依赖了 Archaius ，Archaius 本身又依赖很多第三方包，例如 Guava、Apache Commons Configuration 等。



### Sentinel 与 Hystrix resilience4j 对比

|                | Sentinel                                                     | Hystrix                 | resilience4j                     |
| :------------- | :----------------------------------------------------------- | :---------------------- | :------------------------------- |
| 隔离策略       | 信号量隔离（并发线程数限流）                                 | 线程池隔离/信号量隔离   | 信号量隔离                       |
| 熔断降级策略   | 基于响应时间、异常比率、异常数等                             | 异常比率模式、超时熔断  | 基于异常比率、响应时间           |
| 实时统计实现   | 滑动窗口（LeapArray）                                        | 滑动窗口（基于 RxJava） | Ring Bit Buffer                  |
| 动态规则配置   | 支持[多种配置源](https://github.com/alibaba/Sentinel/wiki/动态规则扩展#datasource-扩展) | 支持多种数据源          | 有限支持                         |
| 扩展性         | 丰富的 SPI 扩展接口                                          | 插件的形式              | 接口的形式                       |
| 基于注解的支持 | 支持                                                         | 支持                    | 支持                             |
| 限流           | 基于 QPS，支持基于调用关系的限流                             | 有限的支持              | Rate Limiter                     |
| 集群流量控制   | 支持                                                         | 不支持                  | 不支持                           |
| 流量整形       | 支持预热模式、匀速排队模式等多种复杂场景                     | 不支持                  | 简单的 Rate Limiter 模式         |
| 系统自适应保护 | 支持                                                         | 不支持                  | 不支持                           |
| 控制台         | 提供开箱即用的控制台，可配置规则、查看秒级监控、机器发现等   | 简单的监控查看          | 不提供控制台，可对接其它监控系统 |
| 多语言支持     | Java / C++                                                   | Java                    | Java                             |
| 开源社区状态   | 活跃                                                         | 停止维护                | 较活跃                           |

至于如何选择，个人觉得，只要满足需求掌握使用理念，选技术文档最多最全的一种即可，你最熟悉的就是最适合你的。



## 总结

Hystrix 框架提供了高可用相关的各种各样的功能，有了 Hystrix 的保护，整个系统可以长期处于高可用的状态。

这一小节的内容不仅仅是学会 Hystrix 这门工具的使用，更重要的是理解降级的设计理念，即便 Hystrix 官方已经停止维护更新，但不可否定 Hystrix 是一个优秀的生产力工具。

阅读全文 