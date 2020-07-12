package per.lzy.concurrencuylearning.thread.methods;

/**
 * @author liuzy
 * @date 2020/7/12 22:12
 */
public class ThreadMethods_setName implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "----start");
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
        }

        System.out.println(Thread.currentThread().getName() + "----end");
    }

    /**
     * 一、线程命名方式：主线程叫做main，其他线程是Thread-x，可以通过构造函数和调用setName方法进行修改
     *
     * @param args
     */
    public static void main(String[] args) {
        ThreadMethods_setName threadMethodsTest = new ThreadMethods_setName();

        // 带参构造方法为线程命名
        // 线程命名方式：主线程叫做main，其他线程是Thread-x
        Thread thread1 = new Thread(threadMethodsTest, "线程-1");
        Thread thread2 = new Thread(threadMethodsTest, "线程-2");

        // 调用setName进行修改
        thread1.setName("线程名称-修改之后的线程1");
        thread2.setName("线程名称-修改之后的线程2");

        thread1.start();
        thread2.start();

        // main线程
        System.out.println(Thread.currentThread().getName());
    }
}
