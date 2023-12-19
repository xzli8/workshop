package data_structure_algorithm.leetcode;

public class _304sumRegion {


    /**
     * Your NumMatrix object will be instantiated and called as such:
     * NumMatrix obj = new NumMatrix(matrix);
     * int param_1 = obj.sumRegion(row1,col1,row2,col2);
     */

    public static class Solution1 {

        class NumMatrix {

            /**
             前缀和数组
             preSum[i][j]表示以(0, 0)为左上角，(i-1, j-1)为右下角的矩形元素和
             */
            private int[][] preSum;

            /**
             时间复杂度：O(N)
             空间复杂度：O(N)
             */
            public NumMatrix(int[][] matrix) {
                int m = matrix.length, n = matrix[0].length;
                preSum = new int[m+1][n+1];
                for (int i = 1; i <= m; i++) {
                    for (int j = 1; j <= n; j++) {
                        preSum[i][j] = preSum[i-1][j] + preSum[i][j-1]
                                - preSum[i-1][j-1] + matrix[i-1][j-1];
                    }
                }
            }

            /**
             时间复杂度：O(1)
             空间复杂度：O(1)
             */
            public int sumRegion(int row1, int col1, int row2, int col2) {
                return preSum[row2 + 1][col2 + 1] - preSum[row1][col2 + 1]
                        - preSum[row2 + 1][col1] + preSum[row1][col1];
            }

        }

    }

}
