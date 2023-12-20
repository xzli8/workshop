package data_structure_algorithm.leetcode;

public class _59generateMatrix {

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
