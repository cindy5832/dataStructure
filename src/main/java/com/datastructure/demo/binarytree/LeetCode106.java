package com.datastructure.demo.binarytree;

import java.util.Arrays;

public class LeetCode106 {
    /**
     * inorder = {4,2,1,6,3,7}
     * postorder = {4,2,6,7,3,1}
     **/
    public TreeNode buildTree(int[] postorder, int[] inorder) {
        if (inorder.length == 0) return null;
        int rootVal = postorder[postorder.length - 1];
        TreeNode root = new TreeNode(rootVal);
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == rootVal) {
                int[] inleft = Arrays.copyOfRange(inorder, 0, i);
                int[] inright = Arrays.copyOfRange(inorder, i + 1, inorder.length);
                int[] postleft = Arrays.copyOfRange(postorder, 0, i);
                int[] postright = Arrays.copyOfRange(postorder, i, postorder.length - 1);

                root.left = buildTree(inleft, postleft);
                root.right = buildTree(inright, postright);
                break;
            }
        }
        return root;
    }
}
