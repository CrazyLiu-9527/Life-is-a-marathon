package Leetcode.LinkedList;

import java.util.ArrayList;
import java.util.List;

/**
 * 请判断一个链表是否为回文链表。
 *
 * @author liuzy
 * @date 2020/7/22 22:55
 */
public class Palindrome_linked_list_234 {

    /*
    示例 1:
        输入: 1->2
        输出: false
    示例 2:
        输入: 1->2->2->1
        输出: true
     */
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node = head;
        node.next = new ListNode(2);
        node = node.next;
        node.next = new ListNode(3);
        node = node.next;
        node.next = new ListNode(2);
        node = node.next;
        node.next = new ListNode(1);
        Utils.printNode(head);

        System.out.println("============");
        boolean res = Palindrome_linked_list_234.isPalindrome(head);
        System.out.println(res);
    }

    public static boolean isPalindrome(ListNode head) {
        // 接143题的思路，可以放弃暴力遍历的解法，将链表放在一个list中，然后使用指针对撞

        if (head == null) {
            return true;
        }

        // 将节点放置list中
        List<ListNode> list = new ArrayList<>();
        while (head != null) {
            ListNode tmpNode = head;
            head = head.next;
            tmpNode.next = null;
            list.add(tmpNode);
        }

        int l = 0;
        int r = list.size() - 1;
        while (l < r) {
            if (list.get(l).val == list.get(r).val) {
                l++;
                r--;
            } else {
                return false;
            }
        }

        return true;
    }

    public static boolean isPalindrome2(ListNode head) {
        /*
        1 找到中点位置，从这里开始反转后半段链表
        2 再次找到中点位置，中点与head开始进行对比，中途有不相同的返回false，否则最后返回true
        tips：注意这道题不同于返回中间节点，所以slow从dummy开始，而fast从head开始，也就是slow.next指向的是中点位置，为什么要这样做，是为了将链表从中点断开，也就是slow.next=null;
        这样做可以不用担心链表节点数是偶数还是奇数。
         */

        //找到中点位置，从这里开始反转后半段链表
        //再次找到中点位置，中点与head开始进行对比，中途有不相同的返回false，否则最后返回true
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = dummy;//遍历结束时slow指向的是mid的前一个
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode head2 = reverse(slow.next);
        slow.next = null;
        while (head != null && head2 != null) {
            if (head.val != head2.val)
                return false;
            else {
                head = head.next;
                head2 = head2.next;
            }
        }
        return true;
    }

    /**
     * 反转链表
     *
     * @param head
     * @return
     */
    private static ListNode reverse(ListNode head) {
        if (head == null)
            return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = pre.next;
        while (cur.next != null) {
            ListNode nex = cur.next;

            cur.next = nex.next;
            nex.next = pre.next;
            pre.next = nex;
        }
        return pre.next;
    }
}
