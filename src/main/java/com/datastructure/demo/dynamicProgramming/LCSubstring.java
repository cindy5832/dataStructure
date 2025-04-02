package com.datastructure.demo.dynamicProgramming;

import static com.datastructure.demo.dynamicProgramming.ChangeMakingProblemLeetCode322.print;

// 求最常公共string
public class LCSubstring {
    static int lcs(String a, String b) {
        int[][] dp = new int[a.length()][b.length()];
        int max = 0;
        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < b.length(); j++) {
                if (a.charAt(i) == b.charAt(j)) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }
                    max = Integer.max(max, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
            print(dp, a, b);
        }
        return max;
    }

    static void print(int[][] dp, String a, String b) {
        System.out.print("\t");
        for (char c : a.toCharArray()) {
            System.out.print(c + " \t");
        }
        System.out.println();
        for (int i = 0; i < dp[0].length; i++) {
            System.out.print(b.charAt(i) + "\t");
            for (int[] ints : dp) {
                System.out.print(ints[i] + " \t");
            }
            System.out.println();
        }
        System.out.println("=================================");

    }

    /*
        h 	a 	p 	p 	y
    a	0 	1 	0 	0 	0
    p	0 	0 	2 	1 	0
    p	0 	0 	1 	3 	0
    e	0 	0 	0 	0 	0
    a	0 	1 	0 	0 	0
    l	0 	0 	0 	0 	0
    */
    public static void main(String[] args) {
        System.out.println(lcs("happy", "appeal"));
    }
}
