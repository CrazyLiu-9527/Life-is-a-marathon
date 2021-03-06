package SetAndMap.MoreAboutMap08;

/**
 * @author liuzy
 * @date 2020/6/23 22:31
 */
public interface Map<K, V> {
    void add(K key, V value);

    V remove(K key);

    boolean contains(K key);

    V get(K key);

    void set(K key, V newValue);

    int getSize();

    boolean isEmpty();
}
