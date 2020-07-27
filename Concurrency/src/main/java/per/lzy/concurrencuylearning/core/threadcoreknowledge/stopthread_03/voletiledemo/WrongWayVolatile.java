package per.lzy.concurrencuylearning.core.threadcoreknowledge.stopthread_03.voletiledemo;

import java.util.concurrent.TimeUnit;

/**
 * 演示用volatile的局限：part1 看似可行
 *
 * @author zhiyuanliu
 * @date 2020/7/27 14:04
 */
public class WrongWayVolatile implements Runnable {
    private static volatile boolean canceled = false;

    public static void main(String[] args) throws InterruptedException {
        WrongWayVolatile r = new WrongWayVolatile();
        Thread thread = new Thread(r);
        thread.start();
        TimeUnit.SECONDS.sleep(5);
        canceled = true;
    }

    @Override
    public void run() {
        int num = 0;
        try {
            while (num <= 10000 && !canceled) {
                if (num % 100 == 0) {
                    System.out.println(num + "是100的倍数");
                }
                num++;
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
