package com.datastructure.demo.dynamicProgramming;


// leetcode 1143
public class LCSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    /**
     *          a   b   c   x   y   z
     *      0   0   0   0   0   0   0
     * a    0   1   1   1   1   1   1
     * b    0   1   2   2   2   2   2
     * x    0   1   2   2   3   3   3
     * y    0   1   2   2   3   4   4
     * z    0   1   2   2   3   4   5
     **/
    // 相同string
    //  找到上一行上一列數值+1
    // 不同string
    //  max(上一行, 上一列)
    public static void main(String[] args) {
        LCSubsequence lcSubsequence = new LCSubsequence();
        System.out.println(lcSubsequence.longestCommonSubsequence("abcxyz", "abxyz"));
    }
}
