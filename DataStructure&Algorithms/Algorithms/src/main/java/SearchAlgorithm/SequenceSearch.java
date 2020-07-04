package SearchAlgorithm;

/**
 * 顺序查找
 *
 * @author liuzy
 * @date 2020/7/4 16:51
 */
public class SequenceSearch {

    public static int sequenceSearch(int[] arr, int key) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 6, 5, 9, 8, 4, 3, 1};
        int res = SequenceSearch.sequenceSearch(arr, 5);
        System.out.println(res);
    }
}
