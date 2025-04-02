package com.datastructure.demo.binarytree;

public class LeetCode104 {
    public static int maxDepth(TreeNode root) {
        if(root == null) return 0;
//        if(root.left == null && root.right == null) return 1;
        int d1 = maxDepth(root.left);
        int d2 = maxDepth(root.right);
        return Math.max(d1, d2) + 1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                new TreeNode(new TreeNode(3), 2, new TreeNode(4)),
                1,
                new TreeNode(new TreeNode(4), 2, new TreeNode(3)));
        ;
        System.out.println(maxDepth(root));
    }
}
