package BinarySearchTree.NonRecursionPreOrderTraverse09;

import java.util.Stack;

/**
 * 增加二叉树的前序遍历的非递归写法
 * @author zhiyuanliu
 * @date 2020/6/20 15:29
 */
public class BST<E extends Comparable<E>> {
    private class Node {
        public E e;
        public Node left, right;

        public Node() {
            this.e = null;
            this.left = null;
            this.right = null;
        }

        public Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 向二叉搜索树中添加元素e,
     * 上一章节的代码逻辑不统一，而且比较冗长，这里进行一下优化
     */
    public void add(E e) {
        root = addElement(root, e);
    }

    /**
     * 向以node为根的二分搜索树中插入元素e，递归算法
     * 返回插入新结点后二分搜索树的根
     */
    private Node addElement(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) > 0) {
            node.right = addElement(node.right, e);
        } else if (e.compareTo(node.e) < 0) {
            node.left = addElement(node.left, e);
        }

        return node;
    }

    /**
     * 判断以node为根的二分搜索树中是否包含元素e
     */
    public boolean contains(E e) {
        return contains(root, e);
    }

    /**
     * 判断以node为根的二分搜索树中是否包含元素e，递归算法
     *
     * @param node
     * @param e
     * @return
     */
    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }

        if (node.e.compareTo(e) == 0) {
            return true;
        } else if (node.e.compareTo(e) > 0) {
            return contains(node.right, e);
        } else {
            return contains(node.left, e);
        }
    }

//     * 前序遍历： 根节点 -> 左子树 -> 右子树
//     * 中序遍历： 左子树 -> 根节点 -> 右子树
//     * 后序遍历： 左子树 -> 右子树 -> 根节点


    /**
     * 二叉搜索树的前序遍历
     */
    public void preOrder() {
        preOrder(root);
    }

    /**
     * 前序遍历以node为根的二分搜索树，递归算法
     * @param node
     */
    private void preOrder(Node node) {
        if (node == null) {
            return;
        }

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 二分搜索树的非递归遍历
     * 使用栈将节点压入和取出，因为LIFO的特点，所以后访问的节点先压栈，后出栈
     */
    public void preOrderNR() {
        if (root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.e);

            if (cur.right != null) {
                stack.push(cur.right);
            }

            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    /**
     * 二叉树的中序遍历
     * 二分搜索树的中序遍历得到的结果是顺序排列的结果
     */
    public void inOrder() {
        inOrder(root);
    }

    /**
     * 中序遍历以node为根的二分搜索树，递归算法
     * @param node
     */
    private void inOrder(Node node) {
        if (node == null) {
            return;
        }

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    /**
     * 二叉树的后序遍历
     */
    public void postOrder() {
        postOrder(root);
    }

    /**
     * 中序遍历以node为根的二分搜索树，递归算法
     * @param node
     */
    private void postOrder(Node node) {
        if (node == null) {
            return;
        }

        inOrder(node.left);
        inOrder(node.right);
        System.out.println(node.e);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    /**
     * 生成以node为根节点，深度为depth的描述二叉树的字符串
     * @param node
     * @param depth
     * @param res
     */
    private void generateBSTString(Node node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(generateDepthString(depth)).append("null\n");
            return;
        }
        res.append(generateDepthString(depth)).append(node.e).append("\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }
}
