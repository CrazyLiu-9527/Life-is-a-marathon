package per.lzy.concurrencuylearning.practice.producersandconsumers.waitandnotifyall;

import java.util.Date;
import java.util.LinkedList;
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
        EventStorage eventStorage = new EventStorage();
        ExecutorService service = new ThreadPoolExecutor(15, 15, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>(100));
        for (int i = 0; i < 5; i++) {
            service.submit(new Producer(eventStorage));
        }
        for (int i = 0; i < 10; i++) {
            service.submit(new Consumer(eventStorage));
        }
    }
}


// 生产者线程
class Producer implements Runnable {

    private EventStorage eventStorage;

    public Producer(EventStorage eventStorage) {
        this.eventStorage = eventStorage;
    }

    @Override
    public void run() {
        while (true) {
            eventStorage.put();
        }
    }
}

// 消费者线程
class Consumer implements Runnable {

    private EventStorage eventStorage;

    public Consumer(EventStorage eventStorage) {
        this.eventStorage = eventStorage;
    }

    @Override
    public void run() {
        while (true) {
            eventStorage.take();
        }
    }
}

// 数据层抽象出来
class EventStorage {
    private int maxLength;
    private LinkedList<Long> linkedList;

    public EventStorage() {
        this.maxLength = 100;
        this.linkedList = new LinkedList<>();
    }

    public synchronized void put() {
        // 队列中放满时，生产者wait()等待消费者唤醒
        while (linkedList.size() == maxLength) {
            System.out.println("生产者 " + Thread.currentThread().getName() + " list已达到最大容量，进行wait");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("生产者 " + Thread.currentThread().getName() + " 退出wait");
        }

        // 没满时就接着放数据
        long ele = new Date().getTime();
        linkedList.add(ele);
        System.out.println("生产者 " + Thread.currentThread().getName() + " 生产数据：" + ele);
        notifyAll();
    }

    public synchronized void take() {
        // 队列为空的时候，消费者wait()等待生产者唤醒
        while (linkedList.size() == 0) {
            System.out.println("消费者 " + Thread.currentThread().getName() + " list为空，阻塞等待");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("消费者 " + Thread.currentThread().getName() + " 退出等待");
        }

        // 不为空的时候就取数据
        long ele = linkedList.remove();
        System.out.println("消费者 " + Thread.currentThread().getName() + " 消费数据：" + ele);
        notifyAll();
    }
}