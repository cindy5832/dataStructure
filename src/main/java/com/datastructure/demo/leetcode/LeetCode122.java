package com.datastructure.demo.leetcode;

public class LeetCode122 {
    int maxProfit(int[] prices) {
        int i = 0;
        int j = 1;
        int sum = 0;
        while (j < prices.length) {
            if (prices[j] - prices[i] > 0) {
                sum += prices[j] - prices[i];
            }
            i++;
            j++;
        }
        return sum;
    }

    public static void main(String[] args) {
        LeetCode122 leetCode122 = new LeetCode122();
        System.out.println(leetCode122.maxProfit(new int[]{7, 1, 5, 3, 6, 4})); // 7
        System.out.println(leetCode122.maxProfit(new int[]{9, 3, 12, 1, 2, 3})); // 11
    }
}
