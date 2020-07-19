package Leetcode.LinkedList;

/**
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * @author liuzy
 * @date 2020/7/19 18:00
 */
public class Merge_two_sorted_list_21 {

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 思路，遍历两个链表，使用指针指向两个链表的头，不断比较两个链表的节点的值，选择较小的一个值拼接到新的链表上去，并移动拼接的那个链表的指针位置

        ListNode prehead = new ListNode(-1);

        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
    }

    /**
     * 官方解法，递归
     */
    public static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists2(l1, l2.next);
            return l2;
        }
    }

    /*
    示例：
        输入：1->2->4, 1->3->4
        输出：1->1->2->3->4->4
     */
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode node1 = listNode1;
        node1.next = new ListNode(2);
        node1 = node1.next;
        node1.next = new ListNode(4);
        Utils.printNode(listNode1);

        ListNode listNode2 = new ListNode(1);
        ListNode node2 = listNode2;
        node2.next = new ListNode(3);
        node2 = node2.next;
        node2.next = new ListNode(4);
        Utils.printNode(listNode2);

        System.out.println("==================================");
        ListNode res = Merge_two_sorted_list_21.mergeTwoLists(listNode1, listNode2);
        Utils.printNode(res);
    }
}
