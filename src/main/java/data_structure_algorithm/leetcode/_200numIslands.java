package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

public class _200numIslands {

    /**
     * ref: https://leetcode.cn/problems/number-of-islands/solutions/211211/dao-yu-lei-wen-ti-de-tong-yong-jie-fa-dfs-bian-li-/
     */

    public static class Solution1 {

        /**
         DFS：从岛屿的任意点出发遍历标记(可以直接在原数组上标记，也可以用visited数组标记)
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int numIslands(char[][] grid) {
            // 初始化
            this.m = grid.length; this.n = grid[0].length; this.grid = grid;

            // 遍历并计数
            int nums = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
                        nums++;
                        dfs(i, j);
                    }
                }
            }
            return nums;
        }

        private int m, n;
        private char[][] grid;
        private int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        private void dfs(int i, int j) {
            if (!inArea(i, j) || grid[i][j] != '1') return;
            grid[i][j] = '2';
            for (int[] dir : dirs) {
                dfs(i + dir[0], j + dir[1]);
            }
        }

        private boolean inArea(int i, int j) {
            return 0 <= i && i < m && 0 <= j && j < n;
        }

    }



    public static class Solution2 {

        /**
         BFS：从岛屿的任意点出发遍历标记(可以直接在原数组上标记，也可以用visited数组标记)
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int numIslands(char[][] grid) {
            // 初始化
            this.m = grid.length; this.n = grid[0].length; this.grid = grid;

            // 遍历并计数
            int nums = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
                        nums++;
                        bfs(i, j);
                    }
                }
            }
            return nums;
        }

        private int m, n;
        private char[][] grid;
        private int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        private void bfs(int i, int j) {
            grid[i][j] = '2';
            Queue<int[]> q = new ArrayDeque<>();
            q.offer(new int[] {i, j});
            while (!q.isEmpty()) {
                int[] cur = q.poll();
                for (int[] dir : dirs) {
                    int x = cur[0] + dir[0], y = cur[1] + dir[1];
                    if (inArea(x, y) && grid[x][y] == '1') {
                        grid[x][y] = '2';
                        q.offer(new int[] {x, y});
                    }
                }
            }
        }

        private boolean inArea(int i, int j) {
            return 0 <= i && i < m && 0 <= j && j < n;
        }

    }

}
