package per.lzy.concurrencuylearning.threadbase.methods;

/**
 * @author liuzy
 * @date 2020/7/12 22:50
 */
public class ThreadMethods_join implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " -- " + i);
        }
    }

    /**
     * 调用join方法，会等待该线程执行完毕后才执行别的线程~
     * <p>
     * 在很多情况下，主线程创建并启动了线程，如果子线程中要进行大量耗时运算，主线程往往将早于子线程结束之前结束。
     * 这时，如果主线程想等待子线程执行完成之后再结束，比如子线程处理一个数据，主线程要取得这个数据中的值，就要用到join()方法了。
     * 方法join()的作用是等待线程对象销毁。
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        ThreadMethods_join runnable = new ThreadMethods_join();

        Thread t1 = new Thread(runnable, "线程1");

        for (int i = 0; i < 20; i++) {
            // 主线程运行到i=5的时候，会一直等待线程1运行完毕，再接着往下运行
            if (i == 5) {
                t1.start();
                t1.join();
            }

            System.out.println(Thread.currentThread().getName() + " -- " + i);
        }
    }
}
