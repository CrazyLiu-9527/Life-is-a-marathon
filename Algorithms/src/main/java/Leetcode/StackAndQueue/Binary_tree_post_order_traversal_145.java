package Leetcode.StackAndQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的后序遍历
 *
 * @author liuzy
 * @date 2020/7/23 22:48
 */
public class Binary_tree_post_order_traversal_145 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = null;
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        Binary_tree_post_order_traversal_145.postorderTraversal(root);
    }

    public static List<Integer> postorderTraversal(TreeNode root) {
        //思路，递归遍历树，在合适的时候打印节点
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    // 后序遍历 左->右->中
    private static void helper(TreeNode root, List<Integer> res) {
        if (root == null) return;
        helper(root.left, res);
        helper(root.right, res);
        res.add(root.val);
    }
}
