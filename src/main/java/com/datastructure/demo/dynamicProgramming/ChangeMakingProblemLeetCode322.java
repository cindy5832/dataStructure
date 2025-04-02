package com.datastructure.demo.dynamicProgramming;

import java.util.Arrays;

public class ChangeMakingProblemLeetCode322 {
    /**
     * $    0   1   2    3      4       5
     * 1    0   1   11   111   1111    11111
     * 2    0   1   2    12    22      221
     * 5    0   1   2    12    22      5
     * ========================================
     * $    0   1   2   3   4   5
     * 10   max max max max max max
     **/
    public int coinChange(int[] coins, int amount) {
        int[][] dp = new int[coins.length][amount + 1];
        for (int i = 1; i < amount + 1; i++) {
            if (i >= coins[0]) { // 裝得下
                dp[0][i] = dp[0][i - coins[0]] + 1;
            } else {
                dp[0][i] = amount + 1; // 最大值
            }
        }
        print(dp);
        for (int i = 1; i < coins.length; i++) {
            for (int j = 1; j < amount + 1; j++) {
                if (j >= coins[i]) {
                    dp[i][j] = Integer.min(dp[i - 1][j], dp[i][j - coins[i]] + 1);
                } else {
                    // 放不下
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        print(dp);
        return dp[coins.length - 1][amount] > amount ? -1 : dp[coins.length - 1][amount];
    }

    public int coinChange1(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        System.out.println(Arrays.toString(dp));
        for (int coin : coins) {
            for (int j = coin; j < amount + 1; j++) {
                dp[j] = Integer.min(dp[j], dp[j - coin] + 1);
            }
            System.out.println(Arrays.toString(dp));
        }
        int r = dp[amount];
        return r > amount ? -1 : r;
    }

    public int coinChange2(int[] coins, int amount) {
        if (amount == 0) return 0;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int coin : coins) {
            for (int j = coin; j <= amount; j++) {
                dp[j] = Integer.min(dp[j], dp[j - coin] + 1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    static void print(int[][] dp) {
        for (int i = 0; i < dp[0].length; i++) {
            System.out.print(i + "\t");
        }
        System.out.println();
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                System.out.print(dp[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("===============================================");
    }

    public static void main(String[] args) {
        ChangeMakingProblemLeetCode322 problem = new ChangeMakingProblemLeetCode322();
//        int count = problem.coinChange(new int[]{5}, 6);
//        int count1 = problem.coinChange1(new int[]{1, 2, 5}, 5);
        int count = problem.coinChange2(new int[]{1, 2, 5}, 5);
        System.out.println(count);
    }
}
