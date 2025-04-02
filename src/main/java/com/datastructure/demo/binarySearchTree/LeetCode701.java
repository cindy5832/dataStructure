package com.datastructure.demo.binarySearchTree;

import com.datastructure.demo.binarytree.TreeNode;

public class LeetCode701 {

    public TreeNode insertIntoBST(TreeNode node, int val) {
        if (node == null) return new TreeNode(val);
        if (val < node.val) {
            // 父子
            node.left = insertIntoBST(node.left, val);
        } else if (val > node.val) {
            // 父子
            node.right = insertIntoBST(node.right, val);
        }
        return node;
    }
}
