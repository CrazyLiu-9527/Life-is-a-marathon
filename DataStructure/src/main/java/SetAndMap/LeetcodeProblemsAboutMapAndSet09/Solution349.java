package SetAndMap.LeetcodeProblemsAboutMapAndSet09;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * leetcode 349
 *
 * @author liuzy
 * @date 2020/6/23 22:44
 */
public class Solution349 {
    public static int[] intersection(int[] nums1, int[] nums2) {

        TreeSet<Integer> set1 = new TreeSet<>();
        for (int num : nums1)
            set1.add(num);

        ArrayList<Integer> list = new ArrayList<>();
        for (int i : nums2) {
            if (set1.contains(i)) {
                list.add(i);
                set1.remove(i);
            }
        }

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] res = Solution349.intersection(new int[]{1, 2, 3, 2, 1}, new int[]{3, 2, 4, 5, 1});
        for (int i : res) {
            System.out.println(i);
        }
    }
}
