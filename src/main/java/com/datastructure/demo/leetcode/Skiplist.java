package com.datastructure.demo.leetcode;

import java.util.Random;
import java.util.Stack;

public class Skiplist {

    class Node {
        int val;
        Node next;
        Node down;

        public Node(int val, Node next, Node down) {
            this.val = val;
            this.next = next;
            this.down = down;
        }
    }

    Node head = new Node(-1, null, null);
    Random rnd = new Random();

    public Skiplist() {

    }

    public boolean search(int target) {
        Node current = head;
        while (current != null) {
            while (current.next != null && current.next.val < target) {
                current = current.next;
            }
            if (current.next != null && current.next.val == target) {
                return true;
            }
            current = current.down;
        }
        return false;
    }


    public void add(int num) {
        Stack<Node> stack = new Stack<>();
        Node current = head;
        while (current != null) {
            while (current.next != null && current.next.val < num) {
                current = current.next;
            }
            stack.push(current);
            current = current.down;
        }
        boolean insert = true;
        Node down = null;
        while (insert && !stack.isEmpty()) {
            current = stack.pop();
            current.next = new Node(num, current.next, down);
            down = current.next;
            insert = rnd.nextDouble() < 0.5;
        }
        if (insert) {
            head = new Node(-1, null, head);
        }
    }

    public boolean erase(int num) {
        Node current = head;
        boolean found = false;
        while (current != null) {
            while (current.next != null && current.next.val < num) {
                current = current.next;
            }
            if (current.next != null && current.next.val == num) {
                found = true;
                current.next = current.next.next;
            }
            current = current.down;
        }
        return found;
    }


}

