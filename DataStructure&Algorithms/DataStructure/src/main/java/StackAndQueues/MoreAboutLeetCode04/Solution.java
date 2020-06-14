package StackAndQueues.MoreAboutLeetCode04;


import java.util.Stack;

/**
 * leetcode题解
 * 20.有效的括号
 *
 * @author liuzy
 * @date 2020/6/14 22:20
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().isValid("[[]]()()"));

        System.out.println(new Solution().isValid("[[]](()"));
    }

    public boolean isValid(String s) {
        ArrayStack<Character> stack = new ArrayStack<>(10);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                char topChar = stack.pop();
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

        return stack.isEmpty();
    }
}
