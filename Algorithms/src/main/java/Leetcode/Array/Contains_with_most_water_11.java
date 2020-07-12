package Leetcode.Array;

/**
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 * @author liuzy
 * @date 2020/7/12 12:18
 */
public class Contains_with_most_water_11 {

    public static int maxArea(int[] height) {
        // 思路，定义双指针分别指向数组头和尾，然后计算,并移动高度比较短的那一条
        // 虽然这样会漏掉一些情况，但是参考计算公式 n^2 > (n+1)*(n-1) > (n+2)*(n-2) > (n+2)*(n-2) ...,漏掉的总不是最大面积的
        int l = 0;
        int r = height.length - 1;

        int res = 0;

        while (l < r) {
            // 当前的面积
            int cur = Math.min(height[l], height[r]) * (r - l);
            res = Math.max(cur, res);

            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }

        return res;
    }

    /**
     * 例子详见leetcode
     * 输入：[1,8,6,2,5,4,8,3,7]
     * 输出：49
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] nums = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int res = Contains_with_most_water_11.maxArea(nums);
        System.out.println(res);
    }
}
