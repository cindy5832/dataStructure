package com.datastructure.demo.binarytree;

public class LeetCode226 {
    public TreeNode invertTree(TreeNode root) {
        fn(root);
        return root;
    }
    public static void fn(TreeNode node) {
        if(node == null) return;
        TreeNode t = node.left;
        node.left = node.right;
        node.right = t;
        fn(node.left);
        fn(node.right);
    }
}
