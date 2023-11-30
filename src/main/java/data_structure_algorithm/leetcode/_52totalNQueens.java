package data_structure_algorithm.leetcode;

public class _52totalNQueens {

    public static class Solution1 {

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
