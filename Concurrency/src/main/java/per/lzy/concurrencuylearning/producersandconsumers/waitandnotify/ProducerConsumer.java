package per.lzy.concurrencuylearning.producersandconsumers.waitandnotify;

import java.util.Date;
import java.util.LinkedList;

/**
 * @author liuzy
 * @date 2020/7/26 16:43
 */
public class ProducerConsumer {

    public static void main(String[] args) {
        EventStorage eventStorage = new EventStorage();
        Thread producer = new Thread(new Producer(eventStorage));
        Thread consumer = new Thread(new Consumer(eventStorage));
        producer.start();
        consumer.start();
    }
}

// 生产者
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

// 消费者
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
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 没满时就接着放数据
        linkedList.add(new Date().getTime());
        System.out.println("仓库中有了" + linkedList.size() + "个产品");
        notify();
    }

    public synchronized void take() {
        // 队列为空的时候，消费者wait()等待生产者唤醒
        while (linkedList.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 不为空的时候就取数据
        long date = linkedList.remove();
        System.out.println("拿到了" + date + ",现在仓库还剩下" + linkedList.size());
        notify();
    }
}