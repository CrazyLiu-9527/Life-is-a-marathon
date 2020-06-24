package SortingAlgorithm;

/**
 * 冒泡排序算法
 * 时间复杂度O（n^2）
 * @author zhiyuanliu
 * @date 2020/6/24 14:40
 */
public class BubbleSort {

    /*
     * 思路：
     * 俩俩交换，大的放在后面，第一次排序后最大值已在数组末尾。
     * 因为俩俩交换，需要n-1趟排序，比如10个数，需要9趟排序
     *
     * 代码实现要点：
     * 两个for循环，外层循环控制排序的趟数，内层循环控制比较的次数
     * 每趟过后，比较的次数都应该要减1
     *
     * 优化：
     * 如果一趟排序后也没有交换位置，那么该数组已有序～
     */

    /**
     * 冒泡排序
     * @param arr
     * @return
     */
    public static int[] bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    //交换
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        return arr;
    }

    /**
     * 优化后的冒泡排序
     * @param arr
     * @return
     */
    public static int[] bubbleSort2(int[] arr) {
        //外层循环是排序的趟数
        for (int i = 0; i < arr.length - 1; i++) {

            //每比较一趟就重新初始化为0
            int isChange = 0;

            //内层循环是当前趟数需要比较的次数
            for (int j = 0; j < arr.length - i - 1; j++) {

                //前一位与后一位与前一位比较，如果前一位比后一位要大，那么交换
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;


                    //如果进到这里面了，说明发生置换了
                    isChange = 1;

                }
            }
            //如果比较完一趟没有发生置换，那么说明已经排好序了，不需要再执行下去了
            if (isChange == 0) {
                break;
            }

        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 3, 7, 6};
        int[] res = BubbleSort.bubbleSort(arr);
        for (int i : res) {
            System.out.println(i);
        }
        System.out.println("======================");
        int[] res2 = BubbleSort.bubbleSort2(arr);
        for (int i : res) {
            System.out.println(i);
        }
    }
}
