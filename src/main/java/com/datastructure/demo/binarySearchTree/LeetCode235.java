package com.datastructure.demo.binarySearchTree;

import com.datastructure.demo.binarytree.TreeNode;

public class LeetCode235 {

    // 待查找的p q 在某節點的兩側，此節點為公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode a = root;
        while (p.val < a.val && q.val < a.val || a.val < p.val && a.val < q.val) { // 在同一側
            if (p.val < a.val) {
                a = a.left;
            } else {
                a = a.right;
            }
        }
        return a;
    }
}
