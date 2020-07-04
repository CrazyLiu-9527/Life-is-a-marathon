package Recursion.LinkedListProblemsInLeetcode01;

/**
 * 简化solution的代码
 *
 * @author liuzy
 * @date 2020/6/16 22:56
 */
public class Solution2 {
    public ListNode removeElements(ListNode head, int val) {

        while (head != null && head.val == val) {
            head = head.next;
        }

        if (head == null) {
            return head;
        }

        ListNode prev = head;
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }

        return head;
    }
}
