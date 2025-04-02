package com.datastructure.demo.dynamicProgramming;

import java.util.Arrays;

public class TravellingSalesmanProblem {
    /**
     * 0   1   2   3
     * 0  0   1   2   3
     * 1  1   0   6   4
     * 2  2   6   0   5
     * 3  3   4   5   0
     * <p>
     * d(出發位置, 剩餘地點集合) ==> 從出發城市開始，走完剩餘位置，花費最少代價
     * d(0, 1|2|3) => g[0][1] + d(1,2|3)
     * g[1][3] + d(3,2)
     * g[3][2] + d(2,null)
     * g[2][0]
     * g[1][2] + d(2,3)
     * g[0][2] + d(2,1|3)
     * g[0][3] + d(3,1|2)
     * 出發位置 i
     * 剩餘位置集合 j
     * 遍歷 j 時的變量 k (剩餘的某一城市)
     * d(i, j) =>  min(
     * g[i][k] + d(k, j去除k)
     * g[i][k] + d(k, j去除k)
     * g[i][k] + d(k, j去除k)
     * )
     * <p>
     * 0       1   2   3       4   5    6    7
     * city  null    1   2   1|2     3   1|3  2|3  1|2|3
     * 0
     * 1
     * 2
     * 3
     * <p>
     * 000 沒城市
     * 001 1號
     * 010 2號
     * 100 3號
     * 011 1&2
     * 101 1&3
     **/
    public static void main(String[] args) {
        int[][] graph = {
                {0, 1, 2, 3},
                {1, 0, 6, 4},
                {2, 6, 0, 5},
                {3, 4, 5, 0}
        };
        TravellingSalesmanProblem problem = new TravellingSalesmanProblem();
        System.out.println(problem.tsp(graph));
    }

    int tsp(int[][] g) {
        int m = g.length; // 城市數量
        int n = 1 << (m - 1); // 剩餘城市的組合數 2^(m-1)
        int[][] dp = new int[m][n];
        for (int k = 0; k < m; k++) {
            dp[k][0] = g[k][0];
        }

        for (int j = 1; j < n; j++) {
            for (int i = 0; i < m; i++) {
                dp[i][j] = Integer.MAX_VALUE / 2;
                if (contains(j, i)) { // 剩餘城市集合包含以出發城市
                    continue;
                }
                // 填充單元格
                for (int k = 0; k < m; k++) {
                    if (contains(j, k)) {
                        dp[i][j] = Integer.min(dp[i][j], g[i][k] + dp[k][exclude(j, k)]);
                    }
                }
            }
        }
        print(dp);
        return dp[0][n-1];
    }

    private boolean contains(int set, int city) {
        return (set >> (city - 1) & 1) == 1;
    }

    private int exclude(int set, int city) {
        return set ^ (1 << (city - 1));
    }

    private void print(int[][] dp) {
        for (int[] row : dp) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("==============================");
    }
}
