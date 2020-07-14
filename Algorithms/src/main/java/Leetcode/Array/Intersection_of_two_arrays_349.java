package Leetcode.Array;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 * @author liuzy
 * @date 2020/7/14 23:39
 */
public class Intersection_of_two_arrays_349 {

    public static int[] intersection(int[] nums1, int[] nums2) {
        // 将nums1中的元素添加进set中
        HashSet<Integer> nums1set = new HashSet<>();
        for (int i : nums1) {
            nums1set.add(i);
        }

        // 找到交集元素放到resSet中
        HashSet<Integer> resSet = new HashSet<>();
        for (int i : nums2) {
            if (nums1set.contains(i)) {
                resSet.add(i);
            }
        }

        // 遍历保存到res
        int i = 0;
        int[] res = new int[resSet.size()];
        for (Integer integer : resSet) {
            res[i++] = integer;
        }

        return res;
    }

    /**
     * 示例 1：
     * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出：[2]
     * 示例 2：
     * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出：[9,4]
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] nums1 = {4, 9, 5};
        int[] nums2 = {9, 4, 9, 8, 4};
        int[] res = Intersection_of_two_arrays_349.intersection(nums1, nums2);
        System.out.println(Arrays.toString(res));
    }
}
