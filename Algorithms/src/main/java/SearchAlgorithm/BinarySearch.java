package SearchAlgorithm;

/**
 * 二分查找
 *
 * @author zhiyuanliu
 * @date 2020/7/2 21:34
 */
public class BinarySearch {

    /**
     * 二分查找算法
     *
     * @param arr
     * @param key
     * @return
     */
    public static int binarySearch(int[] arr, int key, int L, int R) {
        int l = L;
        int r = R;

        while (l <= r) {
            int middle = (l + r) / 2;
            if (arr[middle] == key) {
                return middle;
            }

            if (arr[middle] > key) {
                r = middle - 1;
            } else {
                l = middle + 1;
            }
            binarySearch(arr, key, l, r);
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int index = BinarySearch.binarySearch(arr, 6, 0, arr.length - 1);
        System.out.println(index);
    }
}
