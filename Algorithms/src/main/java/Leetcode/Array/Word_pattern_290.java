package Leetcode.Array;

import java.util.HashMap;

/**
 * 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 *
 * @author liuzy
 * @date 2020/7/15 22:05
 */
public class Word_pattern_290 {

    public static boolean wordPattern(String pattern, String str) {
        // 思路，使用map保存pattern中每个字符对应的str中的每个字符串
        char[] patterns = pattern.toCharArray();
        String[] strArr = str.split(" ");

        // 长度不等，直接返回false
        if (patterns.length != strArr.length) {
            return false;
        }

        HashMap<Character, String> map = new HashMap<>();
        for (int i = 0; i < patterns.length; i++) {
            if (map.containsKey(patterns[i])) {
                if (!map.get(patterns[i]).equals(strArr[i])) {
                    return false;
                }
            } else {
                //判断 value 中是否存在  很重要
                // 如果不包含key,但是包含value,说明不同的key对应了同一个value
                if (map.containsValue(strArr[i])) {
                    return false;
                }
                map.put(patterns[i], strArr[i]);
            }
        }

        return true;
    }

    /**
     * 示例1:
     * 输入: pattern = "abba", str = "dog cat cat dog"
     * 输出: true
     * 示例 2:
     * 输入:pattern = "abba", str = "dog cat cat fish"
     * 输出: false
     * 示例 3:
     * 输入: pattern = "aaaa", str = "dog cat cat dog"
     * 输出: false
     *
     * @param args
     */
    public static void main(String[] args) {
//        String pattern = "abb";
//        String str = "dog cat cat";

//        String pattern = "aaaa";
//        String str = "dog cat cat dog";

        String pattern = "abba";
        String str = "dog dog dog dog";

        boolean res = Word_pattern_290.wordPattern(pattern, str);
        System.out.println(res);
    }
}
