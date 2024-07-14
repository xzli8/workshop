package data_structure_algorithm.leetcode;

public class _807maxIncreaseKeepingSkyline {

    public static class Solution1 {

        /**
         贪心：对于每个grid[i][j]，取i, j两个方向中最小的最大高度
         时间复杂度：O(N^3)
         空间复杂度：O(1)
         */
        public int maxIncreaseKeepingSkyline(int[][] grid) {
            int n = grid.length, sum = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int iMax = 0, jMax = 0;
                    for (int ii = 0; ii < n; ii++) {
                        iMax = Math.max(iMax, grid[ii][j]);
                    }
                    for (int jj = 0; jj < n; jj++) {
                        jMax = Math.max(jMax, grid[i][jj]);
                    }
                    sum += Math.min(iMax, jMax) - grid[i][j];
                }
            }
            return sum;
        }

    }

}
