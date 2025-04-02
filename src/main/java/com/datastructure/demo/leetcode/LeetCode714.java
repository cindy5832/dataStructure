package com.datastructure.demo.leetcode;

public class LeetCode714 {
    int maxProfit1(int[] prices, int fee) {
        int[] buy = new int[prices.length];
        int[] sell = new int[prices.length];
        buy[0] = -prices[0];
        sell[0] = 0;
        for (int i = 1; i < prices.length; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i] - fee);
        }
        return sell[prices.length - 1];
    }

    int maxProfit2(int[] prices, int fee) {
        int _buy = -prices[0];
        int _sell = 0;
        for (int i = 1; i < prices.length; i++) {
            int buy = Math.max(_buy, _sell - prices[i]);
            int sell = Math.max(_sell, _buy + prices[i] - fee);
            _buy = buy;
            _sell = sell;
        }
        return _sell;
    }

    int maxProfit(int[] prices, int fee) {
        int buy = -prices[0];
        int sell = 0;
        for (int i = 1; i < prices.length; i++) {
            buy = Math.max(buy, sell - prices[i]);
            sell = Math.max(sell, buy + prices[i] - fee);
        }
        return sell;
    }

    int maxProfit3(int[] prices, int fee) {
        int buy = Integer.MIN_VALUE;
        int sell = 0;
        for (int price : prices) {
            buy = Math.max(buy, sell - price);
            sell = Math.max(sell, sell - fee);
        }
        return sell;
    }

    public static void main(String[] args) {
        LeetCode714 leetcode714 = new LeetCode714();
        // 2次
        System.out.println(leetcode714.maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2)); // 8
//        System.out.println(leetcode714.maxProfit(new int[]{1, 3, 7, 2, 18, 3}, 3)); // 16
//        System.out.println(leetcode714.maxProfit(new int[]{2, 1, 4, 4, 2, 3, 2, 5, 1, 2}, 1)); // 4
//        System.out.println(leetcode714.maxProfit(new int[]{9, 3, 12, 1, 2, 3}, 1)); // 9
//        //  1次
//        System.out.println(leetcode714.maxProfit(new int[]{1, 3, 7, 5, 10, 3}, 3)); // 6
//        System.out.println(leetcode714.maxProfit(new int[]{1, 3, 7, 5, 10, 11, 3}, 3)); // 7
    }
}
