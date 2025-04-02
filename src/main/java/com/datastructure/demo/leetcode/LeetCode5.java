package com.datastructure.demo.leetcode;

public class LeetCode5 {
    String longestPalindrome(String s) {
        left = 0;
        right = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length - 1; i++) {
            extend(chars, i, i);
            extend(chars, i, i + 1);
        }
        return new String(chars, left, right - left + 1);
    }

    static int left; // i
    static int right; // j

    void extend(char[] chars, int i, int j) {
        while (i >= 0 && j < chars.length && chars[i] == chars[j]) {
            i--;
            j++;
        }
        // 不是回文
        i++;
        j--;
        if (j - i > right - left) {
            left = i;
            right = j;
        }
    }

    public static void main(String[] args) {
        LeetCode5 leetCode5 = new LeetCode5();
        System.out.println(leetCode5.longestPalindrome("babad"));
        System.out.println(leetCode5.longestPalindrome("cbbd"));
        System.out.println(leetCode5.longestPalindrome("ccc"));
    }
}
