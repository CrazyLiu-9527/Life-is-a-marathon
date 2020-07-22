package Leetcode.LinkedList;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * @author liuzy
 * @date 2020/7/22 22:03
 */
public class Reorder_list_143 {

    /*
    示例 2:
        给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
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

        System.out.println("==============");
        Reorder_list_143.reorderList2(head);
        Utils.printNode(head);
    }

    public static void reorderList(ListNode head) {
        // 思路
        // 1.遍历链表得到最后一个节点
        // 2.定义指针指向第一个节点和最后一个节点，同时要记录这两个节点的前一个节点用作操作节点
        // 3.移动节点，重排指针位置
        // 这个思路可以做出题目，但是耗时太久
        // 这个思路可以优化成裁断两个链表，将第二个链表逆序，然后依次穿插

        if (head == null) {
            return;
        }

        // 定义两侧指针和其前一个节点
        ListNode preLeftNode = head;
        ListNode leftNode = head.next;
        ListNode preRightNode = null;
        ListNode rightNode = null;

        while (leftNode != rightNode) {
            preRightNode = head;
            rightNode = head.next;
            while (rightNode.next != null) {
                preRightNode = preRightNode.next;
                rightNode = rightNode.next;
            }

            if (rightNode == leftNode) { // 左右侧移动到同一节点，说明是奇数个节点，需要将该节点移动到链表尾部
                return;
            } else { // 否则还是要进行节点移动操作
                preRightNode.next = null;
                rightNode.next = leftNode;
                preLeftNode.next = rightNode;

                preLeftNode = leftNode;
                if (leftNode.next == null) {
                    return;
                } else {
                    leftNode = leftNode.next;
                }
            }
        }
    }

    public static void reorderList2(ListNode head) {
        // 思路，遍历一遍链表，用list存储数据，然后再用头尾指针取数据

        if (head == null) {
            return;
        }

        // 保存元素到list中,这里要取出单个节点
        List<ListNode> listNodeList = new ArrayList<>();
        while (head != null) {
            ListNode tmpNode = head;
            head = head.next;
            tmpNode.next = null;
            listNodeList.add(tmpNode);
        }

        // l->左指针，r->右指针
        int l = 0;
        int r = listNodeList.size() - 1;
        // 穿针引线
        while (l < r) {
            listNodeList.get(l).next = listNodeList.get(r);
            l++;
            //偶数个节点的情况，会提前相遇
            if (l == r) {
                break;
            }
            listNodeList.get(r).next = listNodeList.get(l);
            r--;
        }
    }

    /**
     * 厉害，想不出来
     *
     * @param head
     */
    public static void reorderList3(ListNode head) {
        // 使用递归
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        int len = 0;
        ListNode h = head;
        //求出节点数
        while (h != null) {
            len++;
            h = h.next;
        }

        reorderListHelper(head, len);
    }

    private static ListNode reorderListHelper(ListNode head, int len) {
        if (len == 1) {
            ListNode outTail = head.next;
            head.next = null;
            return outTail;
        }
        if (len == 2) {
            ListNode outTail = head.next.next;
            head.next.next = null;
            return outTail;
        }
        //得到对应的尾节点，并且将头结点和尾节点之间的链表通过递归处理
        ListNode tail = reorderListHelper(head.next, len - 2);
        ListNode subHead = head.next;//中间链表的头结点
        head.next = tail;
        ListNode outTail = tail.next;  //上一层 head 对应的 tail
        tail.next = subHead;
        return outTail;
    }
}
