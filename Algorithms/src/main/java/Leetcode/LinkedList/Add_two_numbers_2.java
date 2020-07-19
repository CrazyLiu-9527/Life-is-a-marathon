package Leetcode.LinkedList;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * @author liuzy
 * @date 2020/7/19 16:31
 */
public class Add_two_numbers_2 {


    /*public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 当两个链表有一个还没遍历完的时候就继续遍历，将其相加的值返回为一个新链表

        // res是最终返回的结果，resNode是过程中需要操作的节点
        ListNode res = new ListNode(0);
        ListNode resNode = res;

        while (l1 != null || l2 != null) {

            // 两个节点的值
            int node1Val = l1 == null ? 0 : l1.val;
            int node2Val = l2 == null ? 0 : l2.val;

            // 将resNode指向新创建的节点
            int val = (node1Val + node2Val) > 10 ? (node1Val + node2Val - 10) : (node1Val + node2Val);
            resNode.next = new ListNode(val);
            resNode = resNode.next;

            // 将l1，l2指向下一个节点
            l1 = l1.next;
            l2 = l2.next;
        }

        return res.next;
    }*/

    /**
     * 别人的答案
     * 标签：链表
     * 将两个链表看成是相同长度的进行遍历，如果一个链表较短则在前面补 00，比如 987 + 23 = 987 + 023 = 1010
     * 每一位计算的同时需要考虑上一位的进位问题，而当前位计算结束后同样需要更新进位值
     * 如果两个链表全部遍历完毕后，进位值为 11，则在新链表最前方添加节点 11
     * 小技巧：对于链表问题，返回结果为头结点时，通常需要先初始化一个预先指针 pre，该指针的下一个节点指向真正的头结点head。使用预先指针的目的在于链表初始化时无可用节点值，而且链表构造过程需要指针移动，进而会导致头指针丢失，无法返回结果。
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;

            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);

            cur = cur.next;
            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
        }
        if (carry == 1) {
            cur.next = new ListNode(carry);
        }
        return pre.next;
    }

    /*
    示例：
        输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
        输出：7 -> 0 -> 8
        原因：342 + 465 = 807
     */
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(2);
        ListNode node1 = listNode1;
        node1.next = new ListNode(4);
        node1 = node1.next;
        node1.next = new ListNode(3);
        Utils.printNode(listNode1);

        ListNode listNode2 = new ListNode(5);
        ListNode node2 = listNode2;
        node2.next = new ListNode(6);
        node2 = node2.next;
        node2.next = new ListNode(4);
        Utils.printNode(listNode2);

        ListNode resNode = Add_two_numbers_2.addTwoNumbers(listNode1, listNode2);
        Utils.printNode(resNode);
    }


}



