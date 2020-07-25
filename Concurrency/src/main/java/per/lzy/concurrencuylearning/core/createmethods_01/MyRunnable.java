package per.lzy.concurrencuylearning.core.createmethods_01;

/**
 * 实现runnable接口创建线程
 * @author zhiyuanliu
 * @date 2020/7/10 16:27
 */
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
        }
    }

    /*
    创建/实现线程有几种方式？

        1. 有两种方法，根据 Thread 类的注释（或 Java 官方文档），分别是实现 Runnable 接口和继承 Thread 类
        2. 准确的讲，本质都是一种方式，本质都是构造 Thread 类，调用Thread.run()方法，只不过一种 Runnable 实现类作为 target 传入Thread，然后调用target.run()方法，另一种是直接重写 run() 方法（参考上面本质区别）
        3. 分析优缺点，可扩展性、节约资源、解耦（参考上面两种创建方式的对比）
        4. 分析常见的 6 种典型错误观点，线程池、Callable等本质都是实现 Runnable 接口
     */

    /*
    实现 Runnable 接口和继承 Thread 类的哪种方式更好？

        1. 可扩展性，Java 不支持多继承，继承 Thread 类后就不能继承其他类，限制了可扩展性，而实现 Runnable 方式可以实现多个接口
        2. 代码架构角度，实现 Runnable 接口解耦，一是具体的业务逻辑在run()方法中，二是控制线程生命周期是 Thread 类，两个目的不一样，不建议写在一个类中，应该解耦。
        3. 节约资源，继承 Thread 类，新建任务只能去 new 一个对象，但是资源损耗比较大，继承 Thread 类每次要新建一个任务，每次只能去新建一个线程，而新建一个线程开销是比较大的，需要创建、执行和销毁；
        而 Runnable 方式可以利用线程池工具传入Runnable 实例 target，可以避免创建线程、销毁线程带来的开销。线程创建需要开辟虚拟机栈、本地方法栈、程序计数器等线程私有的内存空间，线程销毁时需要回收这些资源，频繁创建销毁线程会浪费大量系统资源
     */
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();

        Thread t1 = new Thread(myRunnable);
        Thread t2 = new Thread(myRunnable);

        t1.start();
        t2.start();
    }
}
