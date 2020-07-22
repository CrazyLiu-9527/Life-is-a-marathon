package Leetcode.StackAndQueue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * @author liuzy
 * @date 2020/7/22 23:16
 */
public class Valid_parentheses_20 {

    /*
    示例 1:
        输入: "()"
        输出: true
    示例 2:
        输入: "()[]{}"
        输出: true
    示例 3:
        输入: "(]"
        输出: false
    示例 4:
        输入: "([)]"
        输出: false
    示例 5:
        输入: "{[]}"
        输出: true
     */
    public static void main(String[] args) {
        String str = "([])";
        boolean res = Valid_parentheses_20.isValid(str);
        System.out.println(res);
    }

    public static boolean isValid(String s) {
        // 将每个字符推入栈中，然后依次取出进行匹配
        Deque<Character> deque = new LinkedList<>();


        char[] chars = s.toCharArray();

        // 如果char数组的长度是奇数，可以直接返回false;
        if (chars.length % 2 == 1) {
            return false;
        }

        // 将字符推入栈中
        for (char c : chars) {

            // 如果
            if ('{' == c || '[' == c || '(' == c) {
                deque.push(c);
            } else {

                if (deque.isEmpty()) {
                    return false;
                }

                char topChar = deque.pop();
                if (c == ')' && topChar != '(') {
                    return false;
                }
                if (c == '}' && topChar != '{') {
                    return false;
                }
                if (c == ']' && topChar != '[') {
                    return false;
                }
            }
        }
        return deque.isEmpty();
    }

}
