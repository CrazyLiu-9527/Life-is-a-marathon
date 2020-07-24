package per.lzy.concurrencuylearning.producersandconsumers.blockingqueue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 阻塞队列本身就实现了两端的阻塞操作。
 * 可以利用 BlockingQueue 实现生产者-消费者为题，阻塞队列完全可以充当共享数据区域，就可以很好的完成生产者和消费者线程之间的协作
 *
 * @author zhiyuanliu
 * @date 2020/7/24 16:50
 */
public class ProducerConsumer {
    private static LinkedBlockingDeque<Integer> queue = new LinkedBlockingDeque<>();

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(15);
        for (int i = 0; i < 5; i++) {
            service.submit(new Producer(queue));
        }
        for (int i = 0; i < 10; i++) {
            service.submit(new Consumer(queue));
        }
    }

    // 生产者
    static class Producer implements Runnable {

        private BlockingQueue<Integer> queue;

        public Producer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Random random = new Random();
                    int i = random.nextInt(100000);
                    System.out.println("生产者 " + Thread.currentThread().getName() + " 生产数据 " + i);
                    queue.put(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 消费者
    static class Consumer implements Runnable {
        private BlockingQueue<Integer> queue;

        public Consumer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Integer ele = queue.take();
                    System.out.println("消费者" + Thread.currentThread().getName() + " 正在消费数据 " + ele);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
