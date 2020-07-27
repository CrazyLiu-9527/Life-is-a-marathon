package per.lzy.concurrencuylearning.core.threadcoreknowledge.threadobjectclasscommonmethods_05;

import java.util.concurrent.TimeUnit;

/**
 * 演示join期间被中断的效果
 *
 * @author zhiyuanliu
 * @date 2020/7/27 13:50
 */
public class JoinInterrupt {
    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();

        Thread thread = new Thread(() -> {

            try {
                mainThread.interrupt();
                TimeUnit.SECONDS.sleep(5);
                System.out.println("thread finished");
            } catch (InterruptedException e) {
                System.out.println("子线程中断");
            }
        });

        thread.start();
        System.out.println("等待子线程运行完毕");

        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "线程中断了");
            thread.interrupt();
        }

        System.out.println("子线程已经运行完毕");
    }
}
