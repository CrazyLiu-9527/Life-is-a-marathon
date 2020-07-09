package Leetcode.Array;

import java.util.Arrays;

/**
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 * @author liuzy
 * @date 2020/7/9 21:59
 */
public class Remove_Elements_27 {


    public static int removeElement(int[] nums, int val) {
        // 我的思路，定义两个指针i和j，分别指向第一个数字和最后一个，移动i，当nums[i]==val的时候交换i和j位置的元素，然后再让j--，最后返回i+1
        int i = 0;
        int j = nums.length;
        while (i < j) {
            if (nums[i] == val) {
                nums[i] = nums[j - 1];
                j--;
            } else {
                i++;
            }
        }

        return j;
    }

    // 官方解法1,定义快慢指针
    public static int removeElement2(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }


    /**
     * 给定 nums = [0,1,2,2,3,0,4,2], val = 2,
     * 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
     * 注意这五个元素可为任意顺序。
     * 你不需要考虑数组中超出新长度后面的元素。
     *
     * @param args
     */
    public static void main(String[] args) {
//        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
        int[] nums = {1, 2, 4, 2, 6, 7, 2, 1};
//        int[] nums = {2};
        int res = Remove_Elements_27.removeElement2(nums, 2);
        System.out.println(res);
        System.out.println(Arrays.toString(nums));
    }
}
