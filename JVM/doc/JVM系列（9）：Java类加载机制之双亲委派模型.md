## 前言

双亲委派模型是Java加载类的机制.采用双亲委派模型的好处是Java类随着它的类加载器一起具备了一种带有优先级的层级关系，通过这种层级关系可以避免类的重复加载.

### 1. 模型基础 

![img](https://pic4.zhimg.com/80/v2-278989890af96aa77c3ffe5d7042678f_720w.jpg)

- **Bootstrap ClassLoader(启动类加载器):**负责将%JAVA_HOME%/lib目录中或-Xbootclasspath中参数指定的路径中的，并且是虚拟机识别的（按名称）类库加载到JVM中
- **Extension ClassLoader（扩展类加载器):** 负责加载%JAVA_HOME%/lib/ext中的所有类库
- **Application ClassLoader（应用程序加载器):**负责ClassPath中的类库

### 2. 为什么使用双亲委派模型?

1.双亲委派模型最大的好处就是让Java类同其类加载器一起具备了一种带优先级的层次关系。这句话可能不好理解，我们举个例子。比如我们要加载java.lang.Object类，无论我们用哪个类加载器去加载Object类，这个加载请求最终都会委托给Bootstrap ClassLoader，这样就保证了所有加载器加载的Object类都是同一个类。如果没有双亲委派模型，那就乱了套了，完全可能搞出多个不同的Object类。
2.自上而下每个类加载器都会尽力加载.

### 3. 看看源码

1.首先加载类调用的loadClass方法,我们找到ClassLoader的loadClass():

```Java
protected Class<?> loadClass(String name, boolean resolve)
        throws ClassNotFoundException
    {
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                long t0 = System.nanoTime();
                try {
                    if (parent != null) {
                        c = parent.loadClass(name, false);
                    } else {
                        c = findBootstrapClassOrNull(name);
                    }
                } catch (ClassNotFoundException e) {
                    // ClassNotFoundException thrown if class not found
                    // from the non-null parent class loader
                }

                if (c == null) {
                    // If still not found, then invoke findClass in order
                    // to find the class.
                    long t1 = System.nanoTime();
                    c = findClass(name);

                    // this is the defining class loader; record the stats
                    sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                    sun.misc.PerfCounter.getFindClasses().increment();
                }
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }
```

- 首先判断了该类是否已加载.
- 若没加载,则传给双亲加载器去加载,
- 若双亲加载器没能成功加载它,则自己用findClass()去加载.所以是个向上递归的过程.
- 自定义加载器时,需要重写findClass方法,因为是空的,没有任何内容:

```java
protected Class<?> findClass(String name) throws ClassNotFoundException {
        throw new ClassNotFoundException(name);
    }
```

### 4. 自己动手,编写一个自己的类加载器

1.首先需要一个编译好的class文件,笔者用了一个之前写的斐波那契的类Fib.class(所在路径:C:/Users/Think/crabapple),下面是用idea通过反编译方式打开的class文件,注意记下class文件的包名,在后续代码中需要使用类的全限定名称.

```java
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
package crabapple;

public class Fib {

    public static int fib(int num) {
        return num < 2 ? num : fib(num - 2) + fib(num - 1);
    }
}
```

2.继承ClassLoader,重写findClass方法:

```java
class MyClassLoader extends ClassLoader {
    private String classPath;  // 保存的地址

    /**
     * 传入地址构造函数
     * @param classPath
     */
    public MyClassLoader(String classPath) {
        this.classPath = classPath;
    }

    /**
     * 读取class文件
     * @param name
     * @return
     * @throws Exception
     */
    private byte[] loadByte(String name) throws Exception {
        String inPath = classPath + "/" + name + ".class";
        FileInputStream fis = new FileInputStream(inPath);
        int len = fis.available();
        byte[] data = new byte[len];
        fis.read(data);
        fis.close();
        return data;
    }

    /**
     * 重写findClass方法，让加载的时候调用findClass方法
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] data = loadByte(name);
            // 将字节码载入内存
            return defineClass(name, data, 0, data.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
```

- loadByte方法仅用作读取文件
- findClass方法才是加载类到内存的,注意name必须填全限定名,比如java.lang.Object.

3.测试,一下将使用一些反射机制和class类的方法.

```java
public class ClassLoaderTest extends ClassLoader {

    //main函数本该抛出异常有 ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException,为了好看,简写成Exception
    public static void main(String[] args) throws Exception {
        //初始化类加载器
        MyClassLoader myClassLoader=new MyClassLoader("C:/Users/Think/crabapple");
        //加载Fib类,笔者class文件包名为crabapple
        Class myClass=myClassLoader.loadClass("crabapple.Fib");
        //获取加载类的实例
        Object object=myClass.newInstance();
        //获取该类一个名为fib,且参数为int的方法
        Method method=myClass.getMethod("fib",int.class);
        //执行这个方法
        int result=method.invoke(object,4);
        //打印结果
        System.out.print(result);

        //output
        /**
         * 3
         * Process finished with exit code 0
         */
    }
}
```

- 执行成功
- 我们来分析下,Fib类的加载过程,初始化自定义类加载器后,loadClass方法肯定将其委派到双亲Application ClassLoader,而Application ClassLoader又将其委派到Extension ClassLoader,继而委派到Bootstrap ClassLoader.但是Bootstrap ClassLoader发现Fib并不在自己的加载能力范围内,于是移向Extension ClassLoader,同理Extension ClassLoader只能加载/ext中的class,继而让给Application ClassLoader,而Application ClassLoader只加载classpath中的类,于是又回到我们自定义的MyClassLoader,幸好我们重写了findClass方法进而执行了加载,否在findClass抛出找不到类的异常.至此Fib类加载完成.

## 结语

如上便是笔者对双亲委派模型的总结,如有错误,欢迎指正.