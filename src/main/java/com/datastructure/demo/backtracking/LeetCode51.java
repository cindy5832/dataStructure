package com.datastructure.demo.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode51 {
    /**
     * 0   1   2   3       0   -1  -2  -3      3   4   5   6
     * 1   2   3   4       1   0   -1  -2      2   3   4   5
     * 2   3   4   5       2   1   0   -1      1   2   3   4
     * 3   4   5   6       3   2   1   0       0   1   2   3
     **/
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        boolean[] column = new boolean[n]; // 列衝突
        boolean[] leftSlide = new boolean[2 * n - 1]; // 左斜衝突 i+j
        boolean[] rightSlide = new boolean[2 * n - 1]; // 右斜衝突 n-1 - (i-j)
        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }
        return dfs(0, n, board, column, leftSlide, rightSlide, result);
    }

    public int totalNQueens(int n) {
        boolean[] column = new boolean[n]; // 列衝突
        boolean[] leftSlide = new boolean[2 * n - 1]; // 左斜衝突 i+j
        boolean[] rightSlide = new boolean[2 * n - 1]; // 右斜衝突 n-1 - (i-j)
        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }
        return dfs2(0, n, board, column, leftSlide, rightSlide);
    }

    private List<List<String>> dfs(int i, int n, char[][] board, boolean[] column, boolean[] leftSlide, boolean[] rightSlide, List<List<String>> result) {
        if (i == n) {
            List<String> list = new ArrayList<>();
            for (char[] row : board) {
                list.add(new String(row));
            }
            result.add(list);
            return result;
        }
        for (int j = 0; j < n; j++) {
            if (column[j] || leftSlide[i + j] || rightSlide[n - 1 - (i - j)]) {
                continue;
            }
            board[i][j] = 'Q';
            column[j] = leftSlide[i + j] = rightSlide[n - 1 - (i - j)] = true;
            dfs(i + 1, n, board, column, leftSlide, rightSlide, result);
            board[i][j] = '.';
            column[j] = leftSlide[i + j] = rightSlide[n - 1 - (i - j)] = false;
        }
        return result;
    }
    int count = 0;
    private int dfs2(int i, int n, char[][] board, boolean[] column, boolean[] leftSlide, boolean[] rightSlide) {
        if (i == n) {
            return count++;
        }
        for (int j = 0; j < n; j++) {
            if (column[j] || leftSlide[i + j] || rightSlide[n - 1 - (i - j)]) {
                continue;
            }
            board[i][j] = 'Q';
            column[j] = leftSlide[i + j] = rightSlide[n - 1 - (i - j)] = true;
            dfs2(i + 1, n, board, column, leftSlide, rightSlide);
            board[i][j] = '.';
            column[j] = leftSlide[i + j] = rightSlide[n - 1 - (i - j)] = false;
        }
        return count;
    }

    public static void main(String[] args) {
        int n = 4;
//        boolean[] ca = new boolean[n]; // 紀錄衝突列
//        boolean[] cb = new boolean[2 * n - 1]; // 左斜線衝突
//        boolean[] cc = new boolean[2 * n - 1]; // 右斜線衝突
//        char[][] table = new char[n][n];
//        for (char[] chars : table) {
//            Arrays.fill(chars, '.'); // . 空 Q 皇后
//        }
//        dfs1(0, n, table, ca, cb, cc);
        LeetCode51 solver = new LeetCode51();
        System.out.println(solver.solveNQueens(n));
        System.out.println(solver.totalNQueens(n));
    }

    static void dfs1(int i, int n, char[][] table, boolean[] ca, boolean[] cb, boolean[] cc) {
        if (i == n) { // 找到解
            System.out.println("==========================");
            for (char[] chars : table) {
                System.out.println(new String(chars));
            }
            return;
        }
        for (int j = 0; j < n; j++) {
            if (ca[j] || cb[i + j] || cc[n - 1 - (i - j)]) continue;
            table[i][j] = 'Q';
            ca[j] = true;
            cb[i + j] = true;
            cc[n - 1 - (i - j)] = true;
            dfs1(i + 1, n, table, ca, cb, cc);
            table[i][j] = '.';
            ca[j] = false;
            cb[i + j] = false;
            cc[n - 1 - (i - j)] = false;
        }
    }
}
