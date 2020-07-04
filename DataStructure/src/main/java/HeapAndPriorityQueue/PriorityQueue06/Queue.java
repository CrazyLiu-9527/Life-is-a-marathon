package HeapAndPriorityQueue.PriorityQueue06;

/**
 * @author zhiyuanliu
 * @date 2020/6/24 14:09
 */
public interface Queue<E> {

    int getSize();
    boolean isEmpty();
    void enqueue(E e);
    E dequeue();
    E getFront();

}
