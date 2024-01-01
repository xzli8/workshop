package data_structure_algorithm.leetcode;

import org.junit.Test;

public class _1444ways {



    public static class Solution1 {

        @Test
        public void test() {
            System.out.println(ways(new String[] {"A..","AAA","..."}, 3));
        }

        /**
         DFS：超出时间限制
         */
        public int ways(String[] pizza, int k) {
            return dfs(pizza.length, pizza[0].length(), new MatrixSum(pizza), k - 1, 0, 0);
        }

        public static final int MOD = (int) 1e9 + 7;
        private int dfs(int m, int n, MatrixSum ms, int count, int row, int col) {
            if (count == 0) return ms.sumRegion(row, col, m, n) > 0 ? 1 : 0;

            int sum = 0;
            // 水平切
            for (int i = row + 1; i < m; i++) {
                // 切出去的部分有苹果
                if (ms.sumRegion(row, col, i, n) > 0) {
                    sum = (sum + dfs(m, n, ms, count - 1, i, col)) % MOD;
                }
            }
            // 垂直切
            for (int j = col + 1; j < n; j++) {
                if (ms.sumRegion(row, col, m, j) > 0) {
                    sum = (sum + dfs(m, n, ms, count - 1, row, j)) % MOD;
                }
            }
            return sum;
        }

        /**
         计算二维数组的区间和(304.二维区域和检索-数组不可变)
         时间复杂度：O(1)
         */
        class MatrixSum {

            private final int[][] preSum;

            public MatrixSum(String[] matrix) {
                int m = matrix.length, n = matrix[0].length();
                preSum = new int[m + 1][n + 1];
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        int num = matrix[i].charAt(j) == 'A' ? 1 : 0;
                        preSum[i + 1][j + 1] = preSum[i + 1][j] + preSum[i][j + 1] - preSum[i][j] + num;
                    }
                }
            }

            public int sumRegion(int row1, int col1, int row2, int col2) {
                return preSum[row2][col2] - preSum[row2][col1] - preSum[row1][col2] + preSum[row1][col1];
            }

        }

    }



    public static class Solution2 {

        /**
         DFS + memo
         */
        public int ways(String[] pizza, int k) {
            int m = pizza.length, n = pizza[0].length();
            memo = new Integer[k][m][n];
            return dfs(m, n, new MatrixSum(pizza), k - 1, 0, 0);
        }

        private static final int MOD = (int) 1e9 + 7;
        private Integer[][][] memo;
        private int dfs(int m, int n, MatrixSum ms, int count, int row, int col) {
            if (count == 0) return ms.sumRegion(row, col, m, n) > 0 ? 1 : 0;
            if (memo[count][row][col] != null) return memo[count][row][col];

            int sum = 0;
            // 水平切
            for (int i = row + 1; i < m; i++) {
                // 切出去的部分有苹果
                if (ms.sumRegion(row, col, i, n) > 0) {
                    sum = (sum + dfs(m, n, ms, count - 1, i, col)) % MOD;
                }
            }
            // 垂直切
            for (int j = col + 1; j < n; j++) {
                if (ms.sumRegion(row, col, m, j) > 0) {
                    sum = (sum + dfs(m, n, ms, count - 1, row, j)) % MOD;
                }
            }
            memo[count][row][col] = sum;
            return sum;
        }

        /**
         计算二维数组的区间和(304.二维区域和检索-数组不可变)
         时间复杂度：O(1)
         */
        class MatrixSum {

            private final int[][] preSum;

            public MatrixSum(String[] matrix) {
                int m = matrix.length, n = matrix[0].length();
                preSum = new int[m + 1][n + 1];
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        int num = matrix[i].charAt(j) == 'A' ? 1 : 0;
                        preSum[i + 1][j + 1] = preSum[i + 1][j] + preSum[i][j + 1] - preSum[i][j] + num;
                    }
                }
            }

            public int sumRegion(int row1, int col1, int row2, int col2) {
                return preSum[row2][col2] - preSum[row2][col1] - preSum[row1][col2] + preSum[row1][col1];
            }

        }

    }



    public static class Solution3 {

    }


}
