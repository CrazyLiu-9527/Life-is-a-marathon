package per.lzy.concurrencuylearning.core.stopthread_03.bestpractice;

/**
 * 最佳实践1：catch了InterruptedException之后的优先选择：在方法签名中抛出异常 那么在run()就会强制try/catch
 *
 * @author liuzy
 * @date 2020/7/25 17:47
 */
public class RightWayStopThreadInProd implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProd());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("go");
            System.out.println(Thread.currentThread().isInterrupted());
            try {
                throwInMethod();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().isInterrupted());
                // 阻塞的时候收到interrupt信号所以进到了catch，然后interrupt标识位被复位为false，
                // 所有下面需要重新将线程中断的标志位设置为true，通知上面的循环停止运行
                Thread.currentThread().interrupt();
                System.out.println(Thread.currentThread().isInterrupted());
                // 保存日志、停止程序
                System.out.println("保存日志");
                e.printStackTrace();
            }
        }
    }

    private void throwInMethod() throws InterruptedException {
        Thread.sleep(2000);
    }
}
