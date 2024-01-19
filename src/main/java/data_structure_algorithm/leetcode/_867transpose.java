package data_structure_algorithm.leetcode;

public class _867transpose {

    public static class Solution1 {

        /**
         模拟：交换行和列
         时间复杂度：O(M * N)
         空间复杂度：O(1)
         */
        public int[][] transpose(int[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            int[][] res = new int[n][m];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    res[j][i] = matrix[i][j];
                }
            }
            return res;
        }

    }

}
