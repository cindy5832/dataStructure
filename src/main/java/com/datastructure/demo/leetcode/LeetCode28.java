package com.datastructure.demo.leetcode;

public class LeetCode28 {
    int strStr(String str1, String str2) {
        char[] origin = str1.toCharArray();
        char[] pattern = str2.toCharArray();
        int i = 0;
        int j = 0;
        while (i <= origin.length - pattern.length) {
            for (j = 0; j < pattern.length; j++) {
                if (pattern[j] != origin[i + j]) {
                    break;
                }
            }
            if (j == pattern.length) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static void main(String[] args) {
        LeetCode28 l = new LeetCode28();
        System.out.println(l.strStr("aaacaaab", "aaab"));
    }
}
