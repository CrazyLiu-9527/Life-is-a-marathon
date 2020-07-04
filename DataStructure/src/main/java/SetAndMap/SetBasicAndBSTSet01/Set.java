package SetAndMap.SetBasicAndBSTSet01;

/**
 * @author zhiyuanliu
 * @date 2020/6/23 10:32
 */
public interface Set<E> {

    void add(E e);

    boolean contains(E e);

    void remove(E e);

    int getSize();

    boolean isEmpty();
}
