package per.lzy.concurrencuylearning.core.threadcoreknowledge.threadobjectclasscommonmethods_05;

/**
 * 三个线程
 * 线程1和线程2首先被阻塞，线程3唤醒他们。
 * notify，notifyAll。
 * start先执行并不代表线程先启动
 *
 * @author zhiyuanliu
 * @date 2020/7/27 10:43
 */
public class WaitNotifyAll implements Runnable {
    private static Object resourceA = new Object();

    public static void main(String[] args) throws InterruptedException {
        WaitNotifyAll runnable = new WaitNotifyAll();
        Thread threadA = new Thread(runnable, "Thread-A");
        Thread threadB = new Thread(runnable, "Thread-B");

        Thread threadC = new Thread(() -> {
            synchronized (resourceA) {
//               resourceA.notify();
                resourceA.notifyAll();
                System.out.println(Thread.currentThread().getName() + " notified");
            }
        }, "Thread-C");

        threadA.start();
        threadB.start();
        // 如果这里不进行sleep，那么三个线程的执行顺序是得不到保证的，
        // 可能会出现threadC先执行notify，然后threadA和threadB再进行wait，导致notify失败的情况
        // 也可能会出现threadA或threadB先执行，然后threadC执行，然后再执行threadB或threadA的情况，只能唤醒一个线程
        // 当然也可能正常运行
        Thread.sleep(500);
        threadC.start();
    }

    @Override
    public void run() {
        // 执行顺序
        // 1.threadA 先进来执行到wait，然后释放锁
        // 2.threadB 获取到锁执行到wait然后释放锁
        // 3.threadC 开始执行然后获取notifyAll唤醒两个线程，然后两个线程依次执行剩余代码
        synchronized (resourceA) {
            System.out.println(Thread.currentThread().getName() + " got resourceA lock");
            try {
                System.out.println(Thread.currentThread().getName() + " wait to start");
                resourceA.wait();
                System.out.println(Thread.currentThread().getName() + " wait to end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
