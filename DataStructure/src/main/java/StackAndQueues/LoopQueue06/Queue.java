package StackAndQueues.LoopQueue06;

/**
 * @author liuzy
 * @date 2020/6/14 23:11
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
