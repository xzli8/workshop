package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

public class _2326spiralMatrix {

    public static class Solution1 {

        /**
         边界模拟(类似59.螺旋矩阵II): O(N), O(1)
         */
        public int[][] spiralMatrix(int m, int n, ListNode head) {
            // init
            int[][] res = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    res[i][j] = -1;
                }
            }

            // mock boundary
            int upper = 0, down = m - 1, left = 0, right = n - 1;
            while (head != null) {
                for (int i = left; i <= right && head != null; i++, head = head.next) res[upper][i] = head.val;
                if (++upper > down) break;
                for (int i = upper; i <= down && head != null; i++, head = head.next) res[i][right] = head.val;
                if (--right < left) break;
                for (int i = right; i >= left && head != null; i--, head = head.next) res[down][i] = head.val;
                if (--down < upper) break;
                for (int i = down; i >= upper && head != null; i--, head = head.next) res[i][left] = head.val;
                if (++left > right) break;
            }
            return res;
        }

    }

}
