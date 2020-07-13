package per.lzy.concurrencuylearning.lockdemo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock锁分为：**公平锁** 和 **非公平锁**。
 * 公平锁表示线程获取锁的顺序是按照线程加锁的顺序来分配的，即先来先得的**FIFO先进先出顺序**。
 * 而非公平锁就是一种获取锁的抢占机制，是**随机获取**锁的，和公平锁不一样的就是先来的不一定先的到锁，这样可能造成某些线程一直拿不到锁，结果也就是不公平的了。
 * @author zhiyuanliu
 * @date 2020/7/13 19:47
 */
public class FairOrNoFairLock {

    public static class MyService {
        private ReentrantLock lock;

        public MyService(boolean fair) {
            super();
            this.lock = new ReentrantLock(fair);
        }

        public void serviceMethod() {
            lock.lock();
            try {
                System.out.println("ThreadName=" + Thread.currentThread().getName() + "获得锁定");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        // true为公平锁，false为非公平锁
        final MyService service = new MyService(true);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("★线程" + Thread.currentThread().getName() + "运行了");
                service.serviceMethod();
            }
        };

        Thread[] threadArray = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threadArray[i] = new Thread(runnable);
        }

        for (int i = 0; i < 10; i++) {
            threadArray[i].start();
        }
    }
}
