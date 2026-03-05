package data_structure_algorithm.leetcode;

public class _1035maxUncrossedLines {

    public static class Solution1 {

        /**
         动态规划：与“1143.最长公共子序列”一样
         */
        public int maxUncrossedLines(int[] nums1, int[] nums2) {
            // 定义状态
            int n1 = nums1.length, n2 = nums2.length;
            int[][] dp = new int[n1 + 1][n2 + 1];

            // 初始状态
            // 数组元素默认初始化为0，可省略

            // 状态转移
            for (int i = 1; i <= n1; i++) {
                for (int j = 1; j <= n2; j++) {
                    if (nums1[i - 1] == nums2[j - 1]) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
            return dp[n1][n2];
        }

    }

}
