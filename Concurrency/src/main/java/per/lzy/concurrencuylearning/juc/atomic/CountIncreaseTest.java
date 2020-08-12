package per.lzy.concurrencuylearning.juc.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 不安全的自增
 *
 * @author liuzy
 * @date 2020/7/19 22:44
 */
public class CountIncreaseTest {

    /*
        由该例子可以看出，count++不是一个原子操作
     */

    // 创建一个线程池
    private ExecutorService executorService = Executors.newCachedThreadPool();
    // 测试用的count
    private int count = 0;

    public static void main(String[] args) throws InterruptedException {
        CountIncreaseTest countIncreaseTest = new CountIncreaseTest();
        countIncreaseTest.add();
        countIncreaseTest.increase();
    }

    // 单线程模式下count++
    public void add() {
        for (int i = 0; i < 100; i++) {
            count++;
        }
        System.out.println("count的值是：" + count);
    }

    // 多线程模式下count++
    public void increase() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> count++);
        }

        // 等待线程执行完毕
        executorService.shutdown();
        // 最多等待1分钟
        executorService.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("count的值是：" + count);
    }
}
