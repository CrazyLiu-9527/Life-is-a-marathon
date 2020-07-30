package per.lzy.concurrencuylearning.juc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 演示newFixedThreadPool出错的情况
 * OOM原因是因为FixedThreadPool的队列最大是Integer.MAX_VALUE
 * 可以调整内存使得本例更加明显
 *
 * @author liuzy
 * @date 2020/7/31 00:08
 */
public class FixedThreadPoolOOM {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            executorService.submit(new SubTask());
        }
    }
}


class SubTask implements Runnable {

    @Override
    public void run() {
        try {
            // 这里是为了让一个线程一直占用线程池资源，以便其它的线程进入队列等待
            Thread.sleep(1000000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}