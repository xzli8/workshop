package data_structure_algorithm.leetcode;

public class _733floodFill {

    public static class Solution1 {

        /**
         DFS
         时间复杂度：O(M * N)
         空间复杂度：O(M * N)
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

}
