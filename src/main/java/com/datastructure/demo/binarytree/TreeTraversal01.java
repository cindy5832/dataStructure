package com.datastructure.demo.binarytree;

import java.util.LinkedList;

public class TreeTraversal01 {
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
    static void preOrder(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode curr = root; // 當前節點
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                System.out.print(curr.val + "\t");
                stack.push(curr); // 記住路線
                curr = curr.left;
            } else {
                TreeNode pop = stack.pop();
//                System.out.print(pop.val + "\t");
                curr = pop.right;
            }
        }
    }

    // in-order 中序遍歷：左子樹 -> 節點 -> 右子樹
    static void inOrder(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode curr = root; // 當前節點
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
//                System.out.println(curr.val);
                stack.push(curr); // 記住路線
                curr = curr.left;
            } else {
                TreeNode pop = stack.pop();
                System.out.print(pop.val + "\t");
                curr = pop.right;
            }
        }
    }

    // post-order 後序遍歷：左子樹 -> 右子樹 -> 節點
    static void postOrder(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode curr = root; // 當前節點
        TreeNode pop = null; // 最近一次彈出的元素
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
//                System.out.println(curr.val);
                stack.push(curr); // 記住路線
                curr = curr.left;
            } else {
                TreeNode peek = stack.peek();
                if (peek.right == null || peek.right == pop) {
                    pop = stack.pop();
                    System.out.print(pop.val + "\t");
                } else {
                    curr = peek.right;
                }
            }
        }
    }
}
