package com.datastructure.demo.binarySearchTree;

import com.datastructure.demo.binarytree.TreeNode;

import java.util.LinkedList;

public class LeetCode938 {

    public int rangeSumBST(TreeNode node, int low, int high) {
        if (node == null) return 0;
        if (node.val < low){
            return rangeSumBST(node.right, low, high);
        }
        if(node.val > high){
            return rangeSumBST(node.left, low, high);
        }
        return node.val + rangeSumBST(node.left, low, high) + rangeSumBST(node.right, low, high);
    }

    public int rangeSumBST1(TreeNode node, int low, int high) {
        TreeNode p = node;
        LinkedList<TreeNode> stack = new LinkedList<>();
        int sum = 0;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                TreeNode pop = stack.pop();
                if (pop.val > high) break;
                if (pop.val >= low) {
                    sum += pop.val;
                }
                p = pop.right;
            }
        }
        return sum;
    }

    public static void main(String[] args) {

    }
}
