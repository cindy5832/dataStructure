package com.datastructure.demo.dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode22 {
    public List<String> generateParenthesis(int n) {
        ArrayList<String>[] dp = new ArrayList[n + 1];
        dp[0] = new ArrayList<>(List.of(""));
        dp[1] = new ArrayList<>(List.of("()"));
        for (int j = 2; j < n + 1; j++) {
            dp[j] = new ArrayList<>();
            for (int i = 0; i < j; i++) { // 第j個catalan
                for (String k1 : dp[i]) {
                    System.out.println("k1:" + k1);
                    for (String k2 : dp[j - 1 - i]) {
                        dp[j].add("(" + k1 + ")" + k2);
                    }
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        LeetCode22 leetCode22 = new LeetCode22();
        System.out.println(leetCode22.generateParenthesis(3));
    }
}
