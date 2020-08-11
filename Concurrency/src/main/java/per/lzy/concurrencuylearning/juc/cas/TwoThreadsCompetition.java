package per.lzy.concurrencuylearning.juc.cas;

/**
 * 模拟CAS操作，等价代码
 * 模拟两个线程竞争
 *
 * @author zhiyuanliu
 * @date 2020/8/11 11:36
 */
public class TwoThreadsCompetition implements Runnable {
    private volatile int value;

    public static void main(String[] args) throws InterruptedException {
        TwoThreadsCompetition r = new TwoThreadsCompetition();
        r.value = 0;
        Thread t1 = new Thread(r, "Thread 1");
        Thread t2 = new Thread(r, "Thread 2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(r.value);
    }

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            value = newValue;
        }
        return oldValue;
    }

    @Override
    public void run() {
        compareAndSwap(0, 1);
    }
}
