package per.lzy.concurrencuylearning.core.threadcoreknowledge.threadobjectclasscommonmethods_05;

/**
 * 展示Wait和notify的基本用法
 * 1、研究代码执行顺序
 * 2、证明wait释放锁
 *
 * @author zhiyuanliu
 * @date 2020/7/27 10:18
 */
public class Wait {
    private static Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();

        // sleep是为了保证thread1线程先执行，这样后面thread2才能唤醒wait的线程1
        thread1.start();
        Thread.sleep(500);
        thread2.start();
    }

    static class Thread1 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + " 开始执行了");
                try {
                    // wait释放锁之后Thread2获取锁，所以后面的“获得了锁”在thread2执行notify之后被唤醒
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 获得了锁");
            }
        }
    }

    static class Thread2 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                // notify虽然唤醒了线程1，但是线程2依然持有锁，所以会等到线程2执行之后才进入到线程1的wait之后接着执行
                object.notify();
                System.out.println(Thread.currentThread().getName() + " 调用了notify");
            }
        }
    }
}
