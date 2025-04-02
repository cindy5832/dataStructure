package com.datastructure.demo.leetcode;

public class LeetCode123 {
    /**
     * 第1次買 不依賴之前狀態，以當日價格買入
     * 第1次賣，依賴於昨天第1次買 + 當日價格
     * 第2次買，依賴於昨天第1次賣 - 當日價格
     * 第2次賣，依賴於昨天第2次買 + 當日價格
     **/
    int maxProfit(int[] prices) {
        int buy1 = Integer.MIN_VALUE;
        int sell1 = 0;
        int buy2 = Integer.MIN_VALUE;
        int sell2 = 0;
        for (int price : prices) {
            buy1 = Math.max(buy1, -price);
            sell1 = Math.max(sell1, buy1 + price);
            buy2 = Math.max(buy2, sell1 - price);
            sell2 = Math.max(sell2, buy2 + price);
        }
        return sell2;
    }

    public static void main(String[] args) {
        LeetCode123 leetcode123 = new LeetCode123();
        System.out.println(leetcode123.maxProfit(new int[]{3, 2, 6, 5, 0, 3})); // 7
        System.out.println(leetcode123.maxProfit(new int[]{3, 3, 5, 0, 0, 3, 1, 4})); // 6
        System.out.println(leetcode123.maxProfit(new int[]{1, 2, 0, 1, 0, 3, 1, 4, 5})); // 6
    }
}
