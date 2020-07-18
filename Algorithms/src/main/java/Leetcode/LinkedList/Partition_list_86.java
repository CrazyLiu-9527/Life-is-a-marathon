package Leetcode.LinkedList;

/**
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 * 你应当保留两个分区中每个节点的初始相对位置。
 *
 * @author liuzy
 * @date 2020/7/19 01:08
 */
public class Partition_list_86 {

    public static ListNode partition(ListNode head, int x) {
        // 思路，使用两个链表分别存储小于x的节点和大于等于x的节点，遍历链表将节点分别放在两个链表中，然后再合并链表

        // 用于保存不同node的链表
        ListNode beforeList = new ListNode(0);
        ListNode afterList = new ListNode(0);

        // 指向两个链表的虚拟头节点
        ListNode curBeforeNode = beforeList;
        ListNode curAfterNode = afterList;

        // 遍历链表
        while (head != null) {
            // 先取出当前这个节点
            ListNode cur = head;
            // 原链表指向下一个节点
            head = head.next;
            // 当前指针与head剥离
            cur.next = null;

            // 当前节点的值小于x时
            if (cur.val < x) {
                curBeforeNode.next = cur;
                curBeforeNode = curBeforeNode.next;
            } else {    // 当前节点值>=x时
                curAfterNode.next = cur;
                curAfterNode = curAfterNode.next;
            }
        }

        // 拼接链表
        curBeforeNode.next = afterList.next;
        return beforeList.next;
    }

    /*
    示例:
        输入: head = 1->4->3->2->5->2, x = 3
        输出: 1->2->2->4->3->5
     */
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node = head;
        node.next = new ListNode(5);
        node = node.next;
        node.next = new ListNode(2);
        node = node.next;
        node.next = new ListNode(3);
        node = node.next;
        node.next = new ListNode(4);

        ListNode cur = head;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }

        ListNode node2 = partition(head, 3);
        while (node2 != null) {
            System.out.println(node2.val);
            node2 = node2.next;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
