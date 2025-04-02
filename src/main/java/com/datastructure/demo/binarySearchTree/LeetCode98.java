package com.datastructure.demo.binarySearchTree;


import com.datastructure.demo.binarytree.TreeNode;

import java.util.LinkedList;

public class LeetCode98 {
    long prev = Long.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        TreeNode p = root;
        LinkedList<TreeNode> stack = new LinkedList<>();
        long prev = Long.MIN_VALUE; // 代表上一個值
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                TreeNode pop = stack.pop();
                if (prev >= pop.val)
                    return false;
                prev = pop.val;
                p = pop.right;
            }
        }
        return true;
    }

    public boolean isValidBST2(TreeNode root) {
        if (root == null) return true;
        boolean a = isValidBST2(root.left);
        if (!a) return false;
        if (prev >= root.val) return false;
        prev = root.val;
        boolean b = isValidBST2(root.right);
        return a && b;
    }

    public boolean isValidBST3(TreeNode root) {
        return doValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean doValid(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        boolean a = doValid(node.left, min, node.val);
        boolean b = doValid(node.right, node.val, max);
        return a && b;
    }


    /**
     * 4
     * / \
     * 2   6
     * / \
     * 1  3
     **/
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(new TreeNode(new TreeNode(1), 2, new TreeNode(3)), 4, new TreeNode(6));
        System.out.println(new LeetCode98().isValidBST(root1));
        /**
         *     5
         *    / \
         *   4   6
         *      / \
         *     3   7
         **/
        TreeNode root2 = new TreeNode(new TreeNode(4), 5, new TreeNode(new TreeNode(3), 6, new TreeNode(7)));
        System.out.println(new LeetCode98().isValidBST(root2));
    }


}
