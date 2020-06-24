package SortingAlgorithm;

/**
 * 快速排序
 * 核心思想 分治
 * 时间复杂度O（nlogn）
 *
 * @author zhiyuanliu
 * @date 2020/6/24 17:27
 */
public class QuickSort {

    /**
     * 普通快排
     *
     * @param arr 要进行排序的数组
     * @param L   指向数组最左侧的元素
     * @param R   指向数组最右侧的元素
     * @return
     */
    public static void quickSort(int[] arr, int L, int R) {
        // 使i等于最左侧数据的索引
        int i = L;
        // 使j等于最右侧数据的索引
        int j = R;

        // 找到中间值作为支点
        int pivot = arr[(L + R) / 2];

        // 当i<=j的情况下，不断的判断是否满足两边数据交换的条件
        while (i <= j) {

            // 寻找左侧比支点大的数据的索引
            while (pivot > arr[i]) {
                i++;
            }
            // 寻找右侧比支点大的数据的索引
            while (pivot < arr[j]) {
                j--;
            }

            // 若i<=j说明两端都已经找到需要交换的数据
            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }

        //“左边”再做排序，直到左边剩下一个数(递归出口)
        if (L < j) {
            quickSort(arr, L, j);
        }

        //“右边”再做排序，直到右边剩下一个数(递归出口)
        if (i < R) {
            quickSort(arr, i, R);
        }
    }

    /**
     * 双路快排
     * @param args
     */

    /**
     * 三路快排
     * @param args
     */

    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 3, 7, 6};
        QuickSort.quickSort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
