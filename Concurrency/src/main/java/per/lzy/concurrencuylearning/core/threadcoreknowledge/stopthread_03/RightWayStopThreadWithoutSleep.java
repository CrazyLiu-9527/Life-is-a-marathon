package per.lzy.concurrencuylearning.core.threadcoreknowledge.stopthread_03;

/**
 * run方法内没有sleep或wait方法时，停止线程
 *
 * @author liuzy
 * @date 2020/7/25 17:18
 */
public class RightWayStopThreadWithoutSleep implements Runnable {

    /*
    正常情况下使用interrupt停止线程，会将被停止线程的中断标志位设置为true，然后线程停止
     */
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadWithoutSleep());
        thread.start();
        /*
         * 发送中断信号,改变中断标志位, 仅此而已
         * 如果线程循环条件是while (true), t线程会继续执行下去
         * 如果线程循环条件是while (!Thread.interrupted()), t线程会退出
         */
        Thread.sleep(2000);
        thread.interrupt();
    }

    @Override
    public void run() {
        int num = 0;
        // 当接收到中断信号，退出循环，任务结束
        while (!Thread.currentThread().isInterrupted() && num < Integer.MAX_VALUE) {
            if (num % 10000 == 0) {
                System.out.println(num + "是10000的倍数");
            }
            num++;
        }

        System.out.println("任务运行结束了");
    }
}
