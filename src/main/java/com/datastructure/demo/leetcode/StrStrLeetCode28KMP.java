package com.datastructure.demo.leetcode;

public class StrStrLeetCode28KMP {
    int strStr(String str1, String str2) {
        char[] origin = str1.toCharArray();
        char[] pattern = str2.toCharArray();
        int[] lps = lps(pattern); // 最長前後綴數組
        /**
         * 1. 匹配成功 i++ j++ 直到 j==模式字符串長度
         * 2. 匹配失敗
         *      j != 0 跳過最長前後綴字符，繼續匹配
         *      j == 0 則 i++
         **/
        int i = 0;
        int j = 0;
        while (pattern.length - j <= origin.length - i) {
            if (origin[i] == pattern[j]) {
                i++;
                j++;
            } else if (j == 0) {
                i++;
            } else {
                j = lps[j - 1];
            }
            if (j == pattern.length) {
                // 找到解
                return i - pattern.length;
            }
        }
        return -1;
    }

    /**
     * 最長前後綴數組：只跟模式字符串相關
     * 1. 索引：使用了模式字符串前j個字符串-1
     * 2. 值：最長前後綴的長度 (恰好是j要跳轉的位置)
     **/
    private int[] lps(char[] pattern) {
        int[] lps = new int[pattern.length];
        int i = 1;
        int j = 0;
        while (i < pattern.length) {
            if (pattern[i] == pattern[j]) {
                lps[i] = j + 1;
                i++;
                j++;
            } else if (j == 0) {
                i++;
            } else {
                j = lps[j - 1];
            }
        }
        return lps;
    }

    public static void main(String[] args) {
        StrStrLeetCode28KMP solution = new StrStrLeetCode28KMP();
        System.out.println(solution.strStr("ababababcabacacababaca", "ababaca"));
        System.out.println("ababababcabacacababaca".indexOf("ababaca"));
    }
}
