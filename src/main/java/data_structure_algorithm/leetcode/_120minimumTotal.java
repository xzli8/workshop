package data_structure_algorithm.leetcode;

import java.util.Arrays;
import java.util.List;

public class _120minimumTotal {

    public static class Solution1 {

        /**
         动态规划：对于第i层的第j个节点，其可以由第i-1层的j-1或j个节点走过来（考虑边界）
         定义状态：dp[i][j]表示第i层第j个节点的最小路径和
         状态转移：dp[i][j] = min(dp[i-1][j-1], dp[i-1][j]) + nums[i][j]
         时间复杂度：O(n)
         空间复杂度：O(n)
         */
        public int minimumTotal(List<List<Integer>> triangle) {
            // 定义并初始化状态
            int n = triangle.size(), m = triangle.get(n-1).size();
            int[][] dp = new int[n][m];
            dp[0][0] = triangle.get(0).get(0);

            // 状态转移
            for (int i = 1; i < n; i++) {
                dp[i][0] = dp[i-1][0] + triangle.get(i).get(0);
                for (int j = 1; j < i; j++) {
                    dp[i][j] = Math.min(dp[i-1][j-1], dp[i-1][j]) + triangle.get(i).get(j);
                }
                dp[i][i] = dp[i-1][i-1] + triangle.get(i).get(i);

                // for (int j = 0; j <= i; j++) {
                //     int min = Integer.MAX_VALUE;
                //     if (j - 1 >= 0) min = Math.min(min, dp[i-1][j-1]);
                //     if (j < i) min = Math.min(min, dp[i-1][j]);
                //     dp[i][j] = min + triangle.get(i).get(j);
                // }
            }
            return Arrays.stream(dp[n-1]).min().getAsInt();
        }

    }



    public static class Solution2 {

        /**
         优化空间
         */
         public int minimumTotal(List<List<Integer>> triangle) {
             // define state
             int n = triangle.size();
             int[] dp = new int[n], tmp = new int[n];

             // initialize state
             dp[0] = triangle.get(0).get(0);

             // transfer state
             for (int i = 1; i < n; i++) {
                 // copy dp to tmp
                 for (int j = 0; j <= i; j++) {
                     tmp[j] = dp[j];
                 }

                 // transfer state
                 dp[0] = tmp[0] + triangle.get(i).get(0);
                 for (int j = 1; j < i; j++) {
                     dp[j] = Math.min(tmp[j - 1], tmp[j]) + triangle.get(i).get(j);
                 }
                 dp[i] = tmp[i - 1] + triangle.get(i).get(i);

//                 for (int j = 0; j <= i; j++) {
//                     int min = Integer.MAX_VALUE;
//                     if (j - 1 >= 0) min = Math.min(min, tmp[j-1]);
//                     if (j < i) min = Math.min(min, tmp[j]);
//                     dp[j] = min + triangle.get(i).get(j);
//                 }
             }
             return Arrays.stream(dp).min().getAsInt();
         }

    }

}
