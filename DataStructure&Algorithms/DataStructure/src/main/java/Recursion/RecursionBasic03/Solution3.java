package Recursion.RecursionBasic03;

/**
 * @author zhiyuanliu
 * @date 2020/6/17 17:38
 */
public class Solution3 {
    public ListNode removeElements(ListNode head, int val) {
        //创建虚拟头节点
        ListNode dummyHead =  new ListNode(-1);
        dummyHead.next = head;

        //遍历链表去除和val相等的节点
        ListNode prev = dummyHead;
        while (prev.next != null) {
            if(prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }

        return dummyHead.next;
    }

    public static void main(String[] args) {

        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new Solution3()).removeElements(head, 6);
        System.out.println(res);
    }
}
