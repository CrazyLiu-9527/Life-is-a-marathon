package per.lzy.concurrencuylearning.juc.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhiyuanliu
 * @date 2020/8/11 14:39
 */
public class RecursionDemo {
    private static ReentrantLock lock = new ReentrantLock();

    private static void accessResource() {
        lock.lock();
        try {
            System.out.println("已经对资源进行了处理");
            if (lock.getHoldCount() < 5) {
                System.out.println(lock.getHoldCount());
                accessResource();
                System.out.println(lock.getHoldCount());
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        accessResource();
    }
}
