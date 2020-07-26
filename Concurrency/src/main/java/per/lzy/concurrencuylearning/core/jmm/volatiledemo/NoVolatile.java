package per.lzy.concurrencuylearning.core.jmm.volatiledemo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 不适用于volatile的场景
 *
 * @author liuzy
 * @date 2020/7/26 21:44
 */
public class NoVolatile implements Runnable {
    volatile int a;
    AtomicInteger realA = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        Runnable r = new NoVolatile();
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(((NoVolatile) r).a);
        System.out.println(((NoVolatile) r).realA.get());
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            a++;
            realA.incrementAndGet();
        }
    }
}
