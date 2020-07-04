package StackAndQueues.ArrayQueue05;

/**
 * @author liuzy
 * @date 2020/6/14 22:40
 */
public interface Queue<E> {

    /**
     * 判断队列是否为空
     */
    boolean isEmpty();

    /**
     * 获取队列长度
     */
    int getSize();

    /**
     * 出队
     */
    E dequeue();

    /**
     * 入队
     */
    void enqueue(E e);

    /**
     * 获取队首
     */
    E getFront();
}
