package Leetcode.Array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * @author liuzy
 * @date 2020/7/15 21:32
 */
public class Valid_anagram_242 {

    public static boolean isAnagram(String s, String t) {
        // 思路，将s转换成char数组，然后放进map中，再判断t的字符是否包含在map中并且出现次数一致
        if (s.length() != t.length()) {
            return false;
        }
        // s转换成char数组
        char[] s_char = s.toCharArray();
        // 保存每个字符出现的次数
        Map<Character, Integer> map = new HashMap<>();

        // 遍历保存s中每个字符出现的次数
        for (char c : s_char) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        // 遍历查看t中的自读是否都在map中出现过
        char[] t_chars = t.toCharArray();
        for (char c : t_chars) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);
                if (map.get(c) == 0) {
                    map.remove(c);
                }
            } else {
                return false;
            }
        }

        return map.size() == 0;

    }

    /**
     * 示例 1:
     * 输入: s = "anagram", t = "nagaram"
     * 输出: true
     *
     * @param args
     */
    public static void main(String[] args) {
//        String s = "anagram";
//        String t = "nagaram";
        String s = "cat";
        String t = "car";

        boolean res = Valid_anagram_242.isAnagram(s, t);
        System.out.println(res);
    }

    /**
     * 排序算法
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }
}
