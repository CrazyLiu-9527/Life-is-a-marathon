package per.lzy.concurrencuylearning.core.jmm.volatiledemo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile适用的情况1
 *
 * @author liuzy
 * @date 2020/7/26 21:37
 */
public class UseVolatile1 implements Runnable {

    AtomicInteger realA = new AtomicInteger();
    private volatile boolean done = false;

    public static void main(String[] args) throws InterruptedException {
        Runnable r = new UseVolatile1();
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(((UseVolatile1) r).done);
        System.out.println(((UseVolatile1) r).realA.get());
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            setDone();
            realA.incrementAndGet();
        }
    }

    private void setDone() {
        done = true;
    }
}
