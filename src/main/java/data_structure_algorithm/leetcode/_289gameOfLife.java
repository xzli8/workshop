package data_structure_algorithm.leetcode;

public class _289gameOfLife {

    public static class Solution1 {

        /**
         模拟：因为在原数组上一边遍历一边修改，前面的修改会对后面的判断造成影响，所以需要将原数组先复制一份出来
         时间复杂度：O(M * N)
         空间复杂度：O(M * N)
         */
         public void gameOfLife(int[][] board) {
             // 复制数组
             int m = board.length, n = board[0].length;
             int[][] copy = new int[m][n];
             for (int i = 0; i < m; i++) {
                 for (int j = 0; j < n; j++) copy[i][j] = board[i][j];
             }

             // 模拟
             int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
             for (int i = 0; i < m; i++) {
                 for (int j = 0; j < n; j++) {
                     // 统计每个细胞周围存活细胞的个数
                     int liveCount = 0;
                     for (int[] dir : dirs) {
                         int x = i + dir[0], y = j + dir[1];
                         if (0 <= x && x < m && 0 <= y && y < n && copy[x][y] == 1) liveCount++;
                     }

                     // 根据规则改变状态
                     if (copy[i][j] == 1 && (liveCount < 2 || liveCount > 3)) board[i][j] = 0;
                     if (copy[i][j] == 0 && liveCount == 3) board[i][j] = 1;
                 }
             }
         }

    }



    public static class Solution2 {

        /**
         使用复合状态模拟
         ref:https://leetcode.cn/problems/game-of-life/solutions/179750/sheng-ming-you-xi-by-leetcode-solution/?envType=study-plan-v2&envId=top-interview-150
         时间复杂度：O(M * N)
         空间复杂度：O(1)
         */
        public void gameOfLife(int[][] board) {
            // 模拟并改变状态
            int m = board.length, n = board[0].length;
            int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // 统计每个细胞周围存活细胞的个数
                    int liveCount = 0;
                    for (int[] dir : dirs) {
                        int x = i + dir[0], y = j + dir[1];
                        if (0 <= x && x < m && 0 <= y && y < n && (board[x][y] == 1 || board[x][y] == -1)) liveCount++; // 判断条件需要加上-1
                    }

                    // 根据规则改变状态
                    if (board[i][j] == 1 && (liveCount < 2 || liveCount > 3)) board[i][j] = -1; // -1代表细胞过去是活的，现在死了
                    if (board[i][j] == 0 && liveCount == 3) board[i][j] = 2;    // 2代表细胞过去是死的，现在活了
                }
            }

            // 用复合状态更新最终状态
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    board[i][j] = board[i][j] > 0 ? 1 : 0;
                }
            }
        }

    }

}
