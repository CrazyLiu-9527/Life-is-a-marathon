package SortingAlgorithm;

/**
 * 选择排序：
 * 找到数组中最大的元素，与数组中最后一位元素交换
 * 当只有一个数时，则不需要选择了，因此需要n-1趟排序，比如10个数，需要9趟排序
 *
 * @author zhiyuanliu
 * @date 2020/7/2 21:07
 */
public class SelectionSort {

    /**
     * 选择排序
     *
     * @param arr
     */
    public static void selectionSort(int[] arr) {
        // 控制需要循环的次数
        for (int i = 0; i < arr.length - 1; i++) {

            // 每次新的循环将索引重新置为0
            int index = 0;

            // 内层循环控制遍历数组的个数并得到最大数的角标
            // 每次遍历找到最大的那个数的索引
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[index]) {
                    index = j;
                }
            }

            // 找到最大的索引和最后一个无序的值交换
            int temp = arr[index];
            arr[index] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 3, 7, 6, 4};
        SelectionSort.selectionSort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
