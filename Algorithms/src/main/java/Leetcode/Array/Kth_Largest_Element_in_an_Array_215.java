package Leetcode.Array;

import java.util.Arrays;

/**
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * @author liuzy
 * @date 2020/7/9 23:59
 */
public class Kth_Largest_Element_in_an_Array_215 {

    public static int findKthLargest(int[] nums, int k) {
        // 我的思路：将题目转化为查找第nums.length-k个小的元素
        Arrays.sort(nums);

        return nums[nums.length - k];
    }

    // todo 题目本意是让自己实现排序，后面补上自己实现快排的题解

    /**
     * 输入: [3,2,1,5,6,4] 和 k = 2
     * 输出: 5
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 6, 4};
        int k = 2;
        int res = Kth_Largest_Element_in_an_Array_215.findKthLargest(nums, k);
        System.out.println(res);
    }
}
