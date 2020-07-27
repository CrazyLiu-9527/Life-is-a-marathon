package per.lzy.concurrencuylearning.core.threadcoreknowledge.threadobjectclasscommonmethods_05;

/**
 * @author liuzy
 * @date 2020/7/12 22:14
 */
public class ThreadMethods_setDaemon implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "----start");
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
        }

        System.out.println(Thread.currentThread().getName() + "----end");
    }

    /**
     * 二、设置守护线程
     * 守护线程是为其它线程服务的，垃圾回收线程就是守护线程
     * 特点：当别的用户线程执行完了，虚拟机就会推出，守护线程也就会停止，就是说：守护线程作为一个服务线程，没有服务对象就没有必要继续运行了
     * 使用线程时需要注意的点：
     * 1.需要在线程启动前设置为守护线程，启动后设置守护线程会抛出异常（具体查看源码）
     * 2.使用守护线程不要访问共享资源（数据库、文件等），因为它不一定什么时候就会挂掉
     * 3.守护线程中产生的新的线程也是守护线程
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        ThreadMethods_setDaemon threadMethodsTest = new ThreadMethods_setDaemon();

        // 带参构造方法为线程命名
        // 线程命名方式：主线程叫做main，其他线程是Thread-x
        Thread thread1 = new Thread(threadMethodsTest, "线程-1");
        Thread thread2 = new Thread(threadMethodsTest, "线程-2");

        thread2.setDaemon(true);

        thread1.start();

        thread2.start();
    }
}
