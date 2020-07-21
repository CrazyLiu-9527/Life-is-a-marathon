package Leetcode.LinkedList;

/**
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 *
 * @author liuzy
 * @date 2020/7/21 23:12
 */
public class Rotate_list_61 {

    /*
    示例 1:

        输入: 1->2->3->4->5->NULL, k = 2
        输出: 4->5->1->2->3->NULL
        解释:
        向右旋转 1 步: 5->1->2->3->4->NULL
        向右旋转 2 步: 4->5->1->2->3->NULL
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

        System.out.println("==================");
        ListNode res = Rotate_list_61.rotateRight2(head, 0);
        Utils.printNode(res);

    }

    public static ListNode rotateRight(ListNode head, int k) {
        // 思路，先遍历链表得到长度，并对k取余，取余结果表示最终要移动n个位置
        // 将链表分为两段，将最后n个元素的链表尾部指向前面一段元素的头，完成

        // 边界情况优先考虑
        if (head == null || k < 0) {
            return null;
        }

        // 使用dummyHead
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        // 定位最后一个节点，等下需要使用这个节点进行连接
        ListNode lastNode = dummyHead;

        // 得出链表长度
        int len = 0;
        while (head != null) {
            head = head.next;
            lastNode = lastNode.next;
            len++;
        }

        // 计算出最终移动的位置n
        int n = k % len;

        // 如果n=0，就是相对不移动位置，直接返回链表
        if (n == 0) {
            return dummyHead.next;
        }

        // 分割为两个链表，定位分割出来前一个链表的最后一个节点和后一个链表的第一个节点
        ListNode beforeLastNode = dummyHead;
        ListNode afterFirstNode = dummyHead.next;

        // 遍历定位元素位置
        int circleNum = len - n;
        while (circleNum != 0) {
            beforeLastNode = beforeLastNode.next;
            afterFirstNode = afterFirstNode.next;
            circleNum--;
        }

        // 连接两个链表
        lastNode.next = dummyHead.next;
        beforeLastNode.next = null;
        dummyHead = null;

        return afterFirstNode;
    }

    public static ListNode rotateRight2(ListNode head, int k) {
        // 另一种思路，先将链表闭合成环，然后再找到合适的位置断开

        // 边界情况优先考虑
        if (head == null || k < 0) {
            return null;
        }

        // 使用dummyHead指向链表头，将链表闭合成环
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        // 然后遍历一遍链表，将原链表首尾连接起来
        int len = 1;
        while (head.next != null) {
            head = head.next;
            len++;
        }
        head.next = dummyHead.next;

        // 然后再遍历一次链表，直到k==0，找到断开位置的节点
        int n = len - k % len;
        ListNode splitNode = dummyHead;
        while (n != 0) {
            splitNode = splitNode.next;
            n--;
        }

        // 定位断开节点后一个节点，也就是新链表的头
        ListNode resNode = splitNode.next;
        // 将环断开
        splitNode.next = null;

        return resNode;
    }
}
