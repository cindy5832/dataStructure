package com.datastructure.demo.graph;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class LeetCode518 {

    public static void main(String[] args) {
        LeetCode518 leetCode518 = new LeetCode518();
//        int count = leetCode518.coinChange(new int[]{1, 2, 5}, 5);
//        System.out.println(count);
//        int count1 = leetCode518.change(5, new int[]{1, 2, 5});
//        System.out.println(count1);
        int count2 = leetCode518.change1(5, new int[]{1, 2, 5});
        System.out.println(count2);

    }

    public int coinChange(int[] coins, int amount) {
        return rec(0, coins, amount, new LinkedList<>(), true);
    }

    /**
     * @return int 解的個數
     * @description: 求湊成剩餘金額解的個數
     * @param: [index 當前硬幣索引, coins 硬幣面額數組, reminder 剩餘金額, stack, first]
     **/
    public int rec(int index, int[] coins, int reminder, LinkedList<Integer> stack, boolean first) {
        if (!first) {
            stack.push(coins[index]);
        }
        int count = 0;
        // 1. 剩餘金額 < 0
        if (reminder < 0) {
            System.out.println("無解" + stack);
        }
        // 2. 剩餘金額 > 0
        else if (reminder == 0) {
            System.out.println("有解" + stack);
            count = 1;
            // 3. 剩餘金額 == 0
        } else {
            for (int i = index; i < coins.length; i++) {
                count += rec(i, coins, reminder - coins[i], stack, false);
            }
        }
        if (!stack.isEmpty()) {
            stack.pop();
        }
        return count;
    }

    public int change(int amount, int[] coins) {
        return rec(coins.length - 1, amount, coins);
    }

    private int rec(int index, int amount, int[] coins) {
        int count = 0;
        if (amount < 0) {
            return count;
        } else if (amount == 0) {
            count = 1;
        } else {
            for (int i = index; i >= 0; i--) {
                count += rec(i, amount - coins[i], coins);
            }
        }
        return count;
    }

    public int change1(int amount, int[] coins) {
        int[][] dp = new int[coins.length+1][amount+1];
        dp[0][0] = 1;

        for (int i = 1; i <= coins.length; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= amount; j++) {
                // dp[i][j] 使用 i 硬幣湊稱的 j 數量
                dp[i][j] = dp[i-1][j] + (j >= coins[i-1] ? dp[i][j-coins[i-1]] : 0);
            }
        }
        return dp[coins.length][amount];
    }
}
