package com.datastructure.demo.binarytree;

import java.util.LinkedList;

public class ExpressionTree {
    static class TreeNode {
        public String val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(String val) {
            this.val = val;
        }

        public TreeNode(TreeNode left, String val, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }

    public static TreeNode constructExpree(String[] tokens) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        for (String t : tokens) {
            switch (t) {
                case "+", "-", "*", "/" -> { // 運算符
                    TreeNode right = stack.pop();
                    TreeNode left = stack.pop();
                    TreeNode root = new TreeNode(t);
                    root.left = left;
                    root.right = right;
                    stack.push(root);
                }
                default -> { // 數字
                    stack.push(new TreeNode(t));
                }
            }
        }
        return stack.peek();
    }
}
