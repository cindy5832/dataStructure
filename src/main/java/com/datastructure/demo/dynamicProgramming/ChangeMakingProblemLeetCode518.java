package com.datastructure.demo.dynamicProgramming;

import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;

import java.util.Arrays;

public class ChangeMakingProblemLeetCode518 {
    /**
     * $    0   1   2    3      4       5
     * 1    1   1   11   111   1111    11111
     * <p>
     * 2    1   1   11   111   1111    11111
     * 2    21    211     2111
     * 22      221
     * <p>
     * 5    1   1   11   111   1111    11111
     * 2    21    211     2111
     * 22      221
     * 5
     * ========================================
     * if (放得下)
     * dp[i][j] = dp[i-1][j] + dp[i][j-coin]
     * else (放不下)
     * dp[i][j] = dp[i-1][j]
     **/
    public int coinChange(int[] coins, int amount) {
        int[][] dp = new int[coins.length][amount + 1];
        for (int i = 0; i < coins.length; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; j < amount + 1; j++) {
            if (j >= coins[0]) {
                dp[0][j] = dp[0][j - coins[0]];
            }
        }
        print(dp);
        for (int i = 1; i < coins.length; i++) {
            for (int j = 1; j < amount + 1; j++) {
                if (j >= coins[i]) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        print(dp);
        return dp[coins.length - 1][amount];
    }

    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int j = 1; j < amount + 1; j++){
                if (j >= coin) {
                    dp[j] = dp[j - coin] + dp[j];
                }
            }
        }
        return dp[amount];
    }

    private static void print(int[][] dp) {
        for (int i = 0; i < dp[0].length; i++) {
            System.out.print(i + "\t");
        }
        System.out.println();
        for (int[] ints : dp) {
            for (int j = 0; j < ints.length; j++) {
                System.out.print(ints[j] + "\t");
            }
            System.out.println();
        }
        System.out.println("===============================================");
    }

    public static void main(String[] args) {
        ChangeMakingProblemLeetCode518 problem = new ChangeMakingProblemLeetCode518();
//        int count = problem.coinChange(new int[]{2}, 3);
        int count1 = problem.change(5, new int[]{1, 2, 5});
//        System.out.println(count);
        System.out.println(count1);
    }
}
