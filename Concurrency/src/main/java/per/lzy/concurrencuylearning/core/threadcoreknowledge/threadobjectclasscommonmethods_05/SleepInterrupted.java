package per.lzy.concurrencuylearning.core.threadcoreknowledge.threadobjectclasscommonmethods_05;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 更优雅的sleep方式，使用TimeUnit
 * Thread.sleep()
 * TimeUnit.SECONDS.sleep()
 *
 * @author zhiyuanliu
 * @date 2020/7/27 11:40
 */
public class SleepInterrupted implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new SleepInterrupted());
        thread.start();
        Thread.sleep(6500);
        thread.interrupt();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(new Date());
            try {
                TimeUnit.HOURS.sleep(3);
                TimeUnit.MILLISECONDS.sleep(25);
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("我被中断了！");
                e.printStackTrace();
            }

        }
    }
}
