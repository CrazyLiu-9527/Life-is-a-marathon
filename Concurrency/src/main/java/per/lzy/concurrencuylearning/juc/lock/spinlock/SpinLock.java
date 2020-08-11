package per.lzy.concurrencuylearning.juc.lock.spinlock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 *
 * @author zhiyuanliu
 * @date 2020/8/11 14:27
 */
public class SpinLock {
    private AtomicReference<Thread> sign = new AtomicReference<>();

    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + "开始尝试获取自旋锁");
            spinLock.lock();
            System.out.println(Thread.currentThread().getName() + "获取到了自旋锁");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                spinLock.unlock();
                System.out.println(Thread.currentThread().getName() + "释放了自旋锁");
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
    }

    public void lock() {
        Thread curr = Thread.currentThread();
        while (!sign.compareAndSet(null, curr)) {
            System.out.println("自旋锁获取失败，再次尝试");
        }
    }

    public void unlock() {
        Thread current = Thread.currentThread();
        sign.compareAndSet(current, null);
    }
}
