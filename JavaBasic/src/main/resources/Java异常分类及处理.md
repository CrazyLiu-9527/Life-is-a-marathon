## 1. 概述

异常是程序中的一些错误 , 但并不是所有的错误都是异常 , 并且错误有时候是可以避免的 ;

比如说 , 你的代码少了一个分号 , 那么运行出来结果是提示是错误 `java.lang.Error`；如果你用 `System.out.println(11/0)` , 那么你是因为你用 0 做了除数 , 会抛出 `java.lang.ArithmeticException` 的异常 ;

异常发生的原因有很多 , 通常包含以下几大类 :

- 用户输入了非法数据 ;
- 要打开的文件不存在 ;
- 网络通信时连接中断 , 或者JVM内存溢出 ;

这些异常有的是因为用户错误引起 , 有的是程序错误引起的 , 还有其它一些是因为物理错误引起的 ; 要理解 Java 异常处理是如何工作的 , 你需要掌握以下三种类型的异常 :

- **检查性异常** : 最具代表的检查性异常是用户错误或问题引起的异常 , 这是程序员无法预见的 ; 例如要打开一个不存在文件时 , 一个异常就发生了 , 这些异常在编译时不能被简单地忽略 ;
- **运行时异常** : 运行时异常是可能被程序员避免的异常 ; 与检查性异常相反 , 运行时异常可以在编译时被忽略 ;
- **错误** : 错误不是异常 , 而是脱离程序员控制的问题 ; 错误在代码中通常被忽略 ; 例如 , 当栈溢出时 , 一个错误就发生了 , 它们在编译也检查不到的 ;



## 2. Exception 类的层次

所有的异常类是从 `java.lang.Exception` 类继承的子类 ;

Exception 类是 Throwable 类的子类 ; 除了 Exception 类外 , Throwable 还有一个子类 Error ;

Java 程序通常不捕获错误 ; 错误一般发生在严重故障时 , 它们在 Java 程序处理的范畴之外 ;

Error 用来指示运行时环境发生的错误 ; 例如 JVM 内存溢出 ; 一般地 , 程序不会从错误中恢复 ;

异常类有两个主要的子类 : **检查性异常类** 和 **运行时异常类 (RuntimeException)** ;

![exception_type](https://tojohnonly.github.io/images/73-Java%E5%BC%82%E5%B8%B8%E8%AF%A6%E8%A7%A3/exception_type.png)

### 检查型异常 (CheckedException)

在 Java 中所有不是 RuntimeException 派生的 Exception 都是检查型异常 ; 当函数中存在抛出检查型异常的操作时该函数的函数声明中必须包含 throws 语句 ; 调用改函数的函数也必须对该异常进行处理 , 如不进行处理则必须在调用函数上声明 throws 语句 ;
检查型异常是 Java 首创的 , 在编译期对异常的处理有强制性的要求 ; 在 JDK 代码中大量的异常属于检查型异常 , 包括 IOException , SQLException 等等 ;

### 非检查型异常 (UncheckedException)

在 Java 中所有 RuntimeException 的派生类都是非检查型异常 , 与检查型异常对比 , 非检查型异常可以不在函数声明中添加 throws 语句 , 调用函数上也不需要强制处理 ;
常见的 NullPointException , ClassCastException 是常见的非检查型异常 ; 非检查型异常可以不使用 try...catch 进行处理 , 但是如果有异常产生 , 则异常将由 JVM 进行处理 ; 对于 RuntimeException 的子类最好也使用异常处理机制 ; 虽然 RuntimeException 的异常可以不使用 try...catch 进行处理 , 但是如果一旦发生异常 , 则肯定会导致程序中断执行 , 所以为了保证程序再出错后依然可以执行 , 在开发代码时最好使用 try...catch 的异常处理机制进行处理 ;

### 问题

```java
public class ExceptionTypeTest {
    public void doSomething() throws ArithmeticException{
        System.out.println();
    }
    public static void main(){
        ExceptionTypeTest ett = new ExceptionTypeTest();
        ett.doSomething();
    }
}
```

- 问题1 : 上面的程序能否编译通过 ? 并说明理由 ;

解答 : 能编译通过 ;
分析:按照一般常理 , doSomething 方法是定义了 ArithmeticException 异常 , 在 main 方法里面调用了该方法 ; 那么应当继续抛出或者捕获一下 ; 但是 ArithmeticException 异常是继承 RuntimeException 运行时异常 ; Java 里面异常分为两大类 : CheckedException (检查异常) 和 UncheckedException (未检查异常) , 对于未检查异常也叫 RuntimeException (运行时异常) , 对于运行时异常 , Java 编译器不要求你一定要把它捕获或者一定要继续抛出 , 但是对 CheckedException (检查异常) 要求你必须要在方法里面或者捕获或者继续抛出 ;

- 问题2:上面的程序将 ArithmeticException 改为 IOException 能否编译通过 ? 并说明理由 ;

解答 : 不能编译通过 ;
分析 : IOException extends Exception 是属于 CheckedException , 必须进行处理 , 或者必须捕获或者必须抛出 ;

**对未检查的异常 (UncheckedException) 的几种处理方式 :**

- 捕获处理
- 继续抛出
- 不处理

**对检查的异常 (CheckedException) 的几种处理方式 :**

- 继续抛出 , 消极的方法 , 一直可以抛到 Java 虚拟机来处理
- 用 try...catch 捕获



## 3. 异常处理

#### 捕获异常

```java
// 文件名 : ExcepTest.java
import java.io.*;
public class ExcepTest{

    public static void main(String args[]){
        try{
            int a[] = new int[2];
            System.out.println("Access element three :" + a[3]);
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Exception thrown  :" + e);
        }
        System.out.println("Out of the block");
    }
}
```

#### 多重捕获块

> 一个 try 代码块后面跟随多个 catch 代码块的情况就叫多重捕获

```java
try
{
    file = new FileInputStream(fileName);
    x = (byte) file.read();
}catch(IOException i)
{
    i.printStackTrace();
    return -1;
}catch(FileNotFoundException f) //Not valid!
{
    f.printStackTrace();
    return -1;
}
```

#### throws/throw 关键字

> 如果一个方法没有捕获一个检查性异常 , 那么该方法必须使用 throws 关键字来声明 , throws 关键字放在方法签名的尾部 ;
> 也可以使用 throw 关键字抛出一个异常，无论它是新实例化的还是刚捕获到的。

```java
import java.io.*;
public class className{
    public void deposit(double amount) throws RemoteException, 
        InsufficientFundsException{
        // Method implementation
        throw new RemoteException();
    }
    //Remainder of class definition
}
```

#### finally 关键字

> finally 关键字用来创建在 try 代码块后面执行的代码块 ;
> 无论是否发生异常 , finally 代码块中的代码总会被执行 ;
> 在 finally 代码块中 , 可以运行清理类型等收尾善后性质的语句 ;
> finally 代码块出现在 catch 代码块最后 , 语法如下 :

```java
public class ExcepTest{
    public static void main(String args[]){
        int a[] = new int[2];
        try{
            System.out.println("Access element three :" + a[3]);
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Exception thrown  :" + e);
        }
        finally{
            a[0] = 6;
            System.out.println("First element value: " +a[0]);
            System.out.println("The finally statement is executed");
        }
    }
}
```



## 一道题目

一道非常经典的面试题，**NoClassDefFoundError 和 ClassNotFoundException 有什么区别**？

在类的加载过程中， JVM 或者 ClassLoader 无法找到对应的类时，都可能会引起这两种异常/错误，由于不同的 ClassLoader 会从不同的地方加载类，有时是错误的 CLASSPATH 类路径导致的这类错误，有时是某个库的 jar 包缺失引发这类错误。NoClassDefFoundError 表示这个类在编译时期存在，但是在运行时却找不到此类，有时静态初始化块也会导致 NoClassDefFoundError 错误。

> “
>
> ClassLoader 是类路径装载器，在Java 中，类路径装载器一共有三种两类
>
> 一种是虚拟机自带的 ClassLoader，分为三种
>
> - `启动类加载器(Bootstrap)` ，负责加载 $JAVAHOME/jre/lib/rt.jar
> - `扩展类加载器(Extension)`，负责加载 $JAVAHOME/jre/lib/ext/*.jar
> - `应用程序类加载器(AppClassLoader)`，加载当前应用的 classpath 的所有类
>
> 第二种是用户自定义类加载器
>
> - Java.lang.ClassLoader 的子类，用户可以定制类的加载方式。

![img](640-1592301063817.png)

另一方面，ClassNotFoundException 与编译时期无关，当你尝试在运行时使用反射加载类时，ClassNotFoundException 就会出现。

简而言之，ClassNotFoundException 和 NoClassDefFoundError 都是由 CLASSPATH 中缺少类引起的，通常是由于缺少 JAR 文件而引起的，但是如果 JVM 认为应用运行时找不到相应的引用，就会抛出 NoClassDefFoundError 错误；当你在代码中显示的加载类比如 `Class.forName()` 调用时却没有找到相应的类，就会抛出 `java.lang.ClassNotFoundException`。

- NoClassDefFoundError 是 JVM 引起的错误，是 unchecked，未经检查的。因此不会使用 try-catch 或者 finally 语句块；另外，ClassNotFoundException 是受检异常，因此需要 try-catch 语句块或者 try-finally 语句块包围，否则会导致编译错误。
- 调用 **Class.forName()、ClassLoader.findClass() 和 ClassLoader.loadClass()** 等方法时可能会引起 `java.lang.ClassNotFoundException`，如图所示

![img](640-1592301063799.png)

- NoClassDefFoundError 是链接错误，发生在链接阶段，当解析引用找不到对应的类，就会触发；而 ClassNotFoundException 是发生在运行时的异常。