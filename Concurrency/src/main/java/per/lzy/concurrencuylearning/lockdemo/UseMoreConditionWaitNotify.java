package per.lzy.concurrencuylearning.lockdemo;

import java.text.SimpleDateFormat;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用多个Condition实例实现等待/通知机制
 *
 * @author zhiyuanliu
 * @date 2020/7/13 19:30
 */
public class UseMoreConditionWaitNotify {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static class MyServiceMoreCondition {
        private Lock lock = new ReentrantLock();
        public Condition conditionA = lock.newCondition();
        public Condition conditionB = lock.newCondition();

        public void awaitA() {
            lock.lock();
            try {
                System.out.println("begin awaitA时间为： " + sdf.format(System.currentTimeMillis()) + " ThreadName=" + Thread.currentThread().getName());
                conditionA.await();
                System.out.println("end awaitA时间为： " + sdf.format(System.currentTimeMillis()) + " ThreadName=" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void awaitB() {
            lock.lock();
            try {
                System.out.println("begin awaitB时间为： " + sdf.format(System.currentTimeMillis()) + " ThreadName=" + Thread.currentThread().getName());
                conditionB.await();
                System.out.println("end awaitB时间为： " + sdf.format(System.currentTimeMillis()) + " ThreadName=" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void signalAll_A() {
            lock.lock();
            try {
                System.out.println("signalAll_A时间为： " + sdf.format(System.currentTimeMillis()) + " ThreadName=" + Thread.currentThread().getName());
                conditionA.signalAll();
            } finally {
                lock.unlock();
            }
        }

        public void signalAll_B() {
            lock.lock();
            try {
                System.out.println("signalAll_B时间为： " + sdf.format(System.currentTimeMillis()) + " ThreadName=" + Thread.currentThread().getName());
                conditionB.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    public static class ThreadA extends Thread {
        private MyServiceMoreCondition service;

        public ThreadA(MyServiceMoreCondition service) {
            super();
            this.service = service;
        }

        @Override
        public void run() {
            service.awaitA();
        }
    }

    public static class ThreadB extends Thread {
        private MyServiceMoreCondition service;

        public ThreadB(MyServiceMoreCondition service) {
            super();
            this.service = service;
        }

        @Override
        public void run() {
            service.awaitB();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyServiceMoreCondition service = new MyServiceMoreCondition();

        ThreadA a = new ThreadA(service);
        a.setName("A");
        a.start();

        ThreadB b = new ThreadB(service);
        b.setName("B");
        b.start();

        Thread.sleep(3000);
        service.signalAll_A();
    }
}
