package per.lzy.concurrencuylearning.practice.producersandconsumers.waitandnotify;

/**
 * wait notify机制的问题
 *
 * @author zhiyuanliu
 * @date 2020/7/24 15:27
 */
public class EarlyNotify {
    private static String lockObject = "";
    private static volatile boolean wait = true;

    // 这种情况如果先启动notify线程，然后再启动wait线程，wait线程就会一直阻塞，
    // 因此需要添加一个标志位，让 waitThread 调用 wait 方法前先判断状态是否已经改变了没，如果通知早已发出的话，WaitThread 就不再去 wait
    public static void main(String[] args) {
        WaitThread waitThread = new WaitThread(lockObject);
        waitThread.setName("wait线程");
        NotifyThread notifyThread = new NotifyThread(lockObject);
        notifyThread.setName("notify线程");
        notifyThread.start();
        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        waitThread.start();
    }

    // 等待线程
    static class WaitThread extends Thread {
        private String lock;

        public WaitThread(String lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock) {

                try {
                    // 这里判断是否已经在wait
                    while (wait) {
                        System.out.println(Thread.currentThread().getName() + " 进入代码块");
                        System.out.println(Thread.currentThread().getName() + " 开始wait");
                        lock.wait();
                        System.out.println(Thread.currentThread().getName() + " 结束wait");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 唤醒线程
    static class NotifyThread extends Thread {
        private String lock;

        public NotifyThread(String lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " 进入代码块");
                System.out.println(Thread.currentThread().getName() + " 开始notify");
                lock.notify();
                wait = false;
                System.out.println(Thread.currentThread().getName() + " 结束notify");
            }
        }
    }
}
