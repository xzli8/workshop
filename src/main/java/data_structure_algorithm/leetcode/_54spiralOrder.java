package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _54spiralOrder {

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
