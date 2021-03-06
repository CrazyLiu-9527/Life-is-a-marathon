package per.lzy.concurrencuylearning.juc.flowcontroll.cyclebarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 演示CyclicBarrier
 * CyclicBarrier针对线程，CountDownLatch针对事件
 *
 * @author zhiyuanliu
 * @date 2020/8/12 18:32
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("所有人都到场了，大家一起出发");
            }
        });

        for (int i = 0; i < 10; i++) {
            new Thread(new Task(i, cyclicBarrier)).start();
        }

    }

    static class Task implements Runnable {
        private int id;
        private CyclicBarrier cyclicBarrier;

        public Task(int id, CyclicBarrier cyclicBarrier) {
            this.id = id;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程" + id + "现在前往集合地点");
            try {
                Thread.sleep((long) (Math.random() * 1000));
                System.out.println("线程" + id + "到了集合地点，开始等待其他人到达");
                cyclicBarrier.await();
                System.out.println("线程" + id + "出发了");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

        }
    }
}
