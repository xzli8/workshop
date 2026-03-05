package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

public class _1020numEnclaves {

    public static class Solution1 {

        /**
         DFS: O(N), O(N)
         Note: (相似题："130.被围绕的区域")从边界出发遍历并标记，可以在原数组上标记，也可以用visited标记
         */
        public int numEnclaves(int[][] grid) {
            // 初始化
            this.m = grid.length; this.n = grid[0].length; this.grid = grid;

            // 以边界上的1为起点进行遍历
            for (int j = 0; j < n; j++) {
                if (grid[0][j] == 1) {
                    grid[0][j] = 2;
                    dfs(0, j);
                }
                if (grid[m - 1][j] == 1) {
                    grid[m - 1][j] = 2;
                    dfs(m - 1, j);
                }
            }
            for (int i = 0; i < m; i++) {
                if (grid[i][0] == 1) {
                    grid[i][0] = 2;
                    dfs(i, 0);
                }
                if (grid[i][n - 1] == 1) {
                    grid[i][n - 1] = 2;
                    dfs(i, n - 1);
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
            for (int[] dir : dirs) {
                int x = i + dir[0], y = j + dir[1];
                if (inArea(x, y) && grid[x][y] == 1) {
                    grid[x][y] = 2; // 在原数组上标记，将与边界相邻的1标记成2
                    dfs(x, y);
                }
            }
        }

        private boolean inArea(int i, int j) {
            return 0 <= i && i < m && 0 <= j && j < n;
        }

    }


    public static class Solution2 {

        /**
         BFS: O(N), O(N)
         */
        public int numEnclaves(int[][] grid) {
            // 初始化
            this.m = grid.length; this.n = grid[0].length; this.grid = grid;

            // 以边界上的1为起点进行遍历
            for (int j = 0; j < n; j++) {
                if (grid[0][j] == 1) bfs(0, j);
                if (grid[m - 1][j] == 1) bfs(m - 1, j);
            }
            for (int i = 0; i < m; i++) {
                if (grid[i][0] == 1) bfs(i, 0);
                if (grid[i][n - 1] == 1)  bfs(i, n - 1);
            }

            // 统计剩余1的数量
            int count = 0;
            for (int i = 1; i < m - 1; i++) {
                for (int j = 1; j < n - 1; j++) {
                    count += grid[i][j] == 1 ? 1 : 0;
                }
            }
            return count;
        }

        private int m, n;
        private int[][] grid, dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        private void bfs(int i, int j) {
            Queue<int[]> q = new ArrayDeque<>();
            q.offer(new int[] {i, j});
            grid[i][j] = 2;
            while (!q.isEmpty()) {
                int[] cur = q.poll();
                for (int[] dir : dirs) {
                    int x = cur[0] + dir[0], y = cur[1] + dir[1];
                    if (inArea(x, y) && grid[x][y] == 1) {
                        q.offer(new int[]{x, y});
                        grid[x][y] = 2;
                    }
                }
            }
        }

        private boolean inArea(int x, int y) {
            return 0 <= x && x < m && 0 <= y && y < n;
        }

    }


}
