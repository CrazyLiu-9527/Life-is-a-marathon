package per.lzy.concurrencuylearning.thread;

/**
 * 自定义线程名称
 *
 * @author zhiyuanliu
 * @date 2020/7/10 16:34
 */
public class ThreadMethodsTest implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "----start");
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
        }
        System.out.println(Thread.currentThread().getName() + "----end");
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadMethodsTest threadMethodsTest = new ThreadMethodsTest();

        // 带参构造方法为线程命名
        // 线程命名方式：主线程叫做main，其他线程是Thread-x
        Thread thread1 = new Thread(threadMethodsTest, "线程名称-1");
        Thread thread2 = new Thread(threadMethodsTest, "线程名称-2");

        // 一、调用setName进行修改
        thread1.setName("线程名称-修改之后的线程1");
        thread2.setName("线程名称-修改之后的线程2");

        // 二、设置守护线程
        // 守护线程是为其它线程服务的，垃圾回收线程就是守护线程
        // 特点：当别的用户线程执行完了，虚拟机就会推出，守护线程也就会停止，就是说：守护线程作为一个服务线程，没有服务对象就没有必要继续运行了
        // 使用线程时需要注意的点：
        // 1.需要在线程启动前设置为守护线程，启动后设置守护线程会抛出异常（具体查看源码）
        // 2.使用守护线程不要访问共享资源（数据库、文件等），因为它不一定什么时候就会挂掉
        // 3.守护线程中产生的新的线程也是守护线程
        thread2.setDaemon(true);

        // 三、设置线程优先级，运行查看效果的时候需要注释掉上面的 thread2.setDaemon(true);
        // 线程优先级高仅表示线程获取CPU时间片的几率高，但并不表示优先级高的线程一定比优先级低的线程先执行
        // 线程的优先级高度依赖操作系统，虽然Java默认的priority是1-10，但是操作系统提供的优先级并不一定有这么多的等级，所以Java中的优先级需要映射到不同的操作系统中去，这可能导致java中设置的优先级相近的两个或多个线程在操作系统中运行的时候优先级没有区别
        thread1.setPriority(10);
        thread2.setPriority(1);

        // 四、sleep方法，休眠当前正在执行的线程
        Thread.sleep(3 * 1000);
        Thread.sleep(3 * 1000);

        thread1.start();
        thread2.start();

        // main线程
        System.out.println(Thread.currentThread().getName());
    }
}
