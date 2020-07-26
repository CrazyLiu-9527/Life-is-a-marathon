package per.lzy.concurrencuylearning.core.threadcoreknowledge.createmethods_01.wrongways;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时器创建线程
 * 本质上是执行runnable方法
 *
 * @author liuzy
 * @date 2020/7/25 16:52
 */
public class TimmerTaskDemo {

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }, 1000, 1000);
    }
}
