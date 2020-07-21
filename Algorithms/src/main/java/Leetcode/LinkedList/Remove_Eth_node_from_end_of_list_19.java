package Leetcode.LinkedList;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * @author liuzy
 * @date 2020/7/21 22:31
 */
public class Remove_Eth_node_from_end_of_list_19 {

    /*
    示例：
        给定一个链表: 1->2->3->4->5, 和 n = 2.
        当删除了倒数第二个节点后，链表变为 1->2->3->5.
     */
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node = head;
        node.next = new ListNode(2);
        node = node.next;
        node.next = new ListNode(3);
        node = node.next;
        node.next = new ListNode(4);
        node = node.next;
        node.next = new ListNode(5);
        Utils.printNode(head);

        ListNode res = Remove_Eth_node_from_end_of_list_19.removeNthFromEnd2(head, 2);
        Utils.printNode(res);
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        // 思路，先遍历一遍链表得出链表长度，然后算出要删除的是正数第几个元素
        // 再遍历一遍链表，删除元素，显然这不是最优解

        // 边界情况优先考虑
        if (head == null || n <= 0) {
            return null;
        }

        // 使用dummyHead
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        // 得出链表长度
        int len = 0;
        while (head != null) {
            head = head.next;
            len++;
        }

        // 链表长度len没有要移出的n大的时候，返回null
        if (len < n) {
            return null;
        }

        // 计算出是正数第几个元素
        int index = len - n;

        // 初始化指针指向
        int i = 0;
        ListNode cur = dummyHead.next;
        ListNode pre = dummyHead;
        while (cur != null) {

            if (index == i) { // 定位到节点，执行删除操作
                pre.next = cur.next;
                cur.next = null;
                break;
            } else { // 未定位到节点，继续往下遍历
                i++;
                pre = pre.next;
                cur = cur.next;
            }
        }

        return dummyHead.next;
    }

    public static ListNode removeNthFromEnd2(ListNode head, int n) {
        // 思路，定义两个指针，两个指针间距是n，当前一个指针走到尾部的时候，后一个指针定位到的就是要删除的那个元素

        // 边界情况优先考虑
        if (head == null || n <= 0) {
            return null;
        }

        // 使用dummyHead
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        // 定义慢指针指向head，定义快指针指向head后面的第n个元素
        ListNode slowNode = dummyHead.next;
        ListNode fastNode = dummyHead.next;
        while (n != 0) {
            fastNode = fastNode.next;
            n--;
        }

        // 定义慢指针前一个位置的节点，好等下删除的时候直接使用
        ListNode preSlowNode = dummyHead;

        // 遍历链表，当快指针指向null时停止
        while (fastNode != null) {
            preSlowNode = preSlowNode.next;
            slowNode = slowNode.next;
            fastNode = fastNode.next;
        }

        // 此时可以执行删除操作
        preSlowNode.next = slowNode.next;
        // 释放删除节点
        slowNode = null;

        return dummyHead.next;
    }
}
