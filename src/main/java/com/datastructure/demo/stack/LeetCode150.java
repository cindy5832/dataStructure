package com.datastructure.demo.stack;

import java.util.LinkedList;

public class LeetCode150 {
    public static int evalRPN(String[] token) {
        LinkedList<Integer> stack = new LinkedList<>();
        for (String s : token) {
            switch (s) {
                case "+" -> {
                    Integer a = stack.pop();
                    Integer b = stack.pop();
                    stack.push(a + b);
                }
                case "-" -> {
                    Integer b = stack.pop();
                    Integer a = stack.pop();
                    stack.push(a - b);
                }
                case "*" -> {
                    Integer b = stack.pop();
                    Integer a = stack.pop();
                    stack.push(a * b);
                }
                case "/" -> {
                    Integer b = stack.pop();
                    Integer a = stack.pop();
                    stack.push(a / b);
                }
                default -> {
                    stack.push(Integer.parseInt(s));
                }
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {

    }
}
