package Recursion.LinkedListProblemsInLeetcode01;

/**
 * leetcode 203 题解
 *
 * @author liuzy
 * @date 2020/6/16 22:56
 */
public class Solution {
    public ListNode removeElements(ListNode head, int val) {

        //首个元素就和传入的值相等的情况
        while (head != null && head.val == val) {
            ListNode delNode = head;
            head = head.next;
            delNode.next = null;
        }

        if (head == null) {
            return head;
        }

        //相等的值在中间的情况
        ListNode prev = head;
        while (prev.next != null) {
            if (prev.next.val == val) {
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
            } else {
                prev = prev.next;
            }
        }

        return head;
    }
}
