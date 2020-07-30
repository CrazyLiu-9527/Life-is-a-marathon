package per.lzy.concurrencuylearning.juc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 演示关闭线程池
 *
 * @author liuzy
 * @date 2020/7/31 00:23
 */
public class ShutDown {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new ShutDownTask());
        }
        Thread.sleep(1500);
        // shutdownNow立即停止当前线程，并返回一个runnableList
//        List<Runnable> runnableList = executorService.shutdownNow();

        // shutDown， 发出停止信号，但是线程池会接着运行完毕，不再接收后续线程
        executorService.shutdown();
        executorService.execute(new ShutDownTask());
        // 延迟停止，返回停止结果，如果7秒后线程执行完毕，就返回true
//        boolean b = executorService.awaitTermination(7L, TimeUnit.SECONDS);
//        System.out.println(b);
        // isShutdown获取线程是否接收到停止信号，这里是false
//        System.out.println(executorService.isShutdown());
//        executorService.shutdown();
        // isShutdown获取线程是否接收到停止信号，这里是true,但是线程还在执行
//        System.out.println(executorService.isShutdown());
        // isTerminated是返回线程是不是彻底全部执行完毕了
//        System.out.println(executorService.isTerminated());
//        Thread.sleep(10000);
//        System.out.println(executorService.isTerminated());

//        executorService.execute(new ShutDownTask());
    }
}

class ShutDownTask implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "被中断了");
        }
    }
}
