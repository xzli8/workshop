package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _673findNumberOfLIS {

    public static class Solution1 {

        /**
         动态规划
         状态定义：dp[i]表示以元素i结尾序列的最长递增子序列的长度，cnt[i]表示以元素i结尾的最长递增子序列的个数
         状态转移：
         dp: for (int j = 0; j < i; j++) if (nums[j] < nums[i]) dp[i] = max(dp[i], dp[j] + 1)
         cnt:
         if (dp[i] < dp[j] + 1) cnt[i] = cnt[j]
         else if (dp[i] == dp[j] + 1) cnt[i] += cnt[j]
         初始状态：dp[i] = 1, cnt[i] = 1

         时间复杂度：O(N^2)
         空间复杂度：O(N)
         */
        public int findNumberOfLIS(int[] nums) {
            // 定义状态
            int n = nums.length;
            int[] dp = new int[n], cnt = new int[n];

            // 初始状态
            Arrays.fill(dp, 1);
            Arrays.fill(cnt, 1);

            // 状态转移
            int maxLen = 1, res = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    if (nums[j] < nums[i]) {
                        // dp[i]被dp[j]更新，cnt[i]直接赋值为cnt[j]
                        if (dp[j] + 1 > dp[i]) {
                            dp[i] = dp[j] + 1;
                            cnt[i] = cnt[j];
                        }
                        // dp[i]找到一个新的符合条件的前驱dp[j]，最长递增子序列的数量要累加
                        else if (dp[j] + 1 == dp[i]) {
                            cnt[i] += cnt[j];
                        }
                    }
                }

                // 找最长递增子序列及其个数
                if (dp[i] > maxLen) {
                    maxLen = dp[i];
                    res = cnt[i];
                } else if (dp[i] == maxLen) {
                    res += cnt[i];
                }
            }
            return res;
        }

    }

}
