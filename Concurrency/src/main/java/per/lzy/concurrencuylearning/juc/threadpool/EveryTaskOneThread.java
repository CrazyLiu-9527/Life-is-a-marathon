package per.lzy.concurrencuylearning.juc.threadpool;

/**
 * 演示一个线程执行一个runnable
 *
 * @author liuzy
 * @date 2020/7/31 00:15
 */
public class EveryTaskOneThread {
    public static void main(String[] args) {
        Thread thread = new Thread(new Task());
        thread.start();
    }

    static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println("执行了任务");
        }
    }
}
