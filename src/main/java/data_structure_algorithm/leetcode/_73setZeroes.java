package data_structure_algorithm.leetcode;

public class _73setZeroes {

    public static class Solution1 {

        /**
         如果一边遍历一边设置0，非原始0会对后面的设置产生干扰，造成结果错误。所以需要首先遍历矩阵，记录原始0的位置。
         时间复杂度：O(M * N)
         空间复杂度：O(M * N)
         */
         public void setZeroes(int[][] matrix) {
             int m = matrix.length, n = matrix[0].length;
             boolean[][] isZero = new boolean[m][n];
             for (int i = 0; i < m; i++) {
                 for (int j = 0; j < n; j++) {
                     isZero[i][j] = matrix[i][j] == 0;
                 }
             }

             for (int i = 0; i < m; i++) {
                 for (int j = 0; j < n; j++) {
                     if (isZero[i][j]) {
                         for (int k = 0; k < n; k++) matrix[i][k] = 0;
                         for (int k = 0; k < m; k++) matrix[k][j] = 0;
                     }
                 }
             }
         }

    }



    public static class Solution2 {

        /**
         可以分别用一个数组来记录所有的行/列是否出现0，然后根据这个位置数组进行置0
         时间复杂度：O(M * N)
         空间复杂度：O(M + N)
         */
         public void setZeroes(int[][] matrix) {
             int m = matrix.length, n = matrix[0].length;
             boolean[] rows = new boolean[m], cols = new boolean[n];
             for (int i = 0; i < m; i++) {
                 for (int j = 0; j < n; j++) {
                     if (matrix[i][j] == 0) {
                         rows[i] = true;
                         cols[j] = true;
                     }
                 }
             }

             for (int i = 0; i < m; i++) {
                 if (rows[i]) {
                     for (int j = 0; j < n; j++) {
                         matrix[i][j] = 0;
                     }
                 }
             }
             for (int j = 0; j < n; j++) {
                 if (cols[j]) {
                     for (int i = 0; i < m; i++) {
                         matrix[i][j] = 0;
                     }
                 }
             }
         }

    }



    public static class Solution3 {

        /**
         用第一行第一列作为标志位(0表示这一行/列需要置0，非0表示不需要)，但这样会覆盖第一行/列原始情况，
         所以你需要将第一行/列是否需要置0提前用临时变量记录下来
         时间复杂度：O(M * N)
         空间复杂度：O(1)
         */
        public void setZeroes(int[][] matrix) {
            int m = matrix.length, n = matrix[0].length;

            // 临时变量记录第一行/列是否需要置0
            boolean row0 = false, col0 = false;
            for (int i = 0; i < m; i++) {
                if (matrix[i][0] == 0) col0 = true;
            }
            for (int j = 0; j < n; j++) {
                if (matrix[0][j] == 0) row0 = true;
            }

            // 用第一行/列表示该行/列是否需要置0
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    if (matrix[i][j] == 0) {
                        matrix[0][j] = matrix[i][0] = 0;
                    }
                }
            }

            // 根据第一行/列的结果将其他行/列进行置0
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                        matrix[i][j] = 0;
                    }
                }
            }

            // 根据临时变量中记录的结果将第一行/列进行置0
            if (col0) {
                for (int i = 0; i < m; i++) {
                    matrix[i][0] = 0;
                }
            }
            if (row0) {
                for (int j = 0; j < n; j++) {
                    matrix[0][j] = 0;
                }
            }
        }

    }


}
