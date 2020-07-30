package per.lzy.concurrencuylearning.juc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 演示CacheThreadPool
 *
 * @author liuzy
 * @date 2020/7/31 00:11
 */
public class CachedThreadPool {

    // 运行后会发现每个线程的名字都不一样
    // 是因为CachedThreadPool用的队列是SynchronousQueue，这个队列不保存数据，仅做转换用
    // CachedThreadPool的核心线程数为0，最大线程数是Integer.MAX_VALUE，因此进到队列中的数据马上会新建线程
    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            cachedThreadPool.submit(new Task());
        }
    }
}
