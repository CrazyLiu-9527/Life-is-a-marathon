package per.lzy.concurrencuylearning.lockdemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用lock
 * @author zhiyuanliu
 * @date 2020/7/13 15:51
 */
public class ReentrantLockTest {

    // 尝试将lock.lock()和lock.unlock()注掉观察打印结果
    public static class MyService {
        private Lock lock = new ReentrantLock();

        public void testMethod() {
            lock.lock();
            try {
                for (int i = 0; i < 5; i++) {
                    System.out.println("ThreadName=" + Thread.currentThread().getName() + (" " + (i + 1)));
                }
            } finally {
                lock.unlock();
            }
        }
    }

    // 内部类中保存MyService的引用并调用testMethod方法
    public static class MyThread extends Thread {
        private MyService service;

        public MyThread(MyService service) {
            super();
            this.service = service;
        }

        @Override
        public void run() {
            service.testMethod();
        }
    }

    public static void main(String[] args) {

        MyService service = new MyService();

        MyThread a1 = new MyThread(service);
        MyThread a2 = new MyThread(service);
        MyThread a3 = new MyThread(service);
        MyThread a4 = new MyThread(service);
        MyThread a5 = new MyThread(service);

        a1.start();
        a2.start();
        a3.start();
        a4.start();
        a5.start();
    }
}
