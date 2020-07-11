package Leetcode.Array;

/**
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * <p>
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 * @author liuzy
 * @date 2020/7/11 10:11
 */
public class Valid_Palindrome_125 {


    public static boolean isPalindrome(String str) {
        // 思路：对撞指针，定义i和j指向字符串的首尾

        StringBuffer sgood = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                sgood.append(Character.toLowerCase(ch));
            }
        }

        str = sgood.toString().toLowerCase();
        int i = 0;
        int j = sgood.length() - 1;

        while (i < j) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }

    public static boolean isPalindrome2(String str) {
        StringBuffer sgood = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                sgood.append(Character.toLowerCase(ch));
            }
        }

        str = sgood.toString().toLowerCase();

        int len = str.length();
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(len - i - 1)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 示例 1:
     * 输入: "A man, a plan, a canal: Panama"
     * 输出: true
     * 示例 2:
     * 输入: "race a car"
     * 输出: false
     *
     * @param args
     */
    public static void main(String[] args) {
        String str = "Amanapl,ana canalPa.nama";
        boolean res = Valid_Palindrome_125.isPalindrome(str);
        System.out.println(res);
    }
}
