package HeapAndPriorityQueue.PriorityQueue06;

/**
 * 新增替换操作
 * @author zhiyuanliu
 * @date 2020/6/24 10:53
 */
public class MaxHeap<E extends Comparable<E>> {
    private Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap() {
        data = new Array<>();
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

    /**
     * 向堆中添加元素
     * @param e
     */
    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    /**
     * 元素的上浮操作
     * @param k
     */
    private void siftUp(int k) {
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            data.swap(k , parent(k));
            k = parent(k);
        }
    }

    /**
     * 查找堆中的最大元素
     * @return
     */
    public E findMax() {
        if (data.isEmpty()) {
            throw new IllegalArgumentException("Cannot find max when heap is empty");
        }

        return data.get(0);
    }

    /**
     * 取出堆中的最大元素
     * 过程：
     * 1. 先找到堆中的最大元素
     * 2. 交换最大元素和最后一个元素的值
     * 3. 删除最后一个元素
     * 4. 元素下沉操作
     *
     * @return
     */
    public E extractMax() {
        E e = findMax();

        data.swap(0, data.getSize() - 1);
        data.removeLast();

        siftDown(0);

        return e;
    }

    /**
     * 元素下沉操作
     * 元素与其两个子节点中较大的值交换位置，并以此类推，直到当前节点的值比两个子节点都大
     * data[j]是两个子节点中较大的节点的值
     * @param k
     */
    private void siftDown(int k) {
        while (leftChild(k) < data.getSize()) {
            // 找到子节点中较大的元素的索引
            int j = leftChild(k);
            if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0) {
                j++;
            }

            //判断索引k位置的值与其子节点的值是否满足交换条件
            if(data.get(k).compareTo(data.get(j)) >= 0) {
                break;
            }
            data.swap(k, j);
            k = j;
        }
    }

    /**
     * 取出堆中的最大元素，并替换成元素e
     * @param e
     * @return
     */
    public E replace(E e){

        E ret = findMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }
}
