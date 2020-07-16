package Leetcode.LinkedList;

import java.util.Stack;

/**
 * @author liuzy
 * @date 2020/7/16 23:32
 */
public class Reverse_linked_list_206 {

    // todo
    public static ListNode reverseList(ListNode head) {
        // 使用栈，将链表元素一个个放置进去，然后再取出来
        Stack<ListNode> stack = new Stack<>();
        ListNode pushNode = head;
        while (pushNode != null) {
            stack.push(pushNode);
            pushNode = pushNode.next;
        }

        ListNode res = stack.pop();
        ListNode popNode = res;
        while (stack.size() > 0) {
            popNode.next = stack.pop();
            popNode = popNode.next;
        }

        return res;
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
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
