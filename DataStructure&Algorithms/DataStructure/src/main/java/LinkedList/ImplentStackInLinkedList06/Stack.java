package LinkedList.ImplentStackInLinkedList06;

/**
 * @author liuzy
 * @date 2020/6/15 23:35
 */
public interface Stack<E> {
    int getSize();

    boolean isEmpty();

    void push(E e);

    E pop();

    E peek();
}
