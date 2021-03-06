摘自知乎 作者：Intopass





首先，不要纠结于 Pass By Value 和 Pass By Reference 的字面上的意义，否则很容易陷入所谓的“一切传引用其实本质上是传值”这种并不能解决问题无意义论战中。
更何况，要想知道Java到底是传值还是传引用，起码你要先知道传值和传引用的准确含义吧？可是如果你已经知道了这两个名字的准确含义，那么你自己就能判断Java到底是传值还是传引用。
这就好像用大学的名词来解释高中的题目，对于初学者根本没有任何意义。

一：搞清楚 基本类型 和 引用类型的不同之处

```text
int num = 10;
String str = "hello";
```

 ![img](pic/166032bc90958c21604110441ad03f45_720w-1592388914419.jpg) 

如图所示，num是基本类型，值就直接保存在变量中。而str是引用类型，变量中保存的只是实际对象的地址。一般称这种变量为"引用"，引用指向实际对象，实际对象中保存着内容。

二：搞清楚赋值运算符（=）的作用

```text
num = 20;
str = "java";
```

 ![img](pic/287c0efbb179638cf4cf27cbfdf3e746_720w-1592389026362.jpg) 

对于基本类型 num ，赋值运算符会直接改变变量的值，原来的值被覆盖掉。
对于引用类型 str，赋值运算符会改变引用中所保存的地址，原来的地址被覆盖掉。**但是原来的对象不会被改变（重要）。**
如上图所示，"hello" 字符串对象没有被改变。（没有被任何引用所指向的对象是垃圾，会被垃圾回收器回收）

三：调用方法时发生了什么？**参数传递基本上就是赋值操作**。

```java
第一个例子：基本类型
void foo(int value) {
    value = 100;
}
foo(num); // num 没有被改变

第二个例子：没有提供改变自身方法的引用类型
void foo(String text) {
    text = "windows";
}
foo(str); // str 也没有被改变

第三个例子：提供了改变自身方法的引用类型
StringBuilder sb = new StringBuilder("iphone");
void foo(StringBuilder builder) {
    builder.append("4");
}
foo(sb); // sb 被改变了，变成了"iphone4"。

第四个例子：提供了改变自身方法的引用类型，但是不使用，而是使用赋值运算符。
StringBuilder sb = new StringBuilder("iphone");
void foo(StringBuilder builder) {
    builder = new StringBuilder("ipad");
}
foo(sb); // sb 没有被改变，还是 "iphone"。
```



重点理解为什么，第三个例子和第四个例子结果不同？

下面是第三个例子的图解：

 ![img](pic/d8b82e07ea21375ca6b300f9162aa95f_720w-1592389041855.jpg) 

builder.append("4")之后

 ![img](pic/ff2ede9c6c55568d42425561f25a0fd7_720w-1592389049566.jpg) 

下面是第四个例子的图解：

 ![img](pic/d8b82e07ea21375ca6b300f9162aa95f_720w-1592389055385.jpg) 

builder = new StringBuilder("ipad"); 之后

 ![img](pic/46fa5f10cc135a3ca087dae35a5211bd_720w-1592389064189.jpg) 

------

2018年1月31日添加部分内容：

这个答案点赞的不少，虽然当时回答时并没有讲的特别详细，今天就稍微多讲一些各种类型数据在内存中的存储方式。

**从局部变量/方法参数开始讲起：**

局部变量和方法参数在jvm中的储存方法是相同的，都是在栈上开辟空间来储存的，随着进入方法开辟，退出方法回收。以32位JVM为例，boolean/byte/short/char/int/float以及引用都是分配4字节空间，long/double分配8字节空间。对于每个方法来说，最多占用多少空间是一定的，这在编译时就可以计算好。

我们都知道JVM内存模型中有，stack和heap的存在，但是更准确的说，是每个线程都分配一个独享的stack，所有线程共享一个heap。对于每个方法的局部变量来说，是绝对无法被其他方法，甚至其他线程的同一方法所访问到的，更遑论修改。

当我们在方法中声明一个 int i = 0，或者 Object obj = null 时，仅仅涉及stack，不影响到heap，当我们 new Object() 时，会在heap中开辟一段内存并初始化Object对象。当我们将这个对象赋予obj变量时，仅仅是stack中代表obj的那4个字节变更为这个对象的地址。

**数组类型引用和对象：**

当我们声明一个数组时，如int[] arr = new int[10]，因为数组也是对象，arr实际上是引用，stack上仅仅占用4字节空间，new int[10]会在heap中开辟一个数组对象，然后arr指向它。

当我们声明一个二维数组时，如 int[][] arr2 = new int[2][4]，arr2同样仅在stack中占用4个字节，会在内存中开辟一个长度为2的，类型为int[]的数组，然后arr2指向这个数组。这个数组内部有两个引用（大小为4字节），分别指向两个长度为4的类型为int的数组。

 ![img](pic/v2-6590cb935ae8bf3b7241cb309fe041d7_720w-1592389074260.jpg) 

所以当我们传递一个数组引用给一个方法时，数组的元素是可以被改变的，但是无法让数组引用指向新的数组。

你还可以这样声明：int[][] arr3 = new int[3][]，这时内存情况如下图

 ![img](pic/v2-fdc86227021d56a02b559d6485983c71_720w-1592389080505.jpg) 

你还可以这样 arr3[0] = new int [5]; arr3[1] = arr2[0];

 ![img](pic/v2-fdc5e737a95d625a47d66ab61e4a2f55_720w-1592389086273.jpg) 

**关于String：**

原本回答中关于String的图解是简化过的，实际上String对象内部仅需要维护三个变量，char[] chars, int startIndex, int length。而chars在某些情况下是可以共用的。但是因为String被设计成为了不可变类型，所以你思考时把String对象简化考虑也是可以的。

```
String str = new String("hello")
```

 ![img](pic/v2-a143d0a3594d06f54c6853c46c429e08_720w-1592389095628.jpg) 

当然某些JVM实现会把"hello"字面量生成的String对象放到常量池中，而常量池中的对象可以实际分配在heap中，有些实现也许会分配在方法区，当然这对我们理解影响不大。