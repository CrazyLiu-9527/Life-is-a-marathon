package Leetcode.LinkedList;

/**
 * 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。
 *
 * @author liuzy
 * @date 2020/7/21 00:01
 */
public class Delete_node_in_liked_list_237 {

    public static void deleteNode(ListNode node) {
        // 这道题只给了单个节点node，没有给链表head，换一种思路，可以先改变node的值，然后改变node的指向
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /*
    示例 1:
        输入: head = [4,5,1,9], node = 5
        输出: [4,1,9]
        解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.

    说明:
        链表至少包含两个节点。
        链表中所有节点的值都是唯一的。
        给定的节点为非末尾节点并且一定是链表中的一个有效节点。
        不要从你的函数中返回任何结果。
     */
    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        ListNode node = head;
        node.next = new ListNode(5);
        node = node.next;
        node.next = new ListNode(1);
        node = node.next;
        node.next = new ListNode(9);
        Utils.printNode(head);

        System.out.println("==============");
        Delete_node_in_liked_list_237.deleteNode(head);
        System.out.println(head);
    }
}
