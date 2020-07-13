package per.lzy.concurrencuylearning.lockdemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhiyuanliu
 * @date 2020/7/13 15:11
 */
public class LockTest {

    // 最好不要把获取锁的过程写在try语句块中，因为如果在获取锁时发生了异常，异常抛出的同时也会导致锁无法被释放。
    public void doSomething() {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            // doSomething
        } finally {
            lock.unlock();
        }
    }
}
