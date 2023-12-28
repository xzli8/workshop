package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _51solveNQueens {

    public static class Solution1 {

        /**
         回溯
         时间复杂度：O(N!)
         空间复杂度：O(N^2)
         */
        public List<List<String>> solveNQueens(int n) {
            char[][] board = new char[n][n];
            for (int i = 0; i < n; i++) Arrays.fill(board[i], '.');
            backtrace(board, n, 0);
            return res;
        }

        private List<List<String>> res = new ArrayList<>();
        private void backtrace(char[][] board, int n, int row) {
            if (row == n) {
                List<String> tmp = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < n; j++) {
                        sb.append(board[i][j]);
                    }
                    tmp.add(sb.toString());
                }
                res.add(tmp);
                return;
            }

            for (int col = 0; col < n; col++) {
                if (isValid(board, n, row, col)) {
                    board[row][col] = 'Q';
                    backtrace(board, n, row + 1);
                    board[row][col] = '.';
                }
            }
        }

        private boolean isValid(char[][] board, int n, int row, int col) {
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

}
