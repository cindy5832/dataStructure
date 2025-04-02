package com.datastructure.demo.dequeue;

import com.datastructure.demo.queue.LinkedListQueue;
import com.datastructure.demo.queue.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LeetCode103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        LinkedListQueue<TreeNode> queue = new LinkedListQueue<>();
        queue.offer(root);
        int c1 = 1; // 當前節點數
        boolean odd = true; // 奇數層
        while (!queue.isEmpty()) {
            LinkedList<Integer> level = new LinkedList<>();
            int c2 = 0; // 下層節點數
            for (int i = 0; i < c1; i++) {
                TreeNode n = queue.poll();

                if (odd) {
                    level.offerLast(n.val);
                } else {
                    level.offerFirst(n.val);
                }

                if (n.left != null) {
                    queue.offer(n.left);
                    c2++;
                }
                if (n.right != null) {
                    queue.offer(n.right);
                    c2++;
                }
            }
            odd = !odd;
            result.add(level);
            c1 = c2;
        }
        return result;
    }

    public static void main(String[] args) {
        Queue<Integer> q = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> list = new LinkedList<>();
        list.offerFirst(1);
        list.offerFirst(2);
        list.offerLast(3);
        System.out.println(list);

    }
}
