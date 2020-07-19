package Leetcode.LinkedList;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author liuzy
 * @date 2020/7/16 23:32
 */
public class Reverse_linked_list_206 {

    public static ListNode reverseList(ListNode head) {
        // 使用栈，将链表元素一个个放置进去，然后再取出来
        Deque<ListNode> deque = new LinkedList<>();
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = null;
            deque.push(cur);
            cur = next;
        }

        ListNode res = new ListNode(0);
        ListNode tmpNode = res;
        for (ListNode node : deque) {
            tmpNode.next = node;
            tmpNode = tmpNode.next;
        }

        return res.next;
    }

    /*
    示例:
        输入: 1->2->3->4->5->NULL
        输出: 5->4->3->2->1->NULL
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

        ListNode node2 = Reverse_linked_list_206.reverseList(head);
        while (node2 != null) {
            System.out.println(node2.val);
            node2 = node2.next;
        }
    }


    /**
     * 官方解法
     *
     * @param head
     * @return
     */
    public static ListNode reverseList2(ListNode head) {

        // null节点
        ListNode prev = null;
        // curr指向head
        ListNode curr = head;
        while (curr != null) {
            // 保存当前节点的下一个节点
            ListNode next = curr.next;
            // 当前节点指向前一个节点
            curr.next = prev;
            // 前一个节点指向当前节点
            prev = curr;
            // 当前节点指向next节点
            curr = next;
        }
        return prev;
    }
}
