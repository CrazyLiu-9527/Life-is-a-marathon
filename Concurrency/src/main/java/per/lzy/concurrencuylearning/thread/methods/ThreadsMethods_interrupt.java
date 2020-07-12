package per.lzy.concurrencuylearning.thread.methods;

/**
 * @author liuzy
 * @date 2020/7/12 23:05
 */
public class ThreadsMethods_interrupt implements Runnable {
    @Override
    public void run() {
        int i = 0;
        try {
            while (i < 1000) {
                // 睡个1秒钟我们再执行
                Thread.sleep(1000);
                System.out.println(i++);
            }
        } catch (InterruptedException e) {
            // 判断该阻塞线程是否还在
            System.out.println(Thread.currentThread().isAlive());
            // 判断该线程的中断标志位状态
            System.out.println(Thread.currentThread().isInterrupted());
            System.out.println("In Runnable");
            e.printStackTrace();
        }
    }

    /**
     * 线程中断在之前的版本有stop方法，但是被设置过时了。现在已经没有强制线程终止的方法了！
     * <p>
     * 由于stop方法可以让一个线程A终止掉另一个线程B
     * <p>
     * 被终止的线程B会立即释放锁，这可能会让对象处于不一致的状态。
     * 线程A也不知道线程B什么时候能够被终止掉，万一线程B还处理运行计算阶段，线程A调用stop方法将线程B终止，那就很无辜了~
     * 总而言之，Stop方法太暴力了，不安全，所以被设置过时了。
     * <p>
     * 我们一般使用的是interrupt来请求终止线程~
     * <p>
     * 要注意的是：interrupt不会真正停止一个线程，它仅仅是给这个线程发了一个信号告诉它，它应该要结束了(明白这一点非常重要！)
     * 也就是说：Java设计者实际上是想线程自己来终止，通过上面的信号，就可以判断处理什么业务了。
     * 具体到底中断还是继续运行，应该由被通知的线程自己处理
     * Thread t1 = new Thread( new Runnable(){
     * public void run(){
     * // 若未发生中断，就正常执行任务
     * while(!Thread.currentThread.isInterrupted()){
     * // 正常任务代码……
     * }
     * // 中断的处理代码……
     * doSomething();
     * }
     * } ).start();
     *
     * @param args
     */
    public static void main(String[] args) {
        ThreadsMethods_interrupt runnable = new ThreadsMethods_interrupt();

        // 创建线程并启动
        Thread t = new Thread(runnable);
        System.out.println("This is main ");
        t.start();
        try {
            // 在 main线程睡个3秒钟
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("In main");
            e.printStackTrace();
        }
        // 设置中断
        t.interrupt();
    }
}
