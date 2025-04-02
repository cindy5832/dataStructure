package com.datastructure.demo.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                new TreeNode(new TreeNode(4), 2,
                        new TreeNode(new TreeNode(6), 5, new TreeNode(7))),
                1, new TreeNode(null, 3, new TreeNode(
                new TreeNode(9), 8, null)
        ));
        List<Integer> l = preorderTraversal(root);
        System.out.println(l);
        List<Integer> l1 = inorderTraversal(root);
        System.out.println(l1);
        List<Integer> l2 = postorderTraversal(root);
        System.out.println(l2);
    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        TreeNode cur = root;
        while (cur != null || !queue.isEmpty()) {
            if (cur != null) {
                list.add(cur.val);
                queue.push(cur);
                cur = cur.left;
            } else {
                TreeNode pop = queue.pop();
                cur = pop.right;
            }
        }
        return list;
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        TreeNode cur = root;
        while (cur != null || !queue.isEmpty()) {
            if (cur != null) {
                queue.push(cur);
                cur = cur.left;
            } else {
                TreeNode pop = queue.pop();
                list.add(pop.val);
                cur = pop.right;
            }
        }
        return list;
    }

    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        TreeNode cur = root;
        TreeNode pop = null;
        while (cur != null || !queue.isEmpty()) {
            if (cur != null) {
                queue.push(cur);
                cur = cur.left;
            } else {
                TreeNode node = queue.peek();
                if (node.right == null || node.right == pop) {
                    pop = queue.pop();
                    list.add(pop.val);
                } else {
                    cur = node.right;
                }
            }
        }
        return list;
    }
}
