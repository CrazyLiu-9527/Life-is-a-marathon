package per.lzy.concurrencuylearning.lockdemo;

import java.text.SimpleDateFormat;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Condition实现等待/通知机制
 *
 * @author zhiyuanliu
 * @date 2020/7/13 19:18
 */
public class UseSingleConditionWaitNotify {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static class MyService {
        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();

        public void await() {
            lock.lock();
            try {
                System.out.println("await时间为： " + sdf.format(System.currentTimeMillis()));
                condition.await();
                System.out.println("这是condition.await()方法之后的语句，condition.signal()方法之后我才被执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void signal() {
            lock.lock();
            try {
                System.out.println("signal时间为: " + sdf.format(System.currentTimeMillis()));
                condition.signal();
                Thread.sleep(3 * 1000);
                System.out.println("这是condition.signal()方法之后的语句");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static class MyThread extends Thread {
        private MyService service;

        public MyThread(MyService myService) {
            super();
            this.service = myService;
        }

        @Override
        public void run() {
            service.await();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyService myService = new MyService();
        MyThread myThread = new MyThread(myService);
        myThread.start();

        Thread.sleep(3 * 1000);
        myService.signal();
    }
}
