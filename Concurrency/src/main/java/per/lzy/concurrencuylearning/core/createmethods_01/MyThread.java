package per.lzy.concurrencuylearning.core.createmethods_01;

/**
 * 继承thread创建线程
 * @author zhiyuanliu
 * @date 2020/7/10 16:25
 */
public class MyThread extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        MyThread myThread1 = new MyThread();
        MyThread myThread2 = new MyThread();

        myThread1.start();
        myThread2.start();
    }
}
