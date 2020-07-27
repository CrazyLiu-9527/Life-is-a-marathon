package per.lzy.concurrencuylearning.core.threadcoreknowledge.threadobjectclasscommonmethods_05;

/**
 * @author liuzy
 * @date 2020/7/12 22:16
 */
public class ThreadMethods_setProperties implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "----start");
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
        }

        System.out.println(Thread.currentThread().getName() + "----end");
    }

    /**
     * 三、设置线程优先级，运行查看效果的时候需要注释掉上面的 thread2.setDaemon(true);
     * 线程优先级高仅表示线程获取CPU时间片的几率高，但并不表示优先级高的线程一定比优先级低的线程先执行
     * 线程的优先级高度依赖操作系统，虽然Java默认的priority是1-10，但是操作系统提供的优先级并不一定有这么多的等级，所以Java中的优先级需要映射到不同的操作系统中去，这可能导致java中设置的优先级相近的两个或多个线程在操作系统中运行的时候优先级没有区别
     *
     * @param args
     */
    public static void main(String[] args) {
        ThreadMethods_setProperties threadMethodsTest = new ThreadMethods_setProperties();

        // 带参构造方法为线程命名
        // 线程命名方式：主线程叫做main，其他线程是Thread-x
        Thread thread1 = new Thread(threadMethodsTest, "线程名称-1");
        Thread thread2 = new Thread(threadMethodsTest, "线程名称-2");

        thread1.setPriority(10);
        thread2.setPriority(1);

        thread1.start();
        thread2.start();
    }
}
