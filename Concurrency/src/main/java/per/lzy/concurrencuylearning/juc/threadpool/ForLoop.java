package per.lzy.concurrencuylearning.juc.threadpool;

/**
 * 演示没有线程池的时候创建多个线程
 *
 * @author liuzy
 * @date 2020/7/31 00:21
 */
public class ForLoop {

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(new Task());
            thread.start();
        }
    }

    static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println("执行了任务");
        }
    }
}
