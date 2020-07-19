package Leetcode.LinkedList;


/**
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 *
 * @author liuzy
 * @date 2020/7/19 00:32
 */
public class Reverse_linked_list2_92 {

    public static ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode superior = dummyHead;

        // 1. 遍历至m的前一个节点
        for (int i = 1; i < m; i++) {
            superior = superior.next;
        }

        ListNode prev = null;
        ListNode cur = superior.next;

        // 2. 180°旋转爆炸
        for (int i = 0; i <= n - m; i++) {
            ListNode next = cur.next;

            cur.next = prev;
            prev = cur;
            cur = next;
        }

        // 3. 修改m和n-m位置处的节点的指向
        superior.next.next = cur;
        superior.next = prev;
        return dummyHead.next;
    }

    /*
    说明:
        1 ≤ m ≤ n ≤ 链表长度。

        示例:

        输入: 1->2->3->4->5->NULL, m = 2, n = 4
        输出: 1->4->3->2->5->NULL
     */
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode cur = head;
        for (int i = 2; i < 6; i++) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }

        ListNode node = head;
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }

        ListNode node2 = reverseBetween(head, 2, 4);
        while (node2 != null) {
            System.out.println(node2.val);
            node2 = node2.next;
        }
    }
}
