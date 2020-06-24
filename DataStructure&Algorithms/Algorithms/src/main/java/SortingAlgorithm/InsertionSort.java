package SortingAlgorithm;

/**
 * 插入排序
 * 时间复杂度O（n^2）
 *
 * @author zhiyuanliu
 * @date 2020/6/24 23:16
 */
public class InsertionSort {

    public static void insertionSort(int[] arr) {
        //临时变量
        int temp;

        //外层循环控制需要排序的趟数(从1开始因为将第0位看成了有序数据)
        for (int i = 1; i < arr.length; i++) {

            //temp保存的是每次取出来和前面有序队列比较的那个值
            temp = arr[i];

            //如果前一位(已排序的数据)比当前数据要大，那么就进入循环比较[参考第二趟排序]
            while (i >= 1 && arr[i - 1] > temp) {

                //往后退一个位置，让当前数据与之前前位进行比较
                arr[i] = arr[i - 1];

                //不断往前，直到退出循环
                i--;

            }

            //退出了循环说明找到了合适的位置了，将当前数据插入合适的位置中
            arr[i] = temp;
        }

    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 3, 7, 6, 4};
        InsertionSort.insertionSort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
