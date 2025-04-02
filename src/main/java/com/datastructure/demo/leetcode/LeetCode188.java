package com.datastructure.demo.leetcode;

import java.util.Arrays;

public class LeetCode188 {
    int maxProfit(int k, int[] prices) {
        if (k > prices.length / 2) {
            return maxProfit(prices);
        }

        int[] buy = new int[k];
        int[] sell = new int[k];
        Arrays.fill(buy, Integer.MIN_VALUE);
        Arrays.fill(sell, 0);

        for (int price : prices) {
            buy[0] = Math.max(buy[0], -price);
            sell[0] = Math.max(sell[0], buy[0] + price);

            for (int i = 1; i < k; i++) {
                buy[i] = Math.max(buy[i], sell[i - 1] - price);
                sell[i] = Math.max(sell[i], buy[i] + price);
            }
        }
        return sell[k - 1];
    }
    /**
     * 第1次買 不依賴之前狀態，以當日價格買入
     * 第1次賣，依賴於昨天第1次買 + 當日價格
     * 第2次買，依賴於昨天第1次賣 - 當日價格
     * 第2次賣，依賴於昨天第2次買 + 當日價格
     **/

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
        LeetCode188 leetcode188 = new LeetCode188();
        System.out.println(leetcode188.maxProfit(2, new int[]{3, 2, 6, 5, 0, 3})); // 7
        System.out.println(leetcode188.maxProfit(2, new int[]{3, 3, 5, 0, 0, 3, 1, 4})); // 6
        System.out.println(leetcode188.maxProfit(4, new int[]{1, 2, 0, 1, 0, 3, 1, 4, 5})); // 9
    }
}
