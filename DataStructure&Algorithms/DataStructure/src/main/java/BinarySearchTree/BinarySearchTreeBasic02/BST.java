package BinarySearchTree.BinarySearchTreeBasic02;

/**
 * 二叉搜索树
 * @author zhiyuanliu
 * @date 2020/6/20 14:07
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


}
