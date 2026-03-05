package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _1034colorBorder {

    public static class Solution1 {

        /**
         DFS：O(M * N), O(M * N)
         Note: 在DFS/BFS遍历的过程中修改颜色，需要注意修改颜色后可能会影响对于边界的判断，所以需要单独copy一份原始布局。
            case: grid [[1,2,1,2,1,2],[2,2,2,2,1,2],[1,2,2,2,1,2]] row 1 col 3 color 1
         也可以在遍历过程中不修改颜色，只是将需要修改颜色的位置记录下来，遍历完后再修改颜色，可以避免上述问题。
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


    public static class Solution2 {

        /**
         DFS2：O(M * N), O(M * N)
         Note: 在DFS/BFS遍历的过程中修改颜色，需要注意修改颜色后可能会影响对于边界的判断，所以需要单独copy一份原始布局。
            case: grid [[1,2,1,2,1,2],[2,2,2,2,1,2],[1,2,2,2,1,2]] row 1 col 3 color 1
         也可以在遍历过程中不修改颜色，只是将需要修改颜色的位置记录下来，遍历完后再修改颜色，可以避免上述问题。
         */
        public int[][] colorBorder(int[][] grid, int row, int col, int color) {
            // 初始化
            this.m = grid.length; this.n = grid[0].length; this.src = grid[row][col];
            this.visited = new boolean[m][n]; this.grid = grid;

            // 遍历
            dfs(row, col);

            // 染色
            for (int[] point : points) {
                grid[point[0]][point[1]] = color;
            }
            return grid;
        }

        private int m, n, src;
        private boolean[][] visited;
        private int[][] grid;
        private int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        private List<int[]> points = new ArrayList<>();

        private boolean inArea(int i, int j) {
            return 0 <= i && i < m && 0 <= j && j < n;
        }

        private boolean isBoundary(int i, int j) {
            for (int[] dir : dirs) {
                int x = i + dir[0], y = j + dir[1];
                if (!inArea(x, y) || grid[x][y] != src) {
                    return true;
                }
            }
            return false;
        }

        private void dfs(int i, int j) {
            if (!inArea(i, j) || visited[i][j] || grid[i][j] != src) return;
            visited[i][j] = true;
            if (isBoundary(i, j)) points.add(new int[] {i, j});
            for (int[] dir : dirs) {
                dfs(i + dir[0], j + dir[1]);
            }
        }

    }


    public static class Solution3 {

        /**
         * BFS
         */

    }

}
