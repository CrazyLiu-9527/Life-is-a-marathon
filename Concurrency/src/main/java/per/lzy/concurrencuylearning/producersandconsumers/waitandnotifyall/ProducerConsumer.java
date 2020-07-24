package per.lzy.concurrencuylearning.producersandconsumers.waitandnotifyall;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 使用wait&notifyAll实现生产者消费者模式
 *
 * @author zhiyuanliu
 * @date 2020/7/24 16:07
 */
public class ProducerConsumer {

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
//        ExecutorService service = Executors.newFixedThreadPool(15);
        ExecutorService service = new ThreadPoolExecutor(15, 15, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>(100));
        for (int i = 0; i < 5; i++) {
            service.submit(new Producer(linkedList, 8));
        }
        for (int i = 0; i < 10; i++) {
            service.submit(new Consumer(linkedList));
        }
    }

    // 生产者线程
    static class Producer implements Runnable {

        private List<Integer> list;
        private int maxLength;

        public Producer(List<Integer> list, int maxLength) {
            this.list = list;
            this.maxLength = maxLength;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (list) {
                    try {
                        while (list.size() == maxLength) {
                            System.out.println("生产者 " + Thread.currentThread().getName() + " list已达到最大容量，进行wait");
                            list.wait();
                            System.out.println("生产者 " + Thread.currentThread().getName() + " 退出wait");
                        }
                        Random random = new Random();
                        int i = random.nextInt();
                        System.out.println("生产者 " + Thread.currentThread().getName() + " 生产数据 " + i);
                        list.add(i);
                        list.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    // 消费者线程
    static class Consumer implements Runnable {

        private List<Integer> list;

        public Consumer(List<Integer> list) {
            this.list = list;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (list) {
                    try {
                        while (list.isEmpty()) {
                            System.out.println("消费者 " + Thread.currentThread().getName() + " list为空，阻塞等待");
                            list.wait();
                            System.out.println("消费者 " + Thread.currentThread().getName() + " 退出等待");
                        }
                        Integer ele = list.remove(0);
                        System.out.println("消费者 " + Thread.currentThread().getName() + " 消费数据：" + ele);
                        list.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
