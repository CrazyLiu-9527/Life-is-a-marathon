package per.lzy.concurrencuylearning.core.threadcoreknowledge.sixstate_04;

/**
 * 展示BLOCKED、WAITING、TIMED_WAITING状态
 *
 * @author liuzy
 * @date 2020/7/25 22:11
 */
public class BlockedWaitingTimedWaiting implements Runnable {

    public static void main(String[] args) {
        Runnable runnable = new BlockedWaitingTimedWaiting();
        Thread thread1 = new Thread(runnable);
        thread1.start();
        Thread thread2 = new Thread(runnable);
        thread2.start();

        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 打印出TIMED_WAITING状态，因为正在执行Thread.sleep(1000);
        System.out.println(thread1.getState());
        // 打印BLOCKED状态，因为thread2想拿的到sync()却拿不到
        System.out.println(thread2.getState());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 打印WAITING状态，因为执行了wait()
        System.out.println(thread1.getState());

    }

    @Override
    public void run() {
        sync();
    }

    private synchronized void sync() {
        try {
            Thread.sleep(1000);
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
