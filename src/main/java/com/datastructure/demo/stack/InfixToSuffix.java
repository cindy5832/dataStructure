package com.datastructure.demo.stack;

import java.util.LinkedList;

public class InfixToSuffix {
    /*
        a+b          ab+
        a+b-c        ab+c-
        a*b+c        ab*c+
        a+b*c        abc*+
        a+b*c-d      abc*+d-
        (a+b)*c      ab+c*
        (a+b*c-d)*e  abc*+d-e*
        a*(b+c)      abc+*
        ----------------------
        1. 遇到非運算符號直接拼接
        2. 遇到 + - * /
           - 優先級比stack內的運算符高入Stack
           - 否則將 >= stake的符號移除stack後再入stack
        3. 遍歷完成後，Stack剩餘運算符號一次出stack
        4. 帶()
           - ( 直接入Stack，優先級設置為0
           - ) 把stack到( 裡所有運算符號出stack
     */
    static int priority(char c) {
        return switch (c) {
            case '*', '/' -> 2;
            case '+', '-' -> 1;
            case '(' -> 0;
            default -> throw new IllegalArgumentException("不符合運算符:" + c);
        };
    }

    public static String infixToSuffix(String exp) {
        LinkedList<Character> stack = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);
            switch (c) {
                case '+', '-', '*', '/' -> {
                    if (stack.isEmpty()) {
                        stack.push(c);
                    } else {
                        if (priority(c) > priority(stack.peek())) {
                            stack.push(c);
                        } else {
                            while (!stack.isEmpty() && priority(stack.peek()) >= priority(c)) {
                                sb.append(stack.pop());
                            }
                            stack.push(c);
                        }
                    }
                }
                case '(' -> {
                    stack.push(c);
                }
                case ')' -> {
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        sb.append(stack.pop());
                    }
                    stack.pop();
                }
                default -> {
                    sb.append(c);
                }
            }
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(infixToSuffix("a+b"));
        System.out.println(infixToSuffix("a+b-c"));
        System.out.println(infixToSuffix("a+b*c"));
        System.out.println(infixToSuffix("a*b-c"));
    }
}
