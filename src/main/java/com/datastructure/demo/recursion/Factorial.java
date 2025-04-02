package com.datastructure.demo.recursion;

// 階層：1*2*3*4*5...
public class Factorial {
    public static int f(int n){
        if (n == 1){
            return 1;
        }
        return n * f(n - 1);
    }

    public static void main(String[] args) {
        int i = f(5);
        System.out.println(i);
    }
}
