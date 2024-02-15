package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _54spiralOrder {

    public static class Solution0 {

        /**
         模拟(边界)
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public List<Integer> spiralOrder(int[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            List<Integer> res = new ArrayList<>(m * n);
            int upper = 0, down = m - 1, left = 0, right = n - 1;
            while (true) {
                for (int j = left; j <= right; j++) res.add(matrix[upper][j]);
                if (++upper > down) break;
                for (int i = upper; i <= down; i++) res.add(matrix[i][right]);
                if (--right < left) break;
                for (int j = right; j >= left; j--) res.add(matrix[down][j]);
                if (--down < upper) break;
                for (int i = down; i >= upper; i--) res.add(matrix[i][left]);
                if (++left > right) break;
            }
            return res;
        }

    }



    public static class Solution1 {

        // 边界法
        public List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> res = new ArrayList<>();
            if (matrix.length == 0 || matrix[0].length == 0) return res;

            int left = 0;
            int right = matrix[0].length - 1;
            int top = 0;
            int bottom = matrix.length - 1;
            while (true) {
                for (int i = left; i <= right; i++) res.add(matrix[top][i]);
                if (++top > bottom) break;

                for (int i = top; i <= bottom; i++) res.add(matrix[i][right]);
                if (--right < left) break;

                for (int i = right; i >= left; i--) res.add(matrix[bottom][i]);
                if (--bottom < top) break;

                for (int i = bottom; i >= top; i--) res.add(matrix[i][left]);
                if (++left > right) break;
            }
            return res;
        }

    }

}
