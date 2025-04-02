package com.datastructure.demo.recursion;

// 遞歸求和 n + n-1, ... +1
public class E06Sum {
    // f(n) = f(n-1) + n
    public static long sum(long n) {
        if (n == 1) return 1;
        return sum(n - 1) + n;
    }

    public static void main(String[] args) {
        System.out.println(sum(100));
    }
}
