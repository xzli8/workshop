package data_structure_algorithm.leetcode;

public class _696maxAreaOfIsland {

    public static class Solution1 {

        /**
         DFS：从任意岛屿上出发，遍历并标记(可以在原数组上标记，也可以用visited标记)
         时间复杂度：O(M * N)
         空间复杂度：O(M * N)
         */
        public int maxAreaOfIsland(int[][] grid) {
            // 初始化
            this.m = grid.length; this.n = grid[0].length; this.grid = grid;

            // 遍历
            int maxArea = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    maxArea = Math.max(maxArea, dfs(i, j));
                }
            }
            return maxArea;
        }

        private int m, n;
        private int[][] grid;
        private int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        private int dfs(int i, int j) {
            if (!inArea(i, j) || grid[i][j] != 1) return 0;
            grid[i][j] = 2;
            int area = 1;
            for (int[] dir : dirs) {
                area += dfs(i + dir[0], j + dir[1]);
            }
            return area;
        }

        private boolean inArea(int i, int j) {
            return 0 <= i && i < m && 0 <= j && j < n;
        }

    }

}
