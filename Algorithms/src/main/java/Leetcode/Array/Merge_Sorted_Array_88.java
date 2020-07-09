package Leetcode.Array;

import java.util.Arrays;

/**
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * 说明:
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 *
 * @author liuzy
 * @date 2020/7/9 23:41
 */
public class Merge_Sorted_Array_88 {

    /**
     * O((n+m)log(n+m))
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        // 思路，将nums2合并到nums1中，然后调用标准库对nums1排序
        for (int i = 0; i < nums2.length; i++) {
            nums1[m + i] = nums2[i];
        }

        // 上面的遍历可以使用更简单的写法
        // System.arraycopy(nums2, 0, nums1, m, n);

        Arrays.sort(nums1);

    }

    /**
     * 时间复杂度O(n+m)
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        // 思路，开辟一个新的数组，使用双指针p1,p2，分别指向两个数组最小的值，
        int[] nums1_copy = new int[m];
        System.arraycopy(nums1, 0, nums1_copy, 0, m);

        int p1 = 0;
        int p2 = 0;

        int p = 0;

        while ((p1 < m) && (p2 < n))
            nums1[p++] = (nums1_copy[p1] < nums2[p2]) ? nums1_copy[p1++] : nums2[p2++];

        if (p1 < m)
            System.arraycopy(nums1_copy, p1, nums1, p1 + p2, m + n - p1 - p2);
        if (p2 < n)
            System.arraycopy(nums2, p2, nums1, p1 + p2, m + n - p1 - p2);
    }

    /**
     * 输入:
     * nums1 = [1,2,3,0,0,0], m = 3
     * nums2 = [2,5,6],       n = 3
     * 输出: [1,2,2,3,5,6]
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 0, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        Merge_Sorted_Array_88.merge(nums1, 3, nums2, 3);
        System.out.println(Arrays.toString(nums1));
    }
}
