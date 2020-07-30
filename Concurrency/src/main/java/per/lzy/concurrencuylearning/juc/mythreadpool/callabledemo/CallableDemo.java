package per.lzy.concurrencuylearning.juc.mythreadpool.callabledemo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * CallableDemo
 *
 * @author zhiyuanliu
 * @date 2020/7/14 19:44
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建线程池对象
        ExecutorService pool = Executors.newFixedThreadPool(2);

        // Future一般我们认为是Callable的返回值，但他其实代表的是任务的生命周期(当然了，它是能获取得到Callable的返回值的)
        Future<Integer> f1 = pool.submit(new MyCallable(100));
        Future<Integer> f2 = pool.submit(new MyCallable(200));

        // V get
        Integer res1 = f1.get();
        Integer res2 = f2.get();

        System.out.println(res1);
        System.out.println(res2);

        // 结束
        pool.shutdown();
    }
}
