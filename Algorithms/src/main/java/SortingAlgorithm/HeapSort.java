package SortingAlgorithm;

/**
 * 堆排序
 * 堆是一种完全二叉树（完全二叉树是一种特殊的二叉搜索树），最大值在堆的顶部
 * @author zhiyuanliu
 * @date 2020/7/2 21:21
 */
public class HeapSort {

    /**
     * 堆排序
     * @param arr
     */
    public static void heapSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {

            //每完成一次建堆就可以排除一个元素了
            maxHeapify(arr, arr.length - i);

            //交换
            int temp = arr[0];
            arr[0] = arr[(arr.length - 1) - i];
            arr[(arr.length - 1) - i] = temp;

        }
    }

    /**
     * 完成一次建堆，最大值在堆的顶部(根节点)
     */
    public static void maxHeapify(int[] arr, int size) {

        for (int i = size - 1; i >= 0; i--) {
            heapify(arr, i, size);
        }

    }


    /**
     * 建堆
     *
     * @param arr          看作是完全二叉树
     * @param currentRootNode 当前父节点位置
     * @param size            节点总数
     */
    public static void heapify(int[] arr, int currentRootNode, int size) {

        if (currentRootNode < size) {
            //左子树和右字数的位置
            int left = 2 * currentRootNode + 1;
            int right = 2 * currentRootNode + 2;

            //把当前父节点位置看成是最大的
            int max = currentRootNode;

            if (left < size) {
                //如果比当前根元素要大，记录它的位置
                if (arr[max] < arr[left]) {
                    max = left;
                }
            }
            if (right < size) {
                //如果比当前根元素要大，记录它的位置
                if (arr[max] < arr[right]) {
                    max = right;
                }
            }
            //如果最大的不是根元素位置，那么就交换
            if (max != currentRootNode) {
                int temp = arr[max];
                arr[max] = arr[currentRootNode];
                arr[currentRootNode] = temp;

                //继续比较，直到完成一次建堆
                heapify(arr, max, size);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 3, 7, 6, 4};
        HeapSort.heapSort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
