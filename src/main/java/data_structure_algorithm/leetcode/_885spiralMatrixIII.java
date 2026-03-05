package data_structure_algorithm.leetcode;

public class _885spiralMatrixIII {

    public static class Solution1 {

        /**
         模拟: O(N), O(1)
         */
        public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
            int cnt = rows * cols;
            int[][] res = new int[cnt][2];
            res[0] = new int[] {rStart, cStart};
            if (cnt == 1) return res;

            for (int k = 1, idx = 1; ; k += 2) {
                int[][] dirs = new int[][] {{0, 1, k}, {1, 0, k}, {0, -1, k + 1}, {-1, 0, k + 1}};
                for (int[] dir : dirs) {
                    int r = dir[0], c = dir[1], dk = dir[2];
                    while (dk-- > 0) {
                        rStart += r;
                        cStart += c;
                        if (0 <= rStart && rStart < rows && 0 <= cStart && cStart < cols) {
                            res[idx++] = new int[] {rStart, cStart};
                            if (idx == cnt) return res;
                        }
                    }
                }
            }
        }

    }

}
