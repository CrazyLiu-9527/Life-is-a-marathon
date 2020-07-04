package BinarySearchTree.AddElementsInBST03;

/**
 * 在二叉搜索树中添加元素
 *
 * @author zhiyuanliu
 * @date 2020/6/20 14:10
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
     * 向二叉搜索树中添加元素
     */
    public void add(E e) {
        if (root == null) {
            root = new Node(e);
            size++;
        } else {
            addElement(root, e);
        }
    }

    /**
     * 向以node为根的二分搜索树中插入元素e，递归算法
     */
    private void addElement(Node node, E e) {
        if (e.equals(node.e)) {
            return;
        } else if (e.compareTo(node.e) < 0 && node.left == null) {
            node.left = new Node(e);
            size++;
            return;
        } else if (e.compareTo(node.e) > 0 && node.right == null) {
            node.right = new Node(e);
            size++;
            return;
        }

        if (e.compareTo(node.e) > 0) {
            addElement(node.right, e);
        } else {
            addElement(node.left, e);
        }
    }
}
