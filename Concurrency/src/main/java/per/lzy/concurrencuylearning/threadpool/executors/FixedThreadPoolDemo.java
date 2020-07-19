package per.lzy.concurrencuylearning.threadpool.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * FixedThreadPool Demo,
 *
 * @author liuzy
 * @date 2020/7/19 21:07
 */
public class FixedThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 1; i <= 5; i++) {
            final int index = i;
            fixedThreadPool.execute(() -> {
                try {
                    System.out.println("第" + index + "个线程" + Thread.currentThread().getName());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
