package com.datastructure.demo.leetcode;

public class LeetCode121 {
    int maxProfit(int[] prices) {
        int i = 0;
        int j = 1;
        int max = 0;
        while (j < prices.length) {
            int profit = prices[j] - prices[i];
            if (profit > 0) {
                // 漲
                max = Math.max(max, profit);
            } else {
                // 跌
                i = j;
            }
            j++;
        }
        return max;
    }

    public static void main(String[] args) {
        LeetCode121 leetCode121 = new LeetCode121();
        System.out.println(leetCode121.maxProfit(new int[]{7, 1, 5, 3, 6, 4})); // 5
        System.out.println(leetCode121.maxProfit(new int[]{9, 3, 12, 1, 2, 3})); // 9
    }
}
