package Array.CreateOurOwnArray02;

/**
 * 实现我们自己的数组
 *
 * @author liuzy
 * @date 2020/6/13 23:56
 */
public class Array {

    // 保存数据的数组
    private int[] data;
    // data数组实际保存的数据量
    private int size;

    // 构造函数，传入的数组容量capacity构造array
    public Array(int capacity) {
        data = new int[capacity];
    }

    // 无参构造函数，默认的数组容量capacity = 10
    public Array() {
        this(10);
    }

    // 获取数组容量
    public int getCapacity() {
        return data.length;
    }

    // 获取数组的元素个数
    public int getSize() {
        return size;
    }

    // 返回数组是否为空
    public boolean isEmpty() {
        return size == 0;
    }
}
