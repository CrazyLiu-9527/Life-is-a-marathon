package Leetcode.LinkedList;

/**
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 *
 * @author liuzy
 * @date 2020/7/19 00:45
 */
public class Remove_duplicates_from_sorted_list_83 {

    public static ListNode deleteDuplicates(ListNode head) {
        // 当前节点指向头节点
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            // 定位下一个节点
            ListNode next = cur.next;

            // 当前节点和下一个节点的值相等
            if (cur.val == next.val) {
                // 定位要删除的元素
                ListNode del = next;
                // next指向next的下一个节点
                next = next.next;
                // 当前节点的下一个节点指向next节点
                cur.next = next;
                // 释放del节点
                del = null;

                // 移动当前节点和下一个节点，进行下一轮比较
                // 不能移动，因为当前cur.next已经指向新值了
//                cur = next;
//                next = next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

    /*
    示例 2:

        输入: 1->1->2->3->3
        输出: 1->2->3
     */
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node = head;
        node.next = new ListNode(1);
        node = node.next;
        node.next = new ListNode(2);
        node = node.next;
        node.next = new ListNode(3);
        node = node.next;
        node.next = new ListNode(3);

        ListNode cur = head;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }

        ListNode node2 = Remove_duplicates_from_sorted_list_83.deleteDuplicates(head);
        while (node2 != null) {
            System.out.println(node2.val);
            node2 = node2.next;
        }
    }
}
