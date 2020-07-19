package per.lzy.concurrencuylearning.threadbase;

/**
 * 实现runnable接口创建线程
 * @author zhiyuanliu
 * @date 2020/7/10 16:27
 */
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();

        Thread t1 = new Thread(myRunnable);
        Thread t2 = new Thread(myRunnable);

        t1.start();
        t2.start();
    }
}
