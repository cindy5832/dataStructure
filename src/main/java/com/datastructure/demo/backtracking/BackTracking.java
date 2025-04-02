package com.datastructure.demo.backtracking;

import java.util.LinkedList;

public class BackTracking {
    public static void main(String[] args) {
        rec(1, new LinkedList<>());
    }

    private static void rec(int n, LinkedList<String> list) {
        if(n == 3){
            return;
        }
        System.out.println("before:" + list);
        list.push("a");
        rec(n+1, list);
        list.pop();
        System.out.println("after:" + list);

    }
}
