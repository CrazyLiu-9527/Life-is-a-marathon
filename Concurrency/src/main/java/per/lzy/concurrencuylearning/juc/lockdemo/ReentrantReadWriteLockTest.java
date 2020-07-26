package per.lzy.concurrencuylearning.juc.lockdemo;

import java.text.SimpleDateFormat;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zhiyuanliu
 * @date 2020/7/13 19:52
 */
public class ReentrantReadWriteLockTest {

    public static class Service {
        private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        public void read() {

            // 读读共享
            lock.readLock().lock();
            // 写写互斥
//        lock.readLock().unlock();

            try {
                System.out.println("获得读锁 " + Thread.currentThread().getName() + " " + sdf.format(System.currentTimeMillis()));
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.readLock().unlock();
            }
        }
    }

    public static class Service2 {
        private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        public void read() {
            try {
                try {
                    lock.readLock().lock();
                    System.out.println("获得读锁" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
                    Thread.sleep(3 * 1000);
                } finally {
                    lock.readLock().unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void write() {
            try {
                try {
                    lock.writeLock().lock();
                    System.out.println("获得写锁" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
                    Thread.sleep(3 * 1000);
                } finally {
                    lock.writeLock().unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class ThreadA extends Thread {
        private Service service;

        public ThreadA(Service service) {
            super();
            this.service = service;
        }

        @Override
        public void run() {
            service.read();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();
        ThreadA a = new ThreadA(service);
        a.setName("A");
        a.start();
        Thread.sleep(1000);
//        ThreadB b = new ThreadB(service);
//        b.setName("B");
//        b.start();
    }
}
