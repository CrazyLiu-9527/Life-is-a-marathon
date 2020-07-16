package Leetcode.Array;

import java.util.List;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 *
 * @author liuzy
 * @date 2020/7/16 23:09
 */
public class Three_sum_15 {

    public static List<List<Integer>> threeSum(int[] nums) {
        // 排序+双指针
        return null;
    }

    /*
    示例：
        给定数组 nums = [-1, 0, 1, 2, -1, -4]，
        满足要求的三元组集合为：
        [
          [-1, 0, 1],
          [-1, -1, 2]
        ]
     */
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> list = Three_sum_15.threeSum(nums);
        System.out.println(list.toString());
    }
}
