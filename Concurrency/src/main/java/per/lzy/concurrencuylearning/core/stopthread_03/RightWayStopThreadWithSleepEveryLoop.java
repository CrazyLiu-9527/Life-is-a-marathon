package per.lzy.concurrencuylearning.core.stopthread_03;

/**
 * 如果在执行过程中，每次循环都会调用sleep或wait等方法，那么不需要每次迭代都检查是否已中断
 *
 * @author liuzy
 * @date 2020/7/25 17:29
 */
public class RightWayStopThreadWithSleepEveryLoop implements Runnable {
    /*
        由该程序可以看出，循环中每次都sleep的话，不需要在while中判断Thread.isInterrupted(),中断过程会被检测出并catch到
     */
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadWithSleepEveryLoop());
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }

    @Override
    public void run() {
        int num = 0;
        try {
            // 每次循环都会sleep的，不需要!Thread.currentThread().isInterrupted()判断条件，
            // 因为在抛出InterruptedException之前Java虚拟机会先将该线程的中断标志位复位，
            // 即使调用!Thread.currentThread().isInterrupted()返回也是true
            // 这里要重点理解，不然后面的最佳实践可能会看不懂
            while (num <= 10000) {
                if (num % 100 == 0) {
                    // 验证JVM是否将中断标志位复位，返回true，说明复位了，故加在while循环条件中无用
                    System.out.println(!Thread.currentThread().isInterrupted());
                    System.out.println(num + "是100的倍数");
                }
                num++;
                // 这里会检测中断标志位，如果被修改则抛出异常退出while循环
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
