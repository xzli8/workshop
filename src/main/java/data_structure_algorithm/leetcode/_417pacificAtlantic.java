package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class _417pacificAtlantic {

    public static class Solution1 {
         /**
             DFS：模拟从每个单元格出发能否流入太平洋和大西洋
                 时间复杂度：O((M * N) ^ 2)
                 空间复杂度：O(M * N)
          */
         public List<List<Integer>> pacificAtlantic(int[][] heights) {
             // 初始化
             int m = heights.length, n = heights[0].length;
             int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
             boolean[][] resPacific = new boolean[m][n], resAtlantic = new boolean[m][n];

             // 遍历
             for (int i = 0; i < m; i++) {
                 for (int j = 0; j < n; j++) {
                     if (dfs(i, j, m, n, heights, dirs, new boolean[m][n], pos -> pos[0] == 0 || pos[1] == 0)) {
                         resPacific[i][j] = true;
                     }
                     if (dfs(i, j, m, n,  heights, dirs, new boolean[m][n], pos -> pos[0] == m - 1 || pos[1] == n - 1)) {
                         resAtlantic[i][j] = true;
                     }
                 }
             }

             // 结果
             List<List<Integer>> res = new ArrayList<>();
             for (int i = 0; i < m; i++) {
                 for (int j = 0; j < n; j++) {
                     if (resPacific[i][j] && resAtlantic[i][j]) {
                         res.add(Arrays.asList(i, j));
                     }
                 }
             }
             return res;
         }

         private boolean dfs(int i, int j, int m, int n, int[][] heights, int[][] dirs, boolean[][] visited, Predicate<int[]> onBoundary) {
             visited[i][j] = true;
             if (onBoundary.test(new int[] {i, j})) return true;

             for (int[] dir : dirs) {
                 int x = i + dir[0], y = j + dir[1];
                 if (inArea(x, y, m, n) && !visited[x][y] && heights[x][y] <= heights[i][j]) {
                     if (dfs(x, y, m, n, heights, dirs, visited, onBoundary)) return true;
                 }
             }
             return false;
         }

         private boolean inArea(int i, int j, int m, int n) {
             return 0 <= i && i < m && 0 <= j && j < n;
         }


    }



    public static class Solution2 {

        /**
         DFS：逆向思维，从海洋(边界)出发，看一个点是否能流到海洋，然后取交集
         时间复杂度：O(M * N)
         空间复杂度：O(M * N)
         */
        public List<List<Integer>> pacificAtlantic(int[][] heights) {
            // 初始化
            this.heights = heights;
            this.m = heights.length; this.n = heights[0].length;
            boolean[][] pacific = new boolean[m][n], atlantic = new boolean[m][n];

            // 遍历
            for (int i = 0; i < m; i++) dfs(i, 0, pacific);
            for (int j = 0; j < n; j++) dfs(0, j, pacific);
            for (int i = 0; i < m; i++) dfs(i, n - 1, atlantic);
            for (int j = 0; j < n; j++) dfs(m - 1, j, atlantic);

            // 结果
            List<List<Integer>> res = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (pacific[i][j] && atlantic[i][j]) {
                        res.add(Arrays.asList(i, j));
                    }
                }
            }
            return res;
        }

        private int m, n;
        private int[][] heights;
        private int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        private void dfs(int i, int j, boolean[][] ocean) {
            if (ocean[i][j]) return;
            ocean[i][j] = true;
            for (int[] dir : dirs) {
                int x = i + dir[0], y = j + dir[1];
                if (inArea(x, y) && heights[x][y] >= heights[i][j]) {
                    dfs(x, y, ocean);
                }
            }
        }

        private boolean inArea(int i, int j) {
            return 0 <= i && i < m && 0 <= j && j < n;
        }


    }



    public static class Solution3 {

        /**
         BFS
         */


    }



}
