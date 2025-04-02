package com.datastructure.demo.dynamicProgramming;

// fibonacci (動態規劃)
public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(fibonacci(13));
        System.out.println(fibonacci2(13));
    }

    /**
     * 1. 從已知子問題，推導出當前問題的解。推倒過程可以表達為一個數學公式
     * 2. 用一維或二維數組來保存之前的計算結果 (可以進一步優化)
     **/
    public static int fibonacci(int n) {
        int[] dp = new int[n + 1]; // 用來緩存結果
        dp[0] = 0;
        dp[1] = 1;
        if (n == 0) {
            dp[0] = 0;
            return 0;
        }
        if (n == 1) {

            return 1;
        }
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static int fibonacci2(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int a = 0;
        int b = 1;
        for (int i = 2; i <= n; i++) {
            int c = b + a;
            a = b;
            b = c;
        }
        return b;
    }
}
