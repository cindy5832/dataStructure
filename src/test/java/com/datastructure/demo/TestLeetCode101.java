package com.datastructure.demo;

import com.datastructure.demo.binarytree.LeetCode101;
import com.datastructure.demo.binarytree.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestLeetCode101 {
    @Test
    public void test1() {
        TreeNode root = new TreeNode(
                new TreeNode(new TreeNode(3), 2, new TreeNode(4)),
                1,
                new TreeNode(new TreeNode(4), 2, new TreeNode(3)));
        Assertions.assertTrue(new LeetCode101().isSymmetric(root));
    }

    @Test
    public void test2() {
        TreeNode root = new TreeNode(
                new TreeNode(null, 2, new TreeNode(3)),
                1,
                new TreeNode(null, 2, new TreeNode(3)));
        Assertions.assertFalse(new LeetCode101().isSymmetric(root));
    }
}
