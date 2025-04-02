package com.datastructure.demo.leetcode;

import java.util.LinkedList;

public class MinStack {
    LinkedList<Integer> stack = new LinkedList<Integer>();
    LinkedList<Integer> min = new LinkedList<>();

    public MinStack() {
        min.push(Integer.MAX_VALUE);
    }

    public void push(int val) {
        stack.push(val);
        min.push(Math.min(val, min.peek()));
    }

    public void pop() {
        if(stack.isEmpty()){
            return;
        }
        stack.pop();
        min.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min.peek();
    }

}
