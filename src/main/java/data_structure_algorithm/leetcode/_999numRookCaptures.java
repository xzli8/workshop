package data_structure_algorithm.leetcode;

public class _999numRookCaptures {

    public static class Solution1 {

        /**
         数学：矩阵模拟
         T: O(1)
         S: O(1)
         */
        public int numRookCaptures(char[][] board) {
            int m = board.length, n = board[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 'R') {
                        int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
                        int count = 0;
                        for (int[] dir : dirs) {
                            int x = i + dir[0], y = j + dir[1];
                            while (0 <= x && x < m && 0 <= y && y < n) {
                                if (board[x][y] != '.') {
                                    if (board[x][y] == 'p') count++;
                                    break;
                                }
                                x += dir[0];
                                y += dir[1];
                            }
                        }
                        return count;
                    }
                }
            }
            return 0;
        }

    }

}
