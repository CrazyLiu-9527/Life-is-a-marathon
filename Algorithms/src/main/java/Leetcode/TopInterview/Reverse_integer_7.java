package Leetcode.TopInterview;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * @author liuzy
 * @date 2020/7/24 22:31
 */
public class Reverse_integer_7 {

    /*
    示例 1:
        输入: 123
        输出: 321
    示例 2:
        输入: -123
        输出: -321
     */
    public static void main(String[] args) {
        int res = Reverse_integer_7.reverse(-3210);
        System.out.println(res);
    }

    public static int reverse(int x) {
        int res = 0;

        // 每次取余拿到最后一位数字
        while (x != 0) {
            int tmp = x % 10;
            res = res * 10 + tmp;
            x = x / 10;
        }

        return res;
    }

    // 这个才是正解
    public static int reverse2(int x) {
        int res = 0;

        // 每次取余拿到最后一位数字
        while (x != 0) {
            int tmp = x % 10;
            //判断是否 大于 最大32位整数
            if (res > 214748364 || (res == 214748364 && tmp > 7)) {
                return 0;
            }
            //判断是否 小于 最小32位整数
            if (res < -214748364 || (res == -214748364 && tmp < -8)) {
                return 0;
            }
            res = res * 10 + tmp;
            x = x / 10;
        }

        return res;
    }


}
