package com.datastructure.demo;

import com.datastructure.demo.binarySearchTree.BSTTree1;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestBSTTree1 {

    /**
     *    4
     *   / \
     *  2   6
     * / \ / \
     * 1 3 5  7
     **/

    @Test
    public void less() {
        BSTTree1.BSTNode n1 = new BSTTree1.BSTNode(1, 1);
        BSTTree1.BSTNode n3 = new BSTTree1.BSTNode(3, 3);
        BSTTree1.BSTNode n2 = new BSTTree1.BSTNode(2, 2, n1, n3);

        BSTTree1.BSTNode n5 = new BSTTree1.BSTNode(5, 5);
        BSTTree1.BSTNode n7 = new BSTTree1.BSTNode(7, 7);
        BSTTree1.BSTNode n6 = new BSTTree1.BSTNode(6, 6, n5, n7);
        BSTTree1.BSTNode root = new BSTTree1.BSTNode(4, 4, n2, n6);

        BSTTree1 tree = new BSTTree1();
        tree.root = root;

        Assertions.assertIterableEquals(List.of(1, 2, 3, 4, 5), tree.less(6));
        Assertions.assertIterableEquals(List.of(7), tree.greater(6));
        Assertions.assertIterableEquals(List.of(3, 4, 5), tree.between(3, 5));

    }

}
