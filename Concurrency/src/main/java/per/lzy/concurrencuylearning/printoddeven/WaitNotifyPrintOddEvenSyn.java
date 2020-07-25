package per.lzy.concurrencuylearning.printoddeven;

/**
 * 两个线程交替打印0~100的奇偶数，用synchronized关键字实现
 *
 * @author liuzy
 * @date 2020/7/26 00:42
 */
public class WaitNotifyPrintOddEvenSyn {

    /**
     * 充当共享变量的object，锁的对象
     */
    private static final Object lock = new Object();
    /**
     * count
     */
    private static int count = 0;

    //新建2个线程
    //1个只处理偶数，第二个只处理奇数（用位运算）
    //用synchronized来通信
    public static void main(String[] args) {
        new Thread(() -> {
            while (count < 100) {
                synchronized (lock) {
                    if (count % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + ": " + count++);
                    }
                }
            }
        }, "偶数").start();

        new Thread(() -> {
            while (count < 100) {
                synchronized (lock) {
                    while (count % 2 == 1) {
                        System.out.println(Thread.currentThread().getName() + ": " + count++);
                    }
                }
            }
        }, "奇数").start();
    }
}
