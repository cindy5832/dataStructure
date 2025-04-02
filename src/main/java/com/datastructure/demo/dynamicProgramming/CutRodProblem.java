package com.datastructure.demo.dynamicProgramming;

import static com.datastructure.demo.dynamicProgramming.ChangeMakingProblemLeetCode322.print;

public class CutRodProblem {
    /**
     * 0    1   2   3   4   5   6   7   8   9   10
     * 0    1   5   8   9   10  17  17  20  24  30
     * <p>
     * 0   1   2   3   4
     * 1       1   11  111 1111
     * (1) (2) (3) (4)
     * <p>
     * 2   0   1   11  111 1111
     * 2   21  22
     * 211
     * (1) (5) (6) (10)
     * <p>
     * 3   0   1   11  111 1111
     * 2   21  211
     * 3   22
     * 31
     * (1) (5) (8) (10)
     * <p>
     * 4    0   1   11  111 1111
     * 2   21  211
     * 3   22
     * 31
     * 4
     * (1) (5) (8) (10)
     **/

    static int cut(int[] value, int n) {
        int[][] dp = new int[value.length][n + 1];
        for (int i = 1; i < value.length; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (j >= i) { // 放得下
                    dp[i][j] = Integer.max(dp[i - 1][j], value[i] + dp[i][j - i]);
                } else { // 放不下
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        print(dp);
        return dp[value.length - 1][n];
    }

    public static void main(String[] args) {
        // 不同長度對應的鋼條價值數組
        System.out.println(cut(new int[]{0, 1, 5, 8, 9}, 4));
    }
}
