package Leetcode.LinkedList;

/**
 * @author liuzy
 * @date 2020/7/19 16:35
 */
public class Utils {

    public static void printNode(ListNode node) {
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }
}
