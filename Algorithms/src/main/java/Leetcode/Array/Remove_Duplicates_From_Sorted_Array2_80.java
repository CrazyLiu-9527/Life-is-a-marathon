package Leetcode.Array;

import java.util.Arrays;

/**
 * 给定一个有序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 *
 * @author liuzy
 * @date 2020/7/9 22:36
 */
public class Remove_Duplicates_From_Sorted_Array2_80 {


    /**
     * 官方解法
     * 记录要修改的值的个数，然后将大于2个的相同数量的值赋值为下一个值，依次往复
     *
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {

        // j指向下一个要被覆盖的元素，count表示重复元素出现的次数
        int j = 1, count = 1;

        // 遍历数组
        for (int i = 1; i < nums.length; i++) {

            // 一个数和前一个数相等的时候，count++
            if (nums[i] == nums[i - 1]) {
                count++;
            } else {
                count = 1;
            }

            // 精髓
            if (count <= 2) {
                nums[j] = nums[i];
                j++;
            }
        }
        return j;
    }

    /**
     * 给定 nums = [1,1,1,2,2,3],
     * 函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。
     * 你不需要考虑数组中超出新长度后面的元素。
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 2, 3};
        int res = Remove_Duplicates_From_Sorted_Array2_80.removeDuplicates(nums);
        System.out.println(res);
        System.out.println(Arrays.toString(nums));
    }
}
