package SortingAlgorithm;

/**
 * 希尔排序
 * 时间复杂度O（n^1.3）
 * @author zhiyuanliu
 * @date 2020/6/24 23:32
 */
public class ShellSort {

    public static void shellSort(int[] array) {
        // gap也就相当于组别
        for (int gap = array.length >> 1; gap > 0; gap /= 2) {
            // 对分的每个组进行插入排序，直至完毕
            for (int i = gap; i < array.length; i++) {
                int j = i;
                while (j - gap >= 0 && array[j] < array[j - gap]) {

                    int temp = array[j];
                    array[j] = array[j - gap];
                    array[j - gap] = temp;

                    j -= gap;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 3, 7, 6, 4};
        ShellSort.shellSort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }

}
