package Leetcode.Array;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 *
 * @author liuzy
 * @date 2020/7/9 23:13
 */
public class Sort_Colors_75 {

    public static void sortColors(int[] nums) {
        // 使用map保存每种颜色的个数，然后遍历数组给数组赋值
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                int count = map.get(num) + 1;
                map.put(num, count);
            } else {
                map.put(num, 1);
            }
        }
        for (int i = 0; i < map.getOrDefault(0, 0); i++) {
            nums[i] = 0;
        }
        for (int i = map.getOrDefault(0, 0); i < map.getOrDefault(1, 0) + map.getOrDefault(0, 0); i++) {
            nums[i] = 1;
        }
        for (int i = map.getOrDefault(1, 0) + map.getOrDefault(0, 0); i < nums.length; i++) {
            nums[i] = 2;
        }

    }

    /**
     * 官方答案
     * 荷兰三色旗问题解
     */
    public void sortColors2(int[] nums) {
        // 对于所有 idx < i : nums[idx < i] = 0
        // j是当前考虑元素的下标
        int p0 = 0, curr = 0;
        // 对于所有 idx > k : nums[idx > k] = 2
        int p2 = nums.length - 1;

        int tmp;
        while (curr <= p2) {
            if (nums[curr] == 0) {
                // 交换第 p0个和第curr个元素
                // i++，j++
                tmp = nums[p0];
                nums[p0++] = nums[curr];
                nums[curr++] = tmp;
            } else if (nums[curr] == 2) {
                // 交换第k个和第curr个元素
                // p2--
                tmp = nums[curr];
                nums[curr] = nums[p2];
                nums[p2--] = tmp;
            } else curr++;
        }
    }

    /**
     * 输入: [2,0,2,1,1,0]
     * 输出: [0,0,1,1,2,2]
     *
     * @param args
     */
    public static void main(String[] args) {
//        int[] nums = {2, 0, 2, 1, 1, 0, 2, 1, 1};
        int[] nums = {1};
        Sort_Colors_75.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}
