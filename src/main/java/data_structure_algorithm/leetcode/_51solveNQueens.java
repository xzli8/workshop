package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _51solveNQueens {

    public static class Solution1 {

        public List<List<String>> solveNQueens(int n) {
            // init chessboard
            char[][] chessboard = new char[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    chessboard[i][j] = '.';
                }
            }

            backtrace(n, 0, chessboard);
            return res;
        }

        private final List<List<String>> res = new ArrayList<>();
        private void backtrace(int n, int row, char[][] chessboard) {
            if (row == n) {
                List<String> tmp = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < n; j++) {
                        sb.append(chessboard[i][j]);
                    }
                    tmp.add(sb.toString());
                }
                res.add(tmp);
                return;
            }

            for (int col = 0; col < n; col++) {
                if (isValid(n, row, col, chessboard)) {
                    chessboard[row][col] = 'Q';
                    backtrace(n, row + 1, chessboard);
                    chessboard[row][col] = '.';
                }
            }
        }

        private boolean isValid(int n, int row, int col, char[][] chessboard) {
            for (int i = 0; i < row; i++) {
                if (chessboard[i][col] == 'Q') return false;
            }
            for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
                if (chessboard[i][j] == 'Q') return false;
            }
            for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
                if (chessboard[i][j] == 'Q') return false;
            }
            return true;
        }

    }

}
