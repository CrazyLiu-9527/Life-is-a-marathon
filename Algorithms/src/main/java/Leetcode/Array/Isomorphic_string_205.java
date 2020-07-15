package Leetcode.Array;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
 *
 * @author liuzy
 * @date 2020/7/15 23:45
 */
public class Isomorphic_string_205 {

    public static boolean isIsomorphic(String s, String t) {
        // 思路同290题

        char[] s_char = s.toCharArray();
        char[] t_char = t.toCharArray();

        if (s_char.length != t_char.length) {
            return false;
        }
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s_char.length; i++) {
            if (map.containsKey(s_char[i])) {
                if (map.get(s_char[i]) != t_char[i]) {
                    return false;
                }
            } else {
                if (map.containsValue(t_char[i])) {
                    return false;
                }
                map.put(s_char[i], t_char[i]);
            }
        }

        return true;
    }

    /**
     * 示例 1:
     * 输入: s = "egg", t = "add"
     * 输出: true
     * 示例 2:
     * 输入: s = "foo", t = "bar"
     * 输出: false
     *
     * @param args
     */
    public static void main(String[] args) {
//        String s = "egg";
//        String t = "add";

        String s = "foo";
        String t = "bar";
        boolean res = Isomorphic_string_205.isIsomorphic(s, t);
        System.out.println(res);
    }
}
