package data_structure_algorithm.leetcode;

public class _59generateMatrix {

    public static class Solution0 {

        /**
         模拟(边界)
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int[][] generateMatrix(int n) {
            return generateMatrix(n, n);
        }

        private int[][] generateMatrix(int m, int n) {
            int[][] res = new int[m][n];
            int upper = 0, down = m - 1, left = 0, right = n - 1, k = 1;
            while (true) {
                for (int j = left; j <= right; j++) res[upper][j] = k++;
                if (++upper > down) break;
                for (int i = upper; i <= down; i++) res[i][right] = k++;
                if (--right < left) break;
                for (int j = right; j >= left; j--) res[down][j] = k++;
                if (--down < upper) break;
                for (int i = down; i >= upper; i--) res[i][left] = k++;
                if (++left > right) break;
            }
            return res;
        }

    }

    public static class Solution1 {

        // 边界模拟
        public int[][] generateMatrix(int n) {
            int[][] res = new int[n][n];
            int count = 0;
            int left = 0;
            int right = n-1;
            int top = 0;
            int bottom = n-1;
            while(true) {
                for (int i = left; i <= right; i++) res[top][i] = ++count;
                if (++top > bottom) break;

                for (int i = top; i <= bottom; i++) res[i][right] = ++count;
                if (--right < left) break;

                for (int i = right; i >= left; i--) res[bottom][i] = ++count;
                if (--bottom < top) break;

                for (int i = bottom; i >= top; i--) res[i][left] = ++count;
                if (++left > right) break;
            }
            return res;
        }

    }

}
