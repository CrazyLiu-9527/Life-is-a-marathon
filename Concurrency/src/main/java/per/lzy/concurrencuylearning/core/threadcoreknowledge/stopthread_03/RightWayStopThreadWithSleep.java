package per.lzy.concurrencuylearning.core.threadcoreknowledge.stopthread_03;

/**
 * 带有sleep的中断线程写法
 *
 * @author liuzy
 * @date 2020/7/25 17:24
 */
public class RightWayStopThreadWithSleep implements Runnable {
    /*
    由该程序可以看出，在线程阻塞的时候发出interrupt信号，线程会抛出异常
     */
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadWithSleep());
        thread.start();
        thread.sleep(500);
        thread.interrupt();

    }

    @Override
    public void run() {
        int num = 0;
        try {
            while (!Thread.currentThread().isInterrupted() && num <= 300) {
                if (num % 100 == 0) {
                    System.out.println(num + "是100的倍数");
                }
                num++;
            }
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
