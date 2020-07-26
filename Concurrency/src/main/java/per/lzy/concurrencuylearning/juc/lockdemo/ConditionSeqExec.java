package per.lzy.concurrencuylearning.juc.lockdemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用condition实现顺序执行
 * 通过代码很好理解，说简单就是在一个线程运行完之后通过condition.signal()/condition.signalAll()方法通知下一个特定的运行运行，就这样循环往复即可。
 *
 * @author zhiyuanliu
 * @date 2020/7/13 19:36
 */
public class ConditionSeqExec {

    private static volatile int nextPrintWho = 1;
    private static ReentrantLock lock = new ReentrantLock();
    private final static Condition CONDITION_A = lock.newCondition();
    private final static Condition CONDITION_B = lock.newCondition();
    private final static Condition CONDITION_C = lock.newCondition();

    public static void main(String[] args) {
        Thread threadA = new Thread() {
            @Override
            public void run() {
                lock.lock();
                try {
                    while (nextPrintWho != 1) {
                        CONDITION_A.await();
                    }
                    for (int i = 0; i < 3; i++) {
                        System.out.println("ThreadA " + i);
                    }
                    // 通过conditionB实例的线程运行
                    nextPrintWho = 2;
                    CONDITION_B.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };

        Thread threadB = new Thread() {
            @Override
            public void run() {
                lock.lock();
                try {
                    while (nextPrintWho != 2) {
                        CONDITION_A.await();
                    }
                    for (int i = 0; i < 3; i++) {
                        System.out.println("ThreadB " + i);
                    }
                    // 通过conditionC实例的线程运行
                    nextPrintWho = 3;
                    CONDITION_B.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };

        Thread threadC = new Thread() {
            @Override
            public void run() {
                lock.lock();
                try {
                    while (nextPrintWho != 3) {
                        CONDITION_C.await();
                    }
                    for (int i = 0; i < 3; i++) {
                        System.out.println("ThreadC " + i);
                    }
                    nextPrintWho = 1;
                    //通知conditionA实例的线程运行
                    CONDITION_A.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };

        Thread[] aArray = new Thread[5];
        Thread[] bArray = new Thread[5];
        Thread[] cArray = new Thread[5];
        for (int i = 0; i < 5; i++) {
            aArray[i] = new Thread(threadA);
            bArray[i] = new Thread(threadB);
            cArray[i] = new Thread(threadC);
            aArray[i].start();
            bArray[i].start();
            cArray[i].start();
        }
    }
}
