package per.lzy.concurrencuylearning.threadpool.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * SingleThreadPoolDemo
 *
 * @author liuzy
 * @date 2020/7/19 21:15
 */
public class SingleThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        for (int i = 1; i <= 5; i++) {
            final int index = i;
            singleThreadPool.execute(() -> {
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
