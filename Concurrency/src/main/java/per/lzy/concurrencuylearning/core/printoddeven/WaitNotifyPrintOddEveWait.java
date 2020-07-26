package per.lzy.concurrencuylearning.core.printoddeven;

/**
 * 两个线程交替打印0~100的奇偶数，用wait和notify
 *
 * @author liuzy
 * @date 2020/7/26 00:42
 */
public class WaitNotifyPrintOddEveWait {
    /**
     * 充当共享变量的object，锁的对象
     */
    private static final Object lock = new Object();
    /**
     * count
     */
    private static int count = 0;

    public static void main(String[] args) {

        new Thread(new TurningRunner(), "偶数").start();
        new Thread(new TurningRunner(), "奇数").start();

    }

    //1. 拿到锁，我们就打印
    //2. 打印完，唤醒其他线程，自己就休眠
    static class TurningRunner implements Runnable {

        @Override
        public void run() {
            while (count < 100) {
                synchronized (lock) {
                    // 拿到锁就打印
                    System.out.println(Thread.currentThread().getName() + ": " + count++);
                    lock.notify();
                    if (count < 100) {
                        try {
                            // 如果任务还没结束，就让出当前的锁，并休眠
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

}

