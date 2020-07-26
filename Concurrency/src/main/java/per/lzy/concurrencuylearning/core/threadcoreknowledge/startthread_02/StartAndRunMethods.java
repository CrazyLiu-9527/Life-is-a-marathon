package per.lzy.concurrencuylearning.core.threadcoreknowledge.startthread_02;

/**
 * 对比start和run两种启动线程的方式
 *
 * @author liuzy
 * @date 2020/7/25 17:08
 */
public class StartAndRunMethods {

    // 可以看出 run方法只是一个普通的方法，start方法才能真正启动线程
    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName());
        };

        runnable.run();

        new Thread(runnable).start();
    }
}
