package per.lzy.concurrencuylearning.juc.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 演示一个Future的使用方法,lambda形式
 *
 * @author zhiyuanliu
 * @date 2020/8/13 15:01
 */
public class OneFutureLambda {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        Callable<Integer> callable = new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                return null;
            }
        };

        Future<Integer> future = service.submit(callable);

        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        service.shutdown();
    }
}
