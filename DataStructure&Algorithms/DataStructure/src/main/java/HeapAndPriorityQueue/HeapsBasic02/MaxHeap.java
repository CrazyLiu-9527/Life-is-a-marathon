package HeapAndPriorityQueue.HeapsBasic02;

/**
 * 最大堆的一些基本操作
 * @author zhiyuanliu
 * @date 2020/6/24 10:53
 */
public class MaxHeap<E extends Comparable<E>> {
    private Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap() {
    }

    /**
     * 返回堆中的元素个数
     *
     * @return
     */
    public int getSize() {
        return data.getSize();
    }

    /**
     * 返回一个布尔值，表示堆中是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的父节点的索引
     *
     * @param index
     * @return
     */
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 does not have parent");
        }
        return (index - 1) / 2;
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素左孩子节点的索引
     *
     * @return
     */
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子结点的索引
     *
     * @return
     */
    private int rightChild(int index) {
        return index * 2 + 2;
    }

}
