package Leetcode.StackAndQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树，返回它的 前序 遍历。
 *
 * @author liuzy
 * @date 2020/7/23 22:34
 */
public class Binary_tree_pre_order_traversal_144 {

    /*
    输入: [1,null,2,3]
           1
            \
             2
            /
           3

        输出: [1,2,3]
     */
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = null;
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        Binary_tree_pre_order_traversal_144.preorderTraversal(root);
    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        //思路，递归遍历树，在合适的时候打印节点
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    // 前序遍历 中->左->右
    private static void helper(TreeNode root, List<Integer> res) {
        if (root == null) return;
        res.add(root.val);
        helper(root.left, res);
        helper(root.right, res);
    }
}
