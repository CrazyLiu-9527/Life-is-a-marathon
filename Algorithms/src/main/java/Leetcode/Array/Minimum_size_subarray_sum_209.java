package Leetcode.Array;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的子数组，并返回其长度。如果不存在符合条件的子数组，返回 0。
 *
 * @author liuzy
 * @date 2020/7/12 21:48
 */
public class Minimum_size_subarray_sum_209 {

    /**
     * 暴力解法
     * 第一层控制几个数相加起始位置
     * 第二层控制加几个超过目标值了，并记录
     * 双层循环可以保证循环完毕所有结果都走一遍
     *
     * @param s
     * @param nums
     * @return
     */
    public static int minSubArrayLen(int s, int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {

            int sum = nums[i];

            if (sum >= s) {
                return 1;
            }

            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= s) {
                    min = Math.min(min, j - i + 1);
                    break;
                }
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    public static int minSubArrayLen2(int s, int[] nums) throws Exception {
        // 滑动窗口，定义一个两个指针i和j，当nums[i]和nums[j]相加大于s的时候，i++,并减去nums[i]的值;否则j--,然后记录j - i + 1的值

        if (s == 0 || nums.length == 0) {
            return 0;
        }

        int i = 0;
        int j = 0;
        int res = Integer.MAX_VALUE;
        int sum = 0;
        while (j < nums.length) {
            sum += nums[j];
            while (sum >= s) {
                res = Math.min(res, j - i + 1);
                sum -= nums[i];
                i++;
            }

            j++;
        }

        return res == Integer.MAX_VALUE ? 0 : res;
    }

    /**
     * 示例：
     * 输入：s = 7, nums = [2,3,1,2,4,3]
     * 输出：2
     * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        int[] nums = {2, 3, 1, 2, 4, 3};
        int s = 9;
//        int[] nums = {1, 4, 4};
//        int s = 4;
        int res = Minimum_size_subarray_sum_209.minSubArrayLen2(s, nums);
        System.out.println(res);
    }
}
