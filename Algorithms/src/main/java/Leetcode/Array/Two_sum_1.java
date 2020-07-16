package Leetcode.Array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 * @author liuzy
 * @date 2020/7/16 22:46
 */
public class Two_sum_1 {

    public static int[] twoSum(int[] nums, int target) throws Exception {
        // 思路，双重暴力循环
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }

        return new int[]{};
//        throw new Exception("找不到对应的值");
    }

    public static int[] twoSum2(int[] nums, int target) {
        // 思路，查找表

        // key保存元素值，value保存索引
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        // 二遍循环查询出来需要的元素
        for (int i = 0; i < nums.length; i++) {
            int another = target - nums[i];
            if (map.containsKey(another) && map.get(another) != i) {
                return new int[]{i, map.get(another)};
            }
        }

        return new int[]{};
    }

    public static int[] twoSum3(int[] nums, int target) {
        // 思路，查找表 一遍循环

        // key保存元素值，value保存索引
        Map<Integer, Integer> map = new HashMap<>();

        // 二遍循环查询出来需要的元素
        for (int i = 0; i < nums.length; i++) {
            int another = target - nums[i];
            if (map.containsKey(another) && map.get(another) != i) {
                return new int[]{i, map.get(another)};
            }
            map.put(nums[i], i);
        }

        return new int[]{};
    }

    /*
    示例:
        给定 nums = [2, 7, 11, 15], target = 9
        因为 nums[0] + nums[1] = 2 + 7 = 9
        所以返回 [0, 1]
     */
    public static void main(String[] args) throws Exception {
        int[] nums = {2, 7, 11, 15};
        int target = 18;
        int[] res = Two_sum_1.twoSum(nums, target);
        System.out.println(Arrays.toString(res));
    }
}
