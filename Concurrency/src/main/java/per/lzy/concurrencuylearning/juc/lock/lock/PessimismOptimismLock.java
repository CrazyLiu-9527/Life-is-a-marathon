package per.lzy.concurrencuylearning.juc.lock.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 演示乐观锁悲观锁
 *
 * @author zhiyuanliu
 * @date 2020/8/1 17:45
 */
public class PessimismOptimismLock {
    int a;

    public static void main(String[] args) {
        // atomic原子类底层实现是cas，cas是一种典型的乐观锁
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.incrementAndGet();
    }

    // synchronized的就是一种典型的悲观互斥锁
    public synchronized void add() {
        a++;
    }
}
