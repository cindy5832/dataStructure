package com.datastructure.demo.binarytree;

import java.util.LinkedList;

public class LeetCode104_1 {
    public static int maxDepth(TreeNode root) {
        TreeNode curr = root;
        TreeNode pop = null;
        int max = 0;
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (curr != null || !stack.isEmpty()) {
            if(curr != null){
                stack.push(curr);
                int size = stack.size();
                if(size > max){
                    max = size;
                }
                curr = curr.left;
            }else {
                TreeNode peek = stack.peek();
                if(peek.right == null || peek.right == pop){
                    pop = stack.pop();
                }else {
                    curr = peek.right;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                new TreeNode(new TreeNode(3), 2, new TreeNode(4)),
                1,
                new TreeNode(new TreeNode(4), 2, new TreeNode(3)));
        ;
        System.out.println(maxDepth(root));
    }
}
