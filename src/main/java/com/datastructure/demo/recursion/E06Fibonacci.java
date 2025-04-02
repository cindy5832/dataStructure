package com.datastructure.demo.recursion;

import java.util.Arrays;

// F0 0 F1 1 F2 1 F3 2 F4 3 F5 5 F6 8 F7 13 F8 21 F9 34 F10 55 F11 89 F12 144
public class E06Fibonacci {

    /**
     * @return int 第n項的值
     * @description: 使用Memorization改進
     * @param: [n] 第n項
     **/
    public static int fibonacci(int n) {
        int[] cache = new int[n + 1];
        Arrays.fill(cache, -1); // [-1, -1, -1, -1, -1, -1]
        cache[0] = 0;
        cache[1] = 1; // [0, 1, -1, -1, -1, -1]
        return f(n, cache);
    }

    public static int f(int n, int[] cache) {
//        if (n == 0) return 0;
//        if (n == 1) return 1;
        if (cache[n] != -1) return cache[n];
        int x = f(n - 1, cache);
        int y = f(n - 2, cache);
        cache[n] = x + y; // 存入數組
        return cache[n];
    }
}
