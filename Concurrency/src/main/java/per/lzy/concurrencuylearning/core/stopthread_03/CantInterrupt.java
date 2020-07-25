package per.lzy.concurrencuylearning.core.stopthread_03;

/**
 * 如果while里面放try/catch，会导致中断失效
 *
 * @author liuzy
 * @date 2020/7/25 17:33
 */
public class CantInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            while (num <= 10000 && !Thread.currentThread().isInterrupted()) {
                if (num % 100 == 0) {
                    System.out.println(num + "是100的倍数");
                }
                num++;

                // 收到interrupt`信号，复原中断标志位，抛出异常，但是无法退出while循环，所以会继续运行
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
}
