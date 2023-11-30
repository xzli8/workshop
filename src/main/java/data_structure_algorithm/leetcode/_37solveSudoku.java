package data_structure_algorithm.leetcode;

public class _37solveSudoku {

    public static class Solution1 {

        // 回溯：按行枚举
        public void solveSudoku(char[][] board) {
            backtrace(board, 0, 0);
        }

        private boolean backtrace(char[][] board, int row, int col) {
            if (row == 9) return true;  // 找到一个解
            if (col == 9) return backtrace(board, row + 1, 0);  // 枚举到最后一列换行
            if (board[row][col] != '.') return backtrace(board, row, col + 1);  // 该位置为预设数字，跳过

            for (char c = '1'; c <= '9'; c++) {
                if (isValid(board, row, col, c)) {
                    board[row][col] = c;
                    // 不能直接返回下一层的结果，如果下一层返回了false，这一层要进行回溯
                    if (backtrace(board, row, col + 1)) return true;
                    board[row][col] = '.';
                }
            }
            return false;   // 枚举完'1'-'9'，仍然没找到解，此路不通返回false
        }

        private boolean isValid(char[][] board, int row, int col, char c) {
            for (int i = 0; i < 9; i++) {
                if (board[i][col] == c) return false;   // 行内唯一
                if (board[row][i] == c) return false;   // 列内唯一
                if (board[(row/3)*3 + i/3][(col/3)*3 + i%3] == c) return false; // 宫内唯一
            }
            return true;
        }

    }

}
