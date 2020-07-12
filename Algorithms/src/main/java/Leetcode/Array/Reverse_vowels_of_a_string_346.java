package Leetcode.Array;

/**
 * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
 * 首尾的元音交换
 *
 * @author liuzy
 * @date 2020/7/12 11:55
 */
public class Reverse_vowels_of_a_string_346 {

    public static String reverseVowels(String s) {
        // 定义首尾指针，分别判断是否是元音字符，如果是，就交换位置，如果不是就接着往中间靠拢
        char[] chars = s.toCharArray();
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            while (i < s.length() && !isVowels(chars[i])) {
                i++;
            }
            while (j >= 0 && !isVowels(chars[i])) {
                j--;
            }

            // 如果没有元音
            if (j >= i) {
                break;
            }

            char tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;

            i++;
            j--;
        }
        return new String(chars);
    }

    public static boolean isVowels(char c) {
        char[] vowels = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        for (char vowel : vowels) {
            if (vowel == c) {
                return true;
            }
        }

        return false;
    }

    /**
     * 示例 1:
     * 输入: "hello"
     * 输出: "holle"
     *
     * @param args
     */
    public static void main(String[] args) {
        String str = "leetcode";
        Reverse_vowels_of_a_string_346.reverseVowels(str);
        System.out.println(str);
    }
}
