package Leetcode.StackAndQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的中序遍历
 *
 * @author liuzy
 * @date 2020/7/23 22:46
 */
public class Binary_tree_in_order_traversal_94 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = null;
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        Binary_tree_in_order_traversal_94.inorderTraversal(root);
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        //思路，递归遍历树，在合适的时候打印节点
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    // 中序遍历 左->中->右
    private static void helper(TreeNode root, List<Integer> res) {
        if (root == null) return;
        helper(root.left, res);
        res.add(root.val);
        helper(root.right, res);
    }
}
