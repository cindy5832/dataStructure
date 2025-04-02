package com.datastructure.demo.dynamicProgramming;

import java.util.Arrays;

// LeetCode96
public class Catalan {
    public static void main(String[] args) {
        System.out.println(catalan(5));
    }

    static int catalan(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int j = 2; j < n + 1; j++) {
            for (int i = 0; i < j; i++) { // 第j個catalan
                System.out.printf("(%d,%d)\t", i, j - 1 - i);
                dp[j] += dp[i] * dp[j - 1 - i];
            }
            System.out.println();
            System.out.println(Arrays.toString(dp));
        }
        return dp[n];
    }
}
