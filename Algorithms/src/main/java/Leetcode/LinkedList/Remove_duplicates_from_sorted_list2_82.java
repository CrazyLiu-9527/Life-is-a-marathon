package Leetcode.LinkedList;

import java.util.HashMap;

/**
 * 给定一个有序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 *
 * @author liuzy
 * @date 2020/7/19 16:58
 */
public class Remove_duplicates_from_sorted_list2_82 {

    public static ListNode deleteDuplicates(ListNode head) {
        // 思路，先遍历一遍，使用map记录次数，然后再遍历一遍，找到不是只出现一次的就删除

        // map记录每个值出现的次数
        HashMap<Integer, Integer> map = new HashMap<>();
        ListNode curNode = head;
        while (curNode != null) {
            if (map.containsKey(curNode.val)) {
                map.put(curNode.val, map.get(curNode.val) + 1);
            } else {
                map.put(curNode.val, 1);
            }
            curNode = curNode.next;
        }

        // 返回的链表的dummyHead
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        // 遍历链表
        ListNode pre = dummyHead;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;

            // 大于一，说明出现不止一次，则跳过元素
            if (map.get(cur.val) > 1) {
                pre.next = next;
                cur = next;
            } else { // 否则将pre和next向后移动一个单位
                pre = pre.next;
                cur = cur.next;
            }
        }

        return dummyHead.next;
    }

    public static ListNode deleteDuplicates2(ListNode head) {
        // 思路，当链表不为空的时候，如果出现当前节点和下一个节点值相等的情况，一直找到比当前节点的值大的那个一节点，直接删除中间所有节点

        // 设置dummyHead
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        // 使pre指向dummyHead, cur指向头节点，开始遍历
        ListNode pre = dummyHead;
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            ListNode next = cur.next;

            // 当cur和next的值相等时，一直向后找
            if (cur.val == next.val) {
                // 使next指向大于cur值的第一个节点
                while (next != null && cur.val == next.val) {
                    next = next.next;
                }
                // 调整节点位置
                cur = next;
                pre.next = cur;
            } else { // 不等时pre和next向后移动一个位置
                pre = pre.next;
                cur = cur.next;
            }

        }

        return dummyHead.next;
    }

    /*
    示例 1:
        输入: 1->2->3->3->4->4->5
        输出: 1->2->5
     */
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node = head;
        node.next = new ListNode(1);
        /*node = node.next;
        node.next = new ListNode(3);
        node = node.next;
        node.next = new ListNode(3);
        node = node.next;
        node.next = new ListNode(4);
        node = node.next;
        node.next = new ListNode(4);
        node = node.next;
        node.next = new ListNode(5);*/
        Utils.printNode(head);

        ListNode res = Remove_duplicates_from_sorted_list2_82.deleteDuplicates2(head);
        Utils.printNode(res);
    }
}
