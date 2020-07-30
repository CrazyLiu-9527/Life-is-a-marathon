package per.lzy.concurrencuylearning.juc.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 演示ScheduledThreadPool
 *
 * @author liuzy
 * @date 2020/7/31 00:18
 */
public class ScheduledThreadPool {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        // 5秒后执行一次
        scheduledExecutorService.schedule(new Task(), 5, TimeUnit.SECONDS);
        // 1秒之后每3秒执行一次
//        scheduledExecutorService.scheduleAtFixedRate(new Task(), 1, 3, TimeUnit.SECONDS);
    }
}
