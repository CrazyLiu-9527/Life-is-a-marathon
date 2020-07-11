package Leetcode.Array;

import java.util.Arrays;

/**
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 * 说明:
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 *
 * @author zhiyuanliu
 * @date 2020/7/10 15:10
 */
public class Two_Sum2_Input_Array_is_Sorted_167 {

    /**
     * 时间复杂度O(logn)
     *
     * @param nums
     * @param target
     * @return
     * @throws Exception
     */
    public static int[] twoSum(int[] nums, int target) {
        // 思路，双重暴力循环，分别以i和j作为index1和index2，比较的同时判断下标所指的两个值之和和他们的index

        if (nums.length <= 1) {
            return new int[]{};
        }

        // 结果集
        int[] ret = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    ret[0] = i + 1;
                    ret[1] = j + 1;
                }
            }
        }

        return ret;
    }

    public static int[] twoSum2(int[] nums, int target) {
        // 思路，定义i,j分别指向数组的两端，比较他们的和和target的值，不段向中间移动

        if (nums.length <= 1) {
            return new int[]{};
        }

        // 结果集
        int[] ret = new int[2];

        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            if (nums[i] + nums[j] < target) {
                i++;
            }
            if (nums[i] + nums[j] > target) {
                j--;
            }
            if (nums[i] + nums[j] == target) {
                ret[0] = i + 1;
                ret[1] = j + 1;
                break;
            }
        }

        return ret;
    }

    /**
     * 输入: numbers = [2, 7, 11, 15], target = 9
     * 输出: [1,2]
     * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
     *
     * @param args
     */
    public static void main(String[] args) {
//        int[] nums = {2, 7, 11, 15};
//        int[] nums = {1};
        int[] nums = {2, 3, 4};
        int target = 6;
        int[] ret = Two_Sum2_Input_Array_is_Sorted_167.twoSum2(nums, target);
        System.out.println(Arrays.toString(ret));
    }
}
