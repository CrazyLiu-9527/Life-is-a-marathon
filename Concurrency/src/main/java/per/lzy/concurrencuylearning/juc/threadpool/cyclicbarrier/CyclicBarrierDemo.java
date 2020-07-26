package per.lzy.concurrencuylearning.juc.threadpool.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier允许一组线程互相等待，直到到达某个公共屏障点。
 * 叫做cyclic是因为当所有等待线程都被释放以后，CyclicBarrier可以被重用(对比于CountDownLatch是不能重用的)
 * 使用说明：
 * CountDownLatch注重的是等待其他线程完成，CyclicBarrier注重的是：
 * 当线程到达某个状态后，暂停下来等待其他线程，所有线程均到达以后，继续执行。
 *
 * @author liuzy
 * @date 2020/7/19 22:20
 */
public class CyclicBarrierDemo {


    // 你和女朋友约好出去玩，两人分别从家里出发，说好了到达公园之后每人发一条消息给对方，然后汇合了再继续
    public static void meet() {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                String name = Thread.currentThread().getName();
                name = name.equals("Thread-0") ? "你" : "女朋友";
                System.out.println(name + "到达了公园");

                try {
                    // 发送消息
                    cyclicBarrier.await();
                    // 两人汇合，一起出去玩
                    System.out.println("成功汇合，跟" + name + "一起出去玩了");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    // 玩了一天累了，分别回到各自家中，越好了到家之后再聊天
    public static void chat() {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                String name = Thread.currentThread().getName();
                name = name.equals("Thread-0") ? "你" : "女朋友";

                System.out.println(name + "回到家里了");
                try {
                    // 两个人都回到家里了，给对方发消息
                    cyclicBarrier.await();
                    System.out.println("两个人一起聊天");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }


    public static void main(String[] args) {
//        meet();
        chat();
    }
}
