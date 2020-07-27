package per.lzy.concurrencuylearning.core.threadcoreknowledge.threadobjectclasscommonmethods_05;

/**
 * 展示线程sleep的时候不释放synchronized的monitor，等sleep时间到了以后，正常结束后才释放锁
 *
 * @author zhiyuanliu
 * @date 2020/7/27 11:39
 */
public class SleepDontReleaseMonitor implements Runnable {
    private static Object object = new Object();

    public static void main(String[] args) {
        SleepDontReleaseMonitor sleepDontReleaseMonitor = new SleepDontReleaseMonitor();
        new Thread(sleepDontReleaseMonitor).start();
        new Thread(sleepDontReleaseMonitor).start();
    }

    @Override
    public void run() {
        syn();
    }

    private void syn() {
        // 两个线程都走到这里
        System.out.println("线程" + Thread.currentThread().getName() + "进入syn方法");
        // 然后其中一个线程获取到锁
        synchronized (object) {
            System.out.println("线程" + Thread.currentThread().getName() + "获取到了monitor。");
            try {
                // 线程睡眠5秒钟，此过程中是不释放锁的
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程" + Thread.currentThread().getName() + "退出了同步代码块");
        }

    }
}
