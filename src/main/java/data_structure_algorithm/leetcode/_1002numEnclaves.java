package data_structure_algorithm.leetcode;

public class _1002numEnclaves {

    public static class Solution1 {

        /**
         DFS(相似题："130.被围绕的区域")：从边界出发遍历并标记，可以在原数组上标记，也可以用visited标记
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int numEnclaves(int[][] grid) {
            // 初始化
            this.m = grid.length; this.n = grid[0].length; this.grid = grid;

            // 遍历
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                        dfs(i, j);
                    }
                }
            }

            // 计算飞地(剩余1)数量
            int num = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) num++;
                }
            }
            return num;
        }

        private int m, n;
        private int[][] grid;
        private int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        private void dfs(int i, int j) {
            if (!inArea(i, j) || grid[i][j] != 1) return;
            grid[i][j] = 2;
            for (int[] dir : dirs) {
                dfs(i + dir[0], j + dir[1]);
            }
        }

        private boolean inArea(int i, int j) {
            return 0 <= i && i < m && 0 <= j && j < n;
        }


    }


}
