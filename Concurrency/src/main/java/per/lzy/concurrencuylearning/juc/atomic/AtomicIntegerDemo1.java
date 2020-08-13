package per.lzy.concurrencuylearning.juc.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 演示AtomicInteger的基本用法，对比非原子类的线程安全问题，使用了原子类之后，不需要加锁，也可以保证线程安全。
 *
 * @author zhiyuanliu
 * @date 2020/8/13 14:19
 */
public class AtomicIntegerDemo1 implements Runnable {

    public static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger();
    private static volatile int basicCount = 0;

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerDemo1 r = new AtomicIntegerDemo1();
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("原子类的结果：" + ATOMIC_INTEGER);
        System.out.println("普通变量的结果：" + basicCount);
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            incrementAtomic();
            incrementBasic();
        }
    }

    private void incrementBasic() {
        ATOMIC_INTEGER.getAndAdd(-90);
    }

    private void incrementAtomic() {
        basicCount++;
    }
}
