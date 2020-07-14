package Leetcode.Array;

import java.util.HashMap;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * @author liuzy
 * @date 2020/7/14 22:24
 */
public class Longest_substring_without_repeating_characters_3 {


    public static int lengthOfLongestSubstring(String s) {
        // 思路，滑动窗口+set
        if (s.length() == 0) return 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - left + 1);
        }
        return max;

    }

    /**
     * 示例 1:
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     *
     * @param args
     */
    public static void main(String[] args) {
//        String s = "abcagatbcdbb";
        String s = "pwwkew";
        int res = Longest_substring_without_repeating_characters_3.lengthOfLongestSubstring(s);
        System.out.println(res);
    }
}
