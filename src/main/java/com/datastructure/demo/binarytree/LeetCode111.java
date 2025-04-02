package com.datastructure.demo.binarytree;

public class LeetCode111 {
    public static int minDepth(TreeNode node) {
        if (node == null) return 0;
        int d1 = minDepth(node.left);
        int d2 = minDepth(node.right);
        if (d2 == 0) return d1 + 1;
        if (d1 == 0) return d2 + 1;
        return Math.min(d1, d2) + 1;
    }
}
