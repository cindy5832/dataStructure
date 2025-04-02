package com.datastructure.demo.backtracking;

public class LeetCode37 {
    public static void main(String[] args) {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        LeetCode37 leetcode37 = new LeetCode37();
        leetcode37.solveSudoku1(board);
        for (char[] chars : board) {
            for (char aChar : chars) {
                System.out.printf("\t" + aChar);
            }
            System.out.println();
        }
    }

    private boolean dfs(int i, int j, char[][] table, boolean[][] ca, boolean[][] cb, boolean[][] cc) {
        while (table[i][j] != '.') { // 找下一個空格
            if (++j >= 9) {
                j = 0;
                i++;
            }
            if (i >= 9) {
                return true; // 找到解
            }
        }
        // 填空
        for (int x = 1; x <= 9; x++) {
            // 檢查衝突
            if (ca[i][x - 1] || cb[j][x - 1] || cc[i / 3 * 3 + j / 3][x - 1]) {
                continue;
            }
            table[i][j] = (char) (x + '0');
            ca[i][x - 1] = true;
            cb[j][x - 1] = true;
            cc[i / 3 * 3 + j / 3][x - 1] = true;
            if (dfs(i, j, table, ca, cb, cc)) {
                return true;
            }
            table[i][j] = '.';
            ca[i][x - 1] = false;
            cb[j][x - 1] = false;
            cc[i / 3 * 3 + j / 3][x - 1] = false;
        }
        return false;
    }

    public void solveSudoku(char[][] table) {
        // 行衝突 ca[i]
        // ca[0] = {false,false,true,false,true,,false,true,,false,false}
        boolean[][] ca = new boolean[9][9];
        // 列衝突 cb[j]
        // cb[0] = {false,false,false,true,true,true,true,true,false}
        boolean[][] cb = new boolean[9][9];
        // 九宮格衝突         0   1   2
        //  九宮格位置        3   4   5
        //  i/3*3 + j/3     6   7   8
        boolean[][] cc = new boolean[9][9];
        // cc[i/3*3+j/3]
        // cc[0] = {false,false,true,false,true,true,false,true,true}

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char ch = table[i][j];
                // 初始化衝突狀態
                if (ch != '.') {
                    ca[i][ch - '1'] = true;
                    cb[j][ch - '1'] = true;
                    cc[i / 3 * 3 + j / 3][ch - '1'] = true;
                }
            }
        }
        dfs(0, 0, table, ca, cb, cc);
    }

    public void solveSudoku1(char[][] board) {
        boolean[][] row = new boolean[9][9]; // row[i]
        boolean[][] col = new boolean[9][9]; // col[j]
        boolean[][] squ = new boolean[9][9]; // squ[1/3*3 + j/3]
        // 初始化
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char ch = board[i][j];
                if (board[i][j] != '.') {
                    row[i][ch - '1'] = col[j][ch - '1'] = squ[i / 3 * 3 + j / 3][ch - '1'] = true;
                }
            }
        }
        dfs1(0, 0, board, row, col, squ);
    }

    private boolean dfs1(int i, int j, char[][] board, boolean[][] row, boolean[][] col, boolean[][] squ) {
        while (board[i][j] != '.') { // 找下一個空格
            if (++j >= 9) {
                j = 0;
                i++;
            }
            if (i >= 9) {
                return true; // 找到解
            }
        }
        // 填空
        for (int x = 1; x <= 9; x++) {
            // 檢查衝突
            if (row[i][x - 1] || col[j][x - 1] || squ[i / 3 * 3 + j / 3][x - 1]) {
                continue;
            }
            board[i][j] = (char) (x + '0');
            row[i][x - 1] = true;
            col[j][x - 1] = true;
            squ[i / 3 * 3 + j / 3][x - 1] = true;
            if (dfs(i, j, board, row, col, squ)) {
                return true;
            }
            board[i][j] = '.';
            row[i][x - 1] = false;
            col[j][x - 1] = false;
            squ[i / 3 * 3 + j / 3][x - 1] = false;
        }
        return false;
    }

}
