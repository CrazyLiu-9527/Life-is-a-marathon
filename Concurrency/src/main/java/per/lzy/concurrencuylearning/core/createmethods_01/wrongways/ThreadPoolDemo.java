package per.lzy.concurrencuylearning.core.createmethods_01.wrongways;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用线程池创建线程
 * 本质上是执行runnable方法
 *
 * @author liuzy
 * @date 2020/7/25 16:48
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            service.execute(new Task());
        }
    }
}


class Task implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName());
    }
}