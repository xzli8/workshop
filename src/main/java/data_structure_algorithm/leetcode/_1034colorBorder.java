package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _1034colorBorder {

    public static class Solution1 {

        /**
         DFS：遍历并标记
         时间复杂度：O(M * N)
         空间复杂度：O(M * N)
         */
        public int[][] colorBorder(int[][] grid, int row, int col, int color) {
            // 初始化
            this.m = grid.length; this.n = grid[0].length;
            this.src = grid[row][col]; this.color = color;
            this.visited = new boolean[m][n];
            this.grid = grid;
            // this.init = grid.clone();    // 默认是浅拷贝(shallow copy)
            this.init = new int[m][n];
            for (int i = 0; i < m; i++) init[i] = Arrays.copyOf(grid[i], n);

            // 遍历
            dfs(row, col);
            return grid;
        }

        private int m, n, src, color;
        private boolean[][] visited;
        private int[][] grid, init;
        private int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        private boolean inArea(int i, int j) {
            return 0 <= i && i < m && 0 <= j && j < n;
        }

        private boolean isBoundary(int i, int j) {
            for (int[] dir : dirs) {
                int x = i + dir[0], y = j + dir[1];
                if (!inArea(x, y) || init[x][y] != src) {
                    return true;
                }
            }
            return false;
        }

        private void dfs(int i, int j) {
            if (!inArea(i, j) || visited[i][j] || grid[i][j] != src) return;
            visited[i][j] = true;
            if (isBoundary(i, j)) grid[i][j] = color;
            for (int[] dir : dirs) {
                dfs(i + dir[0], j + dir[1]);
            }
        }


    }

}
