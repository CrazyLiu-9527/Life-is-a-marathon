package Leetcode.Array;

import java.util.Arrays;

/**
 * leetcode 26
 * 给定一个有序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * @author liuzy
 * @date 2020/7/9 00:10
 */
public class Remove_Duplicates_From_Sorted_Array_26 {

    /**
     * 快慢指针
     * 时间复杂度O（n）
     *
     * @param nums
     * @return
     */
    public static int removeDuplicates2(int[] nums) {
        // 我的思路，定义一个索引k, [0, k)始终保持不重复且有序，然后循环数组，找到比k大的就进行交换
        if (nums.length == 0) {
            return 0;
        }
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > nums[k]) {
                k++;
                int tmp = nums[i];
                nums[i] = nums[k];
                nums[k] = tmp;
            }
        }
        return k + 1;
    }


    /**
     * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
     * <p>
     * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
     * <p>
     * 你不需要考虑数组中超出新长度后面的元素。
     *
     * @param args
     */
    public static void main(String[] args) {
//        int[] nums = {1, 1, 2};
//        int[] nums = {};
        int[] nums = {0, 0, 1, 2, 3, 3, 4, 15};
        int len = Remove_Duplicates_From_Sorted_Array_26.removeDuplicates2(nums);
        System.out.println(len);
        System.out.println(Arrays.toString(nums));
    }

}
