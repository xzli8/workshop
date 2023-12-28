package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _52totalNQueens {

    public class Solution1 {

        /**
         回溯
         时间复杂度：O(N!)
         空间复杂度：O(N^2)
         */
        public int totalNQueens(int n) {
            char[][] board = new char[n][n];
            for (int i = 0; i < n; i++) Arrays.fill(board[i], '.');
            backtrace(n, 0, board);
            return count;
        }

        private int count = 0;
        private void backtrace(int n, int row, char[][] board) {
            if (row == n) {
                count++;
                return;
            }

            for (int col = 0; col < n; col++) {
                if (isValid(n, row, col, board)) {
                    board[row][col] = 'Q';
                    backtrace(n, row + 1, board);
                    board[row][col] = '.';
                }
            }
        }

        private boolean isValid(int n, int row, int col, char[][] board) {
            for (int i = 0; i < row; i++) {
                if (board[i][col] == 'Q') return false;
            }
            for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
                if (board[i][j] == 'Q') return false;
            }
            for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
                if (board[i][j] == 'Q') return false;
            }
            return true;
        }

    }

    public static class Solution2 {

        // 回溯

        private int total = 0;
        private int[] pos;
        private int n;

        public int totalNQueens(int n) {
            this.n = n;
            pos = new int[n];
            dfs(0);
            return total;
        }

        // 第row行
        private void dfs(int row) {
            if (row == n) {
                total++;
                return;
            }

            // 第row行的Q有n种放法
            for (int col = 0; col < n; col++) {
                if (isValid(row, col)) {
                    pos[row] = col;
                    dfs(row+1);
                }
            }
        }

        // 检验Q放置在第row行第col列是否合理
        private boolean isValid(int row, int col) {
            int leftCol = col-1;
            int rightCol = col+1;

            for (int i = row-1; i >= 0; i--) {
                if (pos[i] == col) return false;
                if (leftCol >= 0 && pos[i] == leftCol) return false;
                if (rightCol < n && pos[i] == rightCol) return false;
                leftCol--;
                rightCol++;
            }
            return true;
        }

    }

}
