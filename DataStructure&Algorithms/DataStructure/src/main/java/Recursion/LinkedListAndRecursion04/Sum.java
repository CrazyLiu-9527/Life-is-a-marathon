package Recursion.LinkedListAndRecursion04;

/**
 * @author zhiyuanliu
 * @date 2020/6/17 17:44
 */
public class Sum {
    public static int sum(int[] arr) {
        return sum(arr, 0);
    }

    private static int sum(int[] arr, int i) {
        if (arr.length == i) {
            return 0;
        }

        return arr[i] + sum(arr, i + 1);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        int ret = sum(arr);
        System.out.println(ret);
    }
}
