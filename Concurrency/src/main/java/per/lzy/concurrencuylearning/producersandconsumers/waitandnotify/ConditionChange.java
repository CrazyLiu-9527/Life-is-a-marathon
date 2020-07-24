package per.lzy.concurrencuylearning.producersandconsumers.waitandnotify;

import java.util.ArrayList;
import java.util.List;

/**
 * 如果线程在等待时接受到了通知，但是之后等待的条件发生了变化，并没有再次对等待条件进行判断，也会导致程序出现错误。
 * 本程序就是这样一个例子
 *
 * @author zhiyuanliu
 * @date 2020/7/24 15:53
 */
public class ConditionChange {

    private static List<String> lockObject = new ArrayList();

    public static void main(String[] args) {
        Consumer consumer1 = new Consumer(lockObject);
        consumer1.setName("消费者线程1");
        Consumer consumer2 = new Consumer(lockObject);
        consumer2.setName("消费者线程2");
        Producer producer = new Producer(lockObject);
        producer.setName("生产者线程1");
        consumer1.start();
        consumer2.start();
        producer.start();
    }

    // 消费者线程
    static class Consumer extends Thread {
        private List<String> lock;

        public Consumer(List<String> lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock) {
                try {
                    // 这里使用if的话，就会存在wait条件变化造成程序错误的问题
                    // 应该改成while
                    // 总结：在使用线程的等待/通知机制时，一般都要在 while 循环中调用 wait()方法，因此需要配合使用一个 boolean 变量（或其他能判断真假的条件，如本文中的 list.isEmpty()），
                    // 满足 while 循环的条件时，进入 while 循环，执行 wait()方法，不满足 while 循环的条件时，跳出循环，执行后面的代码
                    while (lock.isEmpty()) {
                        System.out.println(Thread.currentThread().getName() + " list为空");
                        System.out.println(Thread.currentThread().getName() + " 调用wait方法");
                        lock.wait();
                        System.out.println(Thread.currentThread().getName() + "  wait方法结束");
                    }
                    String element = lock.remove(0);
                    System.out.println(Thread.currentThread().getName() + " 取出第一个元素为：" + element);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 生产者线程
    static class Producer extends Thread {
        private List<String> lock;

        public Producer(List<String> lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " 开始添加元素");
                lock.add("苹果");
//                lock.add("香蕉");
//                lock.add("橘子");
                lock.notifyAll();
            }
        }
    }
}
