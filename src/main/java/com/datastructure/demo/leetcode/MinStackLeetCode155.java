package com.datastructure.demo.leetcode;

import java.util.LinkedList;

public class MinStackLeetCode155 {
    LinkedList<Data> stack = new LinkedList<>();

    record Data(int val, int min) {

    }

    public void push(int val) {
        if (stack.isEmpty()) {
            stack.push(new Data(val, val));
        } else {
            stack.push(new Data(val, Math.min(val, stack.peek().val)));
        }
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek().val;
    }

    public int getmin() {
        return stack.peek().min;
    }

}
