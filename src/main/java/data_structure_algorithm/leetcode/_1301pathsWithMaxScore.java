package data_structure_algorithm.leetcode;

import java.util.Arrays;
import java.util.List;

public class _1301pathsWithMaxScore {

    public static class Solution1 {

        /**
         动态规划
         定义状态：dp[i][j]表示走到[i, j]时的最大和，cnt[i][j]表示走到[i, j]时的最大和的方案数
         状态转移：dp[i][j] = max(dp[i + 1][j], dp[i][j + 1], dp[i + 1][j + 1])
         初始状态：最后一行

         时间复杂度：O(M * N)
         空间复杂度：O(M * N)
         */
        public int[] pathsWithMaxScore(List<String> board) {
            // 定义状态
            int m = board.size(), n = board.get(0).length(), MOD = (int) 1e9 + 7;
            int[][] dp = new int[m][n], cnt = new int[m][n];

            // 初始状态
            for (int i = 0; i < m; i++) {
                Arrays.fill(dp[i], -1);
                Arrays.fill(cnt[i], 0);
            }
            dp[m - 1][n - 1] = 0;
            cnt[m - 1][n - 1] = 1;
            String s = board.get(m - 1);
            for (int j = n - 2; j >= 0; j--) {
                char c = s.charAt(j);
                if (c != 'X' && dp[m - 1][j + 1] != -1) {
                    dp[m - 1][j] = dp[m - 1][j + 1] + c - '0';
                    cnt[m - 1][j] = 1;
                }
            }

            // 状态转移(当前状态可以由下、右、右下转移而来)
            int[][] dirs = new int[][] {{1, 1}, {1, 0}, {0, 1}};
            for (int i = m - 2; i >= 0; i--) {
                s = board.get(i);
                for (int j = n - 1; j >= 0; j--) {
                    char c = s.charAt(j);
                    if (c != 'X') {
                        int num = c == 'E' ? 0 : c - '0';
                        if (j == n - 1) {
                            if (dp[i + 1][j] != -1) {
                                dp[i][j] = dp[i + 1][j] + num;
                                cnt[i][j] = 1;
                            }
                        } else {
                             for (int[] dir : dirs) {
                                 int x = i + dir[0], y = j + dir[1];
                                 if (dp[x][y] == -1) continue;
                                 if (dp[x][y] + num > dp[i][j]) {
                                     dp[i][j] = dp[x][y] + num;
                                     cnt[i][j] = cnt[x][y];
                                 } else if (dp[x][y] + num == dp[i][j]) {
                                     cnt[i][j] += cnt[x][y];
                                 }
                                 cnt[i][j] %= MOD;
                             }
                        }
                    }
                }
            }
            return new int[] {dp[0][0] == -1 ? 0 : dp[0][0], cnt[0][0]};
        }

    }


}
