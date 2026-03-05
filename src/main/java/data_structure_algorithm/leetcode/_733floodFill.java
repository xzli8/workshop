package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class _733floodFill {

    public static class Solution1 {

        /**
         DFS: O(M * N), O(M * N)
         */
        public int[][] floodFill(int[][] image, int sr, int sc, int color) {
            // 初始化
            this.m = image.length; this.n = image[0].length; this.src = image[sr][sc]; this.color = color;
            this.image = image; this.visited = new boolean[m][n];

            // 遍历
            dfs(sr, sc, visited);
            return image;
        }

        private int m, n, src, color;
        private boolean[][] visited;
        private int[][] image;
        private int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        private boolean inArea(int i, int j) {
            return 0 <= i && i < m && 0 <= j && j < n;
        }

        private void dfs(int i, int j, boolean[][] visited) {
            if (!inArea(i, j) || visited[i][j] || image[i][j] != src) return;
            visited[i][j] = true;
            image[i][j] = color;
            for (int[] dir : dirs) {
                dfs(i + dir[0], j + dir[1], visited);
            }
        }

    }


    public static class Solution2 {

        /**
         BFS: O(M * N), O(M * N)
         */
        public int[][] floodFill(int[][] image, int sr, int sc, int color) {
            int m = image.length, n = image[0].length, srcColor = image[sr][sc];
            int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            boolean[][] visited = new boolean[m][n];
            for (int i = 0; i < m; i++) Arrays.fill(visited[i], false);

            Queue<int[]> q = new ArrayDeque<>();
            q.offer(new int[] {sr, sc});
            visited[sr][sc] = true;
            image[sr][sc] = color;
            while (!q.isEmpty()) {
                int[] cur = q.poll();
                for (int[] dir : dirs) {
                    int x = cur[0] + dir[0], y = cur[1] + dir[1];
                    if (0 <= x && x < m && 0 <= y && y < n && !visited[x][y] && image[x][y] == srcColor) {
                        q.offer(new int[] {x, y});
                        visited[x][y] = true;
                        image[x][y] = color;
                    }
                }
            }
            return image;
        }

    }

}
