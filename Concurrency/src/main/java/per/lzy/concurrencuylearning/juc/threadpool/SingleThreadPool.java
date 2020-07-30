package per.lzy.concurrencuylearning.juc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 演示SingleThreadPool
 *
 * @author liuzy
 * @date 2020/7/31 00:16
 */
public class SingleThreadPool {

    // SingleThreadPool底层和FixedThreadPool是一样的，只不过核心线程数和最大线程数被固定为1
    public static void main(String[] args) {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 1000; i++) {
            singleThreadExecutor.submit(new Task());
        }
    }
}
