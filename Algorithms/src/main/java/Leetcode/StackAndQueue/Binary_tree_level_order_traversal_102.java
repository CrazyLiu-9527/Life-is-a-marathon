package Leetcode.StackAndQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 * @author liuzy
 * @date 2020/7/23 22:54
 */
public class Binary_tree_level_order_traversal_102 {

    /*
    示例：
        二叉树：[3,9,20,null,null,15,7],
            3
           / \
          9  20
            /  \
           15   7
        返回其层次遍历结果：
        [
          [3],
          [9,20],
          [15,7]
        ]
     */
    public static void main(String[] args) {

    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        // 思路，使用队列，遍历到每个节点的时候就将其左右子节点放进队列中，然后取出该节点

        if (root == null) {
            return new ArrayList<List<Integer>>();
        }

        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (queue.size() > 0) {
            // 获取当前队列的长度，这个长度相当于当前这一层的节点个数
            int size = queue.size();
            ArrayList<Integer> tmp = new ArrayList<>();
            // 将队列中的元素都拿出来（也就是获取这一层的节点），放到临时的list中
            // 如果节点的左右子树不为空，也放入队列中
            for (int i = 0; i < size; i++) {
                TreeNode t = queue.remove();
                tmp.add(t.val);
                if (t.left != null) {
                    queue.add(t.left);
                }
                if (t.right != null) {
                    queue.add(t.right);
                }
            }
            res.add(tmp);

        }
        return res;
    }

}
