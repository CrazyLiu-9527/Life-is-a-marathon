package per.lzy.concurrencuylearning.juc.threadpool.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * 简单来说：CountDownLatch是一个同步的辅助类，允许一个或多个线程一直等待，直到其它线程完成它们的操作。
 *
 * @author liuzy
 * @date 2020/7/19 22:03
 */
public class CountDownLatchDemo {

    // 例子，某员工需要等其它所有员工下班之后才能下班
    public static void goHome() {
        final CountDownLatch countDownLatch = new CountDownLatch(5);

        System.out.println("晚上八点了，可以下班了...");

        new Thread(() -> {
            try {
                // 调用await()表示等待其他线程完成
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("其余五个人都下班了，我可以走了");
        }).start();

        for (int i = 0; i < 5; i++) {
            final int index = i;
            new Thread(() -> {
                System.out.println("员工" + index + "下班了");
                // 一个线程完成数量减一
                countDownLatch.countDown();
            }).start();
        }
    }

    // 例子，所有员工都需要等老板下班之后才能下班
    public static void goHomeBehindBoss() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        System.out.println("晚上八点了，可以下班了...");

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
            System.out.println("老板下班了，大家可以走了");
        }).start();

        for (int i = 0; i < 5; i++) {
            final int index = i;
            new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("员工" + index + "下班了");
            }).start();
        }
    }

    public static void main(String[] args) {
//        goHome();
        goHomeBehindBoss();
    }
}
