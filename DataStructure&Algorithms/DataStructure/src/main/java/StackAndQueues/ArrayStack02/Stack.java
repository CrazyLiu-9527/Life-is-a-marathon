package StackAndQueues.ArrayStack02;

/**
 * @author liuzy
 * @date 2020/6/14 22:08
 */
public interface Stack<E> {

    int getSize();

    boolean isEmpty();

    void push(E e);

    E pop();

    E peek();
}
