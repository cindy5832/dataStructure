package com.datastructure.demo.divideAndConquer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LeetCode395 {
    static int longestSubstring(String s, int k) {
        if (s.length() < k) return 0;
        int[] count = new int[26]; // 索引對應string 用來存儲該string出現次數
        char[] chars = s.toCharArray();
        for (char c : chars) {
            count[c - 'a']++;
        }
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            int n = count[c - 'a'];
            if (n > 0 && n < k) {
                int j = i + 1;
                while (j < s.length() && count[chars[j] - 'a'] < k) {
                    j++;
                }
                return Integer.max(longestSubstring(s.substring(0, i), k), longestSubstring(s.substring(j), k));
            }
        }
        return s.length();
    }


    public static void main(String[] args) {
        System.out.println(longestSubstring("aaabb", 3));
        System.out.println(longestSubstring("ababbc", 2));

        System.out.println(longestSubstring("dddxaabaaabaacciiiiebff", 3));

    }
    // 統計string每個char出現次數，移除出去次數 < k 的char
    // 剩餘string 遞歸處理，直到
    // 1. 整個Strgin < k (排除)
    // 2. 子串沒有出現次數 < k 的string
}
