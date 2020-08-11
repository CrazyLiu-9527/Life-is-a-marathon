package per.lzy.concurrencuylearning.juc.lock.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 演示非公平和公平的ReentrantReadWriteLock的策略
 *
 * @author zhiyuanliu
 * @date 2020/8/11 14:09
 */
public class NonfairBargeDemo {
    public static ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
    public static ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    public static ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    private static void read() {
        System.out.println(Thread.currentThread().getName() + "开始尝试获取读锁");
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到读锁，正在读取");
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放读锁");
            readLock.unlock();
        }
    }

    private static void write() {
        System.out.println(Thread.currentThread().getName() + "开始尝试获取写锁");
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到写锁，正在写入");
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放写锁");
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> write(), "Thread1").start();
        new Thread(() -> read(), "Thread2").start();
        new Thread(() -> read(), "Thread3").start();
        new Thread(() -> write(), "Thread4").start();
        new Thread(() -> read(), "Thread5").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Thread thread[] = new Thread[1000];
                for (int i = 0; i < 1000; i++) {
                    thread[i] = new Thread(() -> read(), "子线程创建的Thread" + i);
                }
                for (int i = 0; i < 1000; i++) {
                    thread[i].start();
                }
            }
        }).start();
    }
}
