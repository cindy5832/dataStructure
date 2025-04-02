package com.datastructure.demo.binarySearchTree;

import com.datastructure.demo.queue.TreeNode;

public class LeetCode1008 {

    public TreeNode bstFromPreorder(int[] preorder) {
        return partition(preorder, 0, preorder.length - 1);
    }

    private TreeNode partition(int[] preorder, int start, int end) {
        if (start > end) return null;
        TreeNode root = new TreeNode(preorder[start]);
        int index = start + 1;
        while (index <= end) {
            if (preorder[index] > preorder[start]) {
                break;
            }
            index++;
        }
        root.left = partition(preorder, start + 1, index - 1);
        root.right = partition(preorder, index, end);
        return root;
    }

    public TreeNode bstFromPreorder2(int[] preorder) {
        return insertNode(preorder, Integer.MAX_VALUE);
    }

    int i = 0;

    private TreeNode insertNode(int[] preorder, int max) {
        if (i == preorder.length) return null;
        int val = preorder[i];
        if (val > max) return null;
        TreeNode node = new TreeNode(val);
        i++;
        node.left = insertNode(preorder, val);
        node.right = insertNode(preorder, max);
        return node;
    }


    public TreeNode bstFromPreorder1(int[] preorder) {
        TreeNode root = new TreeNode(preorder[0]);
        for (int i = 1; i < preorder.length; i++) {
            int val = preorder[i];
            insert(root, val);
        }
        return root;
    }

    private TreeNode insert(TreeNode node, int val) {
        if (node == null) {
            return new TreeNode(val);
        }
        if (val < node.val) {
            node.left = insert(node.left, val);
        }
        if (val > node.val) {
            node.right = insert(node.right, val);
        }
        return node;
    }

}
