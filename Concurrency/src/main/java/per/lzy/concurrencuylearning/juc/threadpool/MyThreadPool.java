package per.lzy.concurrencuylearning.juc.threadpool;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.*;

/**
 * 线程池实例
 *
 * @author zhiyuanliu
 * @date 2020/7/14 20:02
 */
public class MyThreadPool {

    /**
     * 队列容量，工作队列最多容纳 DEFAULT_SIZE 个数量的任务
     */
    public static final int DEFAULT_SIZE = 500;
    /**
     * 空闲线程存活时间
     */
    public static final int DEFAULT_KEEP_ALIVE = 60;
    /**
     * 任务队列
     */
    public static final BlockingQueue<Runnable> executeQueue = new ArrayBlockingQueue<Runnable>(DEFAULT_SIZE);
    /**
     * 核心线程数 最多有DEFAULT_MAX_CONCURRENT个线程同时运行，其余的将进入等待队列
     * Runtime.getRuntime().availableProcessors()是Java虚拟机可用的处理器数量
     */
    private static final int DEFAULT_MAX_CONCURRENT = Runtime.getRuntime().availableProcessors() * 2;
    /**
     * 线程池名称
     */
    private static final String THREAD_POOL_NAME = "MyThreadPool-%d";
    /**
     * 线程工厂，使用apache包提供的方法
     */
    private static final ThreadFactory FACTORY = new BasicThreadFactory.Builder().namingPattern(THREAD_POOL_NAME).daemon(true).build();
    private static ExecutorService executorService;

    static {
        try {
            executorService = new ThreadPoolExecutor(DEFAULT_MAX_CONCURRENT, DEFAULT_MAX_CONCURRENT + 2, DEFAULT_KEEP_ALIVE, TimeUnit.SECONDS, executeQueue, FACTORY);

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("MyThread pool shutting down");
                    executorService.shutdown();

                    try {
                        if (!executorService.awaitTermination(1, TimeUnit.SECONDS)) {
                            System.err.println("MyThreadPool shutdown immediately due to wait timeout.");
                            executorService.shutdown();
                        }
                    } catch (InterruptedException e) {
                        System.err.println("MyThreadPool shutdown interrupted.");
                        executorService.shutdown();
                        e.printStackTrace();
                    }

                    System.err.println("MyThreadPool shutdown complete.");
                }
            });

            Runtime.getRuntime().addShutdownHook(thread);
        } catch (Exception e) {
            System.err.println("MyThreadPool init error." + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * 构造方法私有，不能直接new
     */
    private MyThreadPool() {
    }

    /**
     * 提供execute方法
     *
     * @param task
     * @return
     */
    public static boolean execute(Runnable task) {
        try {
            executorService.execute(task);
        } catch (RejectedExecutionException e) {
            System.err.println("Task executing was rejected." + e);
            return false;
        }
        return true;
    }

    /**
     * 提供submit方法
     *
     * @param task
     * @param <T>
     * @return
     */
    public static <T> Future<T> submitTask(Callable<T> task) {
        try {
            return executorService.submit(task);
        } catch (RejectedExecutionException e) {
            System.err.println("Task executing was rejected." + e);
            throw new UnsupportedOperationException("Unable to submit the task, rejected.", e);
        }
    }
}
