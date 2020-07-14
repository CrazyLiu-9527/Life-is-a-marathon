package Leetcode.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 * @author liuzy
 * @date 2020/7/14 23:55
 */
public class Intersection_of_two_arrays2_350 {

    public static int[] intersect(int[] nums1, int[] nums2) {
        // 先用一个map将nums1中每个数字出现的次数保存一下
        HashMap<Integer, Integer> nums1HashMap = new HashMap<>();
        for (int i : nums1) {
            if (nums1HashMap.containsKey(i)) {
                nums1HashMap.put(i, nums1HashMap.get(i) + 1);
            } else {
                nums1HashMap.put(i, 1);
            }
        }

        // 再遍历nums2，并用一个list将结果保存，map中包含元素就放到list中，并将map的值减一
        ArrayList<Integer> resList = new ArrayList<>();
        for (int i : nums2) {
            if (nums1HashMap.containsKey(i)) {
                resList.add(i);
                nums1HashMap.put(i, nums1HashMap.get(i) - 1);
                if (nums1HashMap.get(i) == 0) {
                    nums1HashMap.remove(i);
                }
            }
        }

        int[] res = new int[resList.size()];
        int i = 0;
        for (Integer integer : resList) {
            res[i++] = integer;
        }

        return res;
    }

    /**
     * 示例 1：
     * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出：[2,2]
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        int[] res = Intersection_of_two_arrays2_350.intersect(nums1, nums2);
        System.out.println(Arrays.toString(res));
    }
}
