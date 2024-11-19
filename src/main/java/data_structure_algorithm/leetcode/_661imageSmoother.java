package data_structure_algorithm.leetcode;

public class _661imageSmoother {

    public static class Solution1 {

        /**
         Array
         TC: O(N)
         SC: O(1)
         */
        public int[][] imageSmoother(int[][] img) {
            int m = img.length, n = img[0].length;
            int[][] res = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int sum = 0, count = 0;
                    for (int k = Math.max(0, i - 1); k <= Math.min(m - 1, i + 1); k++) {
                        for (int l = Math.max(0, j - 1); l <= Math.min(n - 1, j + 1); l++) {
                            sum += img[k][l];
                            count++;
                        }
                    }
                    res[i][j] = sum / count;
                }
            }
            return res;
        }

    }

}
