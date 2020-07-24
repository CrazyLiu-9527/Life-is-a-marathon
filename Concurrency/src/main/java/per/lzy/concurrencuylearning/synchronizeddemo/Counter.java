package per.lzy.concurrencuylearning.synchronizeddemo;

/**
 * 使用synchronized实现线程同步demo
 *
 * @author zhiyuanliu
 * @date 2020/7/24 14:50
 */
public class Counter {

    public static int count = 0;
    static Object obj = new Object();

    public static void inc() {
        // 使用synchronized完成线程同步
        synchronized (obj) {
            count++;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            new Thread(() -> {
                Counter.inc();
//                    System.out.println(Thread.currentThread().getName() + "++");
            }).start();
        }

        // 这里sleep是为了等待上面的100000个线程处理完成，可以注释掉查看一下
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 这里每次运行的值都有可能不同,可能为100
        System.out.println("Counter.count=" + Counter.count);
    }
}
