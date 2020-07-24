package per.lzy.concurrencuylearning.threadbase.methods;


/**
 * @author liuzy
 * @date 2020/7/12 22:18
 */
public class ThreadMethods_sleep implements Runnable {

    private int i = 0;
    private Object object = new Object();

    @Override
    public void run() {
        synchronized (object) {
            i++;
            System.out.println("i:" + i);
            try {
                System.out.println(Thread.currentThread().getName() + "进入睡眠状态");
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) {
                // TODO: handle exception
            }
            System.out.println(Thread.currentThread().getName() + "睡眠结束");
            i++;
            System.out.println("i:" + i);
        }
    }

    /**
     * 四、sleep方法，休眠当前正在执行的线程
     * 有一点要非常注意，sleep方法不会释放锁，也就是说如果当前线程持有对某个对象的锁，则即使调用sleep方法，其他线程也无法访问这个对象
     * 注意，如果调用了sleep方法，必须捕获InterruptedException异常或者将该异常向上层抛出。
     * 当线程睡眠时间满后，不一定会立即得到执行，因为此时可能CPU正在执行其他的任务。所以说调用sleep方法相当于让线程进入阻塞状态。
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        ThreadMethods_sleep threadMethodsTest = new ThreadMethods_sleep();

        // 带参构造方法为线程命名
        // 线程命名方式：主线程叫做main，其他线程是Thread-x
        Thread thread1 = new Thread(threadMethodsTest, "线程-1");
        Thread thread2 = new Thread(threadMethodsTest, "线程-2");

        thread1.start();
        thread2.start();

    }
}
