package per.lzy.concurrencuylearning.juc.threadpool.semaphore;

import java.util.concurrent.Semaphore;

/**
 * Semaphore(信号量)实际上就是可以`控制同时访问的线程个数`，它维护了一组"许可证"。
 * <p>
 * 当调用acquire()方法时，会消费一个许可证。如果没有许可证了，会阻塞起来
 * 当调用release()方法时，会添加一个许可证。
 * 这些"许可证"的个数其实就是一个count变量罢了~
 *
 * @author liuzy
 * @date 2020/7/19 22:37
 */
public class SemaphoreDemo {

    // 比如一家酸奶店店最多只能容纳10个顾客进店选购，超过了就要在外面排队等待
    public static void main(String[] args) {

        // 假设有50个人同时来到店门口
        final int nums = 50;

        // 酸奶店只能容纳10个人同时挑选酸奶
        Semaphore semaphore = new Semaphore(10);

        for (int i = 0; i < nums; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    // 前10个有号的才能进店购买
                    semaphore.acquire();
                    System.out.println("顾客" + finalI + "在挑选商品...");

                    // 假设挑选了2秒
                    Thread.sleep(2 * 1000);

                    //归还一个许可之后，后面的顾客就可以进店购买了
                    System.out.println("顾客" + finalI + "购买完毕了...");
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
