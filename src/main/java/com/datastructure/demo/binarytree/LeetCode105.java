package com.datastructure.demo.binarytree;

import javax.swing.*;
import java.util.Arrays;

public class LeetCode105 {
    /**
     * preOrder = {1,2,4,3,6,7}
     * inOrder = {4,2,1,6,3,7}
     **/
    public static TreeNode buildTree(int[] preorder, int[] inOrder) {
        if (preorder.length == 0 || inOrder.length == 0) return null;
        int rootValue = preorder[0];
        TreeNode root = new TreeNode(rootValue);
        // 區分左右子樹
        for (int i = 0; i < inOrder.length; i++) {
            if (inOrder[i] == rootValue) {
                // 0 ~ i-1 左子樹
                // i+1 ~ inorder.length - 1 右子樹
                int[] inLeft = Arrays.copyOfRange(inOrder, 0, i); // [4, 2]
                int[] inRight = Arrays.copyOfRange(inOrder, i + 1, inOrder.length); // [6, 3, 7]

                int[] preLeft = Arrays.copyOfRange(preorder, 1, i + 1); // [2, 4]
                int[] preRight = Arrays.copyOfRange(preorder, i + 1, inOrder.length); // [3, 6, 7]

                root.left = buildTree(preLeft, inLeft);
                root.right = buildTree(preRight, inRight);
                break;
            }
        }
        return root;
    }

    public static void main(String[] args) {
        int[] preorder = {1,2,3};
        int[] inOrde = {3,2,1};
        System.out.println(buildTree(preorder, inOrde));
    }
}
