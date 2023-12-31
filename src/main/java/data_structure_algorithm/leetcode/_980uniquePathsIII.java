package data_structure_algorithm.leetcode;

import org.junit.Test;

public class _980uniquePathsIII {

    public static class Solution1 {

        /**
         回溯
         */
        public int uniquePathsIII(int[][] grid) {
            // 初始化
            int startRow = 0, startCol = 0, spaceCount = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 0) {
                        spaceCount++;
                    } else if (grid[i][j] == 1) {
                        spaceCount++;
                        startRow = i;
                        startCol = j;
                    }
                }
            }

            // 回溯
            backtrace(grid, startRow, startCol, spaceCount);
            return pathCount;
        }

        private int pathCount = 0;
        private int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        private void backtrace(int[][] grid, int row, int col, int spaceCount) {
            if (grid[row][col] == 2) {
                if (spaceCount == 0) pathCount++;
                return;
            }

            int m = grid.length, n = grid[0].length;
            int origin = grid[row][col];
            grid[row][col] = -1;
            for (int[] dir : dirs) {
                int x = row + dir[0], y = col + dir[1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] != -1) {
                    backtrace(grid, x, y, spaceCount - 1);
                }
            }
            grid[row][col] = origin;
        }

    }

}
