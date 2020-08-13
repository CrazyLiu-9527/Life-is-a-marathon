package per.lzy.concurrencuylearning.juc.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 演示get方法过程中抛出异常，for循环为了演示抛出Exception的时机：
 * 并不是说一产生异常就抛出，直到我们get执行时，才会抛出。
 *
 * @author zhiyuanliu
 * @date 2020/8/13 15:08
 */
public class GetException {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        Future<Integer> future = service.submit(new Task());

        try {
            /*for (int i = 0; i < 5; i++) {
                System.out.println(i);
                Thread.sleep(500);
            }*/
            Thread.sleep(3000);

            System.out.println(future.isDone());
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

    static class Task implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            throw new IllegalArgumentException("Callable抛出异常");
        }
    }
}
