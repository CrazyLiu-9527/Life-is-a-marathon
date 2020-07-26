package per.lzy.concurrencuylearning.core.threadcoreknowledge.createmethods_01.wrongways;

import java.util.concurrent.*;

/**
 * callable实现线程
 * 本质上是实现runnable
 *
 * @author liuzy
 * @date 2020/7/25 17:00
 */
public class CallableDemo implements Callable<Integer> {

    // 创建线程池对象
    private static ExecutorService pool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future<Integer> thread = pool.submit(new CallableDemo());
        System.out.println(thread.get());
    }

    @Override
    public Integer call() {
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += i;
        }
        return sum;
    }

}
