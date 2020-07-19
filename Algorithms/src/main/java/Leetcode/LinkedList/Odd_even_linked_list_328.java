package Leetcode.LinkedList;

/**
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 *
 * @author liuzy
 * @date 2020/7/19 16:13
 */
public class Odd_even_linked_list_328 {

    public static ListNode oddEvenList(ListNode head) {
        // 思路，使用两个链表分别保存索引为奇数和偶数的节点，然后将两个链表串起来

        ListNode oddList = new ListNode(0);
        ListNode evenList = new ListNode(0);

        ListNode oddNode = oddList;
        ListNode evenNode = evenList;

        int i = 0;
        while (head != null) {
            // 当前要操作的节点指向head节点
            ListNode cur = head;
            // head指向head的下一个节点
            head = head.next;
            // 当前要操作的节点取出来
            cur.next = null;

            // 偶数
            if (i % 2 == 0) {
                oddNode.next = cur;
                oddNode = oddNode.next;
            } else { // 奇数
                evenNode.next = cur;
                evenNode = evenNode.next;
            }

            i++;
        }

        oddNode.next = evenList.next;
        return oddList.next;
    }

    /*
    示例 2:

        输入: 2->1->3->5->6->4->7->NULL
        输出: 2->3->6->7->1->5->4->NULL
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

        ListNode node2 = oddEvenList(head);
        while (node2 != null) {
            System.out.println(node2.val);
            node2 = node2.next;
        }
    }
}
