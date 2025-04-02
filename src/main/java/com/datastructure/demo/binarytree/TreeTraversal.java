package com.datastructure.demo.binarytree;

public class TreeTraversal {
    /**
     * 1
     * / \
     * 2   3
     * /    / \
     * 4     5  6
     **/
    public static void main(String[] args) {
        TreeNode root = new TreeNode(new TreeNode(new TreeNode(4), 2, new TreeNode(7)),
                1, new TreeNode(new TreeNode(5), 3, new TreeNode(6)));
        preOrder(root);
        System.out.println();
        inOrder(root);
        System.out.println();
        postOrder(root);
    }

    // Depth-first order: pre-order, in-order, post-order
    // pre-order 前序遍歷：先訪問節點 -> 左子樹 -> 右子樹
    static void preOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.val + "\t");
        preOrder(node.left); // 左
        preOrder(node.right); // 右
    }

    // in-order 中序遍歷：左子樹 -> 節點 -> 右子樹
    static void inOrder(TreeNode node) {
        if (node == null) {return;}
        inOrder(node.left);
        System.out.print(node.val +"\t");
        inOrder(node.right);

    }

    // post-order 後序遍歷：左子樹 -> 右子樹 -> 節點
    static void postOrder(TreeNode node) {
        if (node == null) {return;}
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.val + "\t");
    }
}
