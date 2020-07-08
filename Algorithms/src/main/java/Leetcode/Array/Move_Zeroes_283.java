package Leetcode.Array;

import java.util.Arrays;

/**
 * leetcode 283
 *
 * @author zhiyuanliu
 * @date 2020/7/8 20:35
 */
public class Move_Zeroes_283 {

    /**
     * 快慢指针
     * 时间复杂度O(n)
     */
    public static void moveZeroes(int[] nums) {
        // 我的思路，k指向第一个为0的数，i指向第一个非0的数，遍历并不断的给i和k重新赋值，交换这两个值的位置，使[0,k)始终为大于0的数
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[k] != 0) {
                k++;
            }
            if (nums[i] == 0) {
                i++;
            }
            if (i < nums.length && k < nums.length && i > k) {
                int tmp = nums[i];
                nums[i] = nums[k];
                nums[k] = tmp;
            }
        }
    }

    /**
     * 时间复杂度O(n)
     */
    public static void moveZeroes2(int[] nums) {
        // 我的思路，k指向第一个为0的数，i指向第一个非0的数，遍历并不断的给i和k重新赋值，交换这两个值的位置，使[0,k)始终为大于0的数
        // 还是上面这个思路，只判断可以交换的情况
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                // 交换两个数的值
                int tmp = nums[i];
                nums[i] = nums[k];
                nums[k] = tmp;

                // k的指向向后移动一位
                k++;
            }
        }
    }

    /**
     * 时间复杂度O(n)
     */
    public static void moveZeroes3(int[] nums) {
        // 我的思路，将不为0的值全部挪到前面，后面的值全部赋值为0，[0, k)表示为0的值
        //第一次遍历将不为0的元素全部挪到前面
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[k] = nums[i];
                k++;
            }
        }

        // 第二次遍历将后面的值全部赋值为0
        for (int i = k; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    /**
     * 时间复杂度O(n^2)
     */
    public static void moveZeroes4(int[] nums) {
        // 我的思路，双重遍历，第一重i寻找为0的值，第二重j寻找比i大的最近的一个非0元素值，交换位置
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[i] == 0 && nums[j] != 0 && j > i) {
                    // 交换两个数的值
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;
                }
            }
        }
    }


    /**
     * 说明:
     * 必须在原数组上操作，不能拷贝额外的数组。
     * 尽量减少操作次数。
     */
    public static void main(String[] args) {
//        int[] nums = {1, 0};
//        int[] nums = {};
        int[] nums = {1, 2, 0, 4, 0, 15};
        Move_Zeroes_283.moveZeroes4(nums);
        System.out.println(Arrays.toString(nums));
    }
}
