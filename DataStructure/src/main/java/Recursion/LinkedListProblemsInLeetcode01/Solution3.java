package Recursion.LinkedListProblemsInLeetcode01;

/**
 * 使用dummyHead实现题解，可以不用考虑特殊情况
 *
 * @author liuzy
 * @date 2020/6/16 22:56
 */
public class Solution3 {
    public ListNode removeElements(ListNode head, int val) {

        //任意创建一个虚拟头节点
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.val == val)
                prev.next = prev.next.next;
            else
                prev = prev.next;
        }

        return dummyHead.next;
    }
}
