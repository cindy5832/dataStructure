package com.datastructure.demo.stack;

import java.util.LinkedList;
import java.util.Stack;

public class LeetCode232 {
    ArrayStack<Integer> s1 = new ArrayStack<>(100);
    ArrayStack<Integer> s2 = new ArrayStack<>(100);

    public void push(int x) {
        // 向對列尾部添加
        s2.push(x);
    }

    public int pop() {
        // 向對列頭移除
        if (s1.isEmpty()) {
            {
                while (!s2.isEmpty()) {
                    s1.push(s2.pop());
                }
            }
        }
        return s1.pop();
    }

    public int peek() {
        // 從對列頭獲取
        if (s1.isEmpty()) {
            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
        }
        return s1.peek();
    }

    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> stack1 = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack);
//        System.out.println(stack.peek());
//        System.out.println(stack.pop());
        stack1.push(stack.pop());
        stack1.push(stack.pop());
        stack1.push(stack.pop());
        System.out.println(stack1);
        System.out.println(stack1.pop());

    }
}
