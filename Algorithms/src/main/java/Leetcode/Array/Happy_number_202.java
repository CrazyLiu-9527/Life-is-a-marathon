package Leetcode.Array;

/**
 * 编写一个算法来判断一个数 n 是不是快乐数。
 * 「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。如果 可以变为  1，那么这个数就是快乐数。
 * 如果 n 是快乐数就返回 True ；不是，则返回 False 。
 *
 * @author liuzy
 * @date 2020/7/15 21:49
 */
public class Happy_number_202 {

    /**
     * 示例：
     * 输入：19
     * 输出：true
     * 解释：
     * 1^2 + 9^2 = 82
     * 8^2 + 2^2 = 68
     * 6^2 + 8^2 = 100
     * 1^2 + 0^2 + 0^2 = 1
     *
     * @param args
     */
    public static void main(String[] args) {

    }

    public int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    /**
     * 标准题解，快慢指针，判断是否有环
     *
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        int slowRunner = n;
        int fastRunner = getNext(n);
        while (fastRunner != 1 && slowRunner != fastRunner) {
            slowRunner = getNext(slowRunner);
            fastRunner = getNext(getNext(fastRunner));
        }
        return fastRunner == 1;
    }
}
