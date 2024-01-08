package data_structure_algorithm.leetcode;

public class _463islandPerimeter {

    public static class Solution1 {

        /**
         遍历：遍历岛屿上每一个位置，根据该位置相邻位置是边界/海洋/岛屿进行计算
         时间复杂度：O(M * N)
         空间复杂度：O(1)
         */
        public int islandPerimeter(int[][] grid) {
            // 初始化
            this.m = grid.length; this.n = grid[0].length;
            int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

            // 遍历
            int res = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        for (int[] dir : dirs) {
                            int x = i + dir[0], y = j + dir[1];
                            if (!inArea(x, y) || grid[x][y] == 0) {
                                res++;
                            }
                        }
                    }
                }
            }
            return res;
        }

        private int m, n;

        private boolean inArea(int i, int j) {
            return 0 <= i && i < m && 0 <= j && j < n;
        }


    }



    public static class Solution2 {

        /**
         DFS：从岛屿上任意位置出发，遍历并标记(可以在原数组上标记，也可以用visited标记)，计算周长
         时间复杂度：O(M * N)
         空间复杂度：O(M * N)
         */
        public int islandPerimeter(int[][] grid) {
            // 初始化
            this.m = grid.length; this.n = grid[0].length; this.grid = grid;

            // 遍历并计算
            int res = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        res += dfs(i, j);
                    }
                }
            }
            return res;
        }

        private int m, n;
        private int[][] grid;
        private int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        private int dfs(int i, int j) {
            if (!inArea(i, j) || grid[i][j] == 0) return 1;     // 遇到边界或海洋返回1
            if (grid[i][j] == 2) return 0;                      // 遇到访问过的陆地返回0
            grid[i][j] = 2;
            int res = 0;
            for (int[] dir : dirs) {
                res += dfs(i + dir[0], j + dir[1]);
            }
            return res;
        }

        private boolean inArea(int i, int j) {
            return 0 <= i && i < m && 0 <= j && j < n;
        }


    }

}
