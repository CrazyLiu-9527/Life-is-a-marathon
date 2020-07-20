package Leetcode.LinkedList;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * @author liuzy
 * @date 2020/7/19 18:24
 */
public class Swap_nodes_in_pairs_24 {

    /*
    示例:
        给定 1->2->3->4, 你应该返回 2->1->4->3.
     */
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node = head;
        node.next = new ListNode(2);
        node = node.next;
        node.next = new ListNode(3);
        node = node.next;
        node.next = new ListNode(4);
        Utils.printNode(head);

        System.out.println("=================");
        ListNode res = Swap_nodes_in_pairs_24.swapPairs(head);
        Utils.printNode(res);
    }

    public static ListNode swapPairs(ListNode head) {
        // 思路，记录操作节点(cur)和操作节点前后两个节点(pre,next)，然后移动指针，并不断往后进行遍历，直到链表节点为空

        // 使用虚拟头节点指向head
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        // 指定pre，cur和next的初始位置
        ListNode pre = dummyHead;
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            ListNode next = cur.next;

            // 交换元素位置
            pre.next = next;
            cur.next = next.next;
            next.next = cur;

            // 移动pre和cur的指向
            pre = cur;
            cur = cur.next;
        }

        return dummyHead.next;
    }

    /**
     * 官方题解，递归做法
     * 厉害
     *
     * @param head
     * @return
     */
    public static ListNode swapPairs2(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode firstNode = head;
        ListNode secondNode = head.next;

        firstNode.next = swapPairs2(secondNode.next);
        secondNode.next = firstNode;

        return secondNode;
    }

}
