package com.datastructure.demo.leetcode;

public class LeetCode309 {

    int maxProfit(int[] prices) {
        int buy = -prices[0];
        int sell = 0;
        int cd = 0; // cooldown
        for (int i = 1; i < prices.length; i++) {
            int nbuy = Math.max(buy, cd - prices[i]);
            int nsell = Math.max(sell, buy + prices[i]);
            int ncd = Math.max(cd, sell);
            sell = nsell;
            buy = nbuy;
            cd = ncd;
        }
        return sell;
    }

    int maxProfit1(int[] prices) {
        if (prices.length == 1) return 0;
        int[] buy = new int[prices.length];
        int[] sell = new int[prices.length];
        buy[0] = -prices[0];
        sell[0] = 0;
        buy[1] = Math.max(-prices[0], -prices[1]);
        sell[1] = Math.max(sell[0], buy[0] + prices[1]);
        for (int i = 2; i < prices.length; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
        }
        return sell[prices.length - 1];
    }

    public static void main(String[] args) {
        LeetCode309 leetcode309 = new LeetCode309();
        System.out.println(leetcode309.maxProfit(new int[]{1,2,3,0,2})); // 3
    }
}
