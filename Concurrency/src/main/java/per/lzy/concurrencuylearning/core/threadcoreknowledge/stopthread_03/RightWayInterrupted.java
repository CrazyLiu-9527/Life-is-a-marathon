package per.lzy.concurrencuylearning.core.threadcoreknowledge.stopthread_03;

/**
 * 注意Thread.interrupted()方法的目标对象是“当前线程”，而不管本方法来自于哪个对象
 *
 * @author liuzy
 * @date 2020/7/25 17:40
 */
public class RightWayInterrupted {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {

            for (; ; ) {

            }
        });

        // 启动线程
        thread.start();
        // 设置中断标识
        thread.interrupt();
        // 获取中断标识
        System.out.println("isInterrupted: " + thread.isInterrupted());
        // 获取中断标识位并重置
        System.out.println("isInterrupted: " + thread.interrupted());
        //获取中断标标识并重置
        System.out.println("isInterrupted: " + Thread.interrupted());
        //获取中断标识
        System.out.println("isInterrupted: " + thread.isInterrupted());
        thread.join();
        System.out.println("Main thread is over.");
    }
}
