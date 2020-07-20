package Leetcode.LinkedList;

/**
 * 对链表进行插入排序。
 * 插入排序算法：
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 重复直到所有输入数据插入完为止。
 *
 * @author liuzy
 * @date 2020/7/20 22:53
 */
public class Insertion_sorted_list_147 {

    /*
    示例 2：
        输入: -1->5->3->4->0
        输出: -1->0->3->4->5
     */
    public static void main(String[] args) {
        ListNode head = new ListNode(-2147483647);
        ListNode node = head;
        node.next = new ListNode(-2147483648);
        /*node = node.next;
        node.next = new ListNode(1);
        node = node.next;
        node.next = new ListNode(3);
        node = node.next;
        node.next = new ListNode(0);*/
        Utils.printNode(head);

        System.out.println("=================");
        ListNode res = Insertion_sorted_list_147.insertionSortList2(head);
        Utils.printNode(res);

    }

    // todo 这个解法在特殊情况下无法通过，[-2147483647,-2147483648]
    public static ListNode insertionSortList(ListNode head) {
        // 思路，cur指针指向当前操作节点，将当前操作节点不断和之前的节点进行比较，并交换位置

        // 虚拟头节点
        ListNode dummyHead = new ListNode(Integer.MIN_VALUE);
        dummyHead.next = head;

        // 初始化指针指向
        ListNode pre = dummyHead;
        ListNode cur = head;
        while (cur != null) {

            // 因为假设前面是排好序的，所以判断当前节点和上一个节点的值，如果当前节点比前一个节点大，就说明是有序的,此时只需要移动指针位置
            if (cur.val > pre.val) {
                // 移动指针位置
                pre = pre.next;
                cur = cur.next;

            } else { // 否则就需要进行节点的定位和交换

                // 使用insertPreNode标定插入位置前面的一个节点
                ListNode insertPreNode = new ListNode(Integer.MIN_VALUE);
                // 使用insertNextNode标定插入位置后面的一个节点
                ListNode insertNextNode = new ListNode(Integer.MIN_VALUE);

                // 每次都要从头节点开始遍历，直到cur节点，找寻中间第一个比cur的值大的节点
                // circleNode表示每次循环所用的node
                ListNode circleNode = dummyHead;
                while (circleNode.val < cur.val) {
                    insertPreNode = circleNode;
                    circleNode = circleNode.next;
                    insertNextNode = circleNode;
                }

                // 执行插入操作
                pre.next = cur.next;
                cur.next = insertNextNode;
                insertPreNode.next = cur;
                // 重新定位cur指针的指向
                cur = pre.next;
            }

        }

        return dummyHead.next;
    }

    /*
        这里贴一通过的解法，后面有时间要对比改进一下
     */
    public static ListNode insertionSortList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        ListNode pre = head;
        ListNode cur = head.next;
        dummy.next = head;
        while (cur != null) {
            if (pre.val <= cur.val) {// 本来就有序
                pre = cur;
                cur = cur.next;
            } else {
                ListNode p = dummy;
                // 找到一个位置使得p < cur < p.next
                while (p.next != cur && p.next.val < cur.val) {
                    p = p.next;
                }
                // 将cur插入到p和p.next之间
                // 因为cur被插到前面，pre的指针需要跳过cur
                pre.next = cur.next;
                cur.next = p.next;
                p.next = cur;
                // 完成插入后，cur回到pre后面
                cur = pre.next;
            }
        }
        return dummy.next;
    }
}
