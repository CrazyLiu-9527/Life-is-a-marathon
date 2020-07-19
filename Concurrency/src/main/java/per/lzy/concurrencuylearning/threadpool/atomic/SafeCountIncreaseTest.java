package per.lzy.concurrencuylearning.threadpool.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liuzy
 * @date 2020/7/19 23:03
 */
public class SafeCountIncreaseTest {

    /*
        使用atomic原子类可以在多线程下安全的操作对象
        atomic底层实现是通过cas(compare and swap 比较并交换)，cas是一个原子操作，所以不会出现一个线程读的时候另一个线程写的操作（解开了我心中的一个谜团）
        但是cas会出现ABA问题，这种问题可以通过加版本号来解决
     */

    // 创建一个线程池
    private ExecutorService executorService = Executors.newCachedThreadPool();
    // 测试用的count
    private AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        SafeCountIncreaseTest safeCountIncreaseTest = new SafeCountIncreaseTest();
        safeCountIncreaseTest.add();
        safeCountIncreaseTest.increase();
    }

    // 单线程模式下count++
    public void add() {
        for (int i = 0; i < 100; i++) {
            count.incrementAndGet();
        }
        System.out.println("count的值是：" + count);
    }

    // 多线程模式下count++
    public void increase() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> count.incrementAndGet());
        }

        // 等待线程执行完毕
        executorService.shutdown();
        // 最多等待1分钟
        executorService.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("count的值是：" + count);
    }
}
