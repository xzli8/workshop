package data_structure_algorithm.leetcode;

public class _48rotate {

    public static class Solution0 {

        /**
         模拟(转置 + 逐行反转)
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public void rotate(int[][] matrix) {
            // 转置
            int n = matrix.length;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    int tmp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = tmp;
                }
            }

            // 逐行反转
            for (int i = 0; i < n; i++) {
                int left = 0, right = n - 1;
                while (left < right) {
                    int tmp = matrix[i][left];
                    matrix[i][left] = matrix[i][right];
                    matrix[i][right] = tmp;
                    left++;
                    right--;
                }
            }
        }

    }

}
