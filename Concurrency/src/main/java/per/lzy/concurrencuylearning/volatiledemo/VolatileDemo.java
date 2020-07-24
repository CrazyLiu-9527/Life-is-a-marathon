package per.lzy.concurrencuylearning.volatiledemo;

/**
 * volatile demo
 *
 * @author zhiyuanliu
 * @date 2020/7/24 14:44
 */
public class VolatileDemo {
    public static volatile boolean over = false;

    // over在用volatile修饰的时候，main线程改变over的值的时候会马上通知thread-0线程，所以3秒之后线程会停止
    // 如果去掉volatile，则main线程改变over的值的时候，thread-0线程不会马上知道该变化，程序会一直运行下去
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (!over) {
            }
        });

        thread.start();
        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        over = true;
    }
}
