package data_structure_algorithm.leetcode;

public class _718findLength {

    public static class Solution1 {

        /**
         动态规划
         定义状态：dp[i][j]表示以nums1[i]和nums2[j]结尾的公共子数组的长度
         状态转移：dp[i][j] = nums1[i] == nums2[j] ? dp[i-1][j-1] + 1 : 0;
         初始状态：dp[i][0] = nums1[i] == nums2[0] ? 1 : 0, dp[0][j] = nums2[j] == nums1[0] ? 1 : 0

         时间复杂度：O(M * N)
         空间复杂度：O(M * N)
         */
         public int findLength(int[] nums1, int[] nums2) {
             // 定义状态
             int m = nums1.length, n = nums2.length;
             int[][] dp = new int[m][n];

             // 初始状态(数组元素默认初始化为0，然后再分别初始化第一行和第一列，还要初始化max)
             int max = 0;
             for (int i = 0; i < m; i++) {
                 if (nums1[i] == nums2[0]) {
                     dp[i][0] = 1;
                     max = 1;
                 }
             }
             for (int j = 0; j < n; j++) {
                 if (nums2[j] == nums1[0]) {
                     dp[0][j] = 1;
                     max = 1;
                 }
             }

             // 状态转移(循环顺序无所谓，先循环i或者先循环j都可以)
             for (int i = 1; i < m; i++) {
                 for (int j = 1; j < n; j++) {
                     if (nums1[i] == nums2[j]) {
                         dp[i][j] = dp[i - 1][j - 1] + 1;
                         max = Math.max(max, dp[i][j]);
                     }
                     // else的内容可以省略，因为默认初始化为0了
                     // else {
                     //     dp[i][j] = 0;
                     // }
                 }
             }
             return max;
         }

    }

    public static class Solution2 {

        /**
         哨兵简化：多定义一行边界，此时的状态dp[i][j]表示以nums[i-1][j-1]结尾的公共子串的长度
         */
        public int findLength(int[] nums1, int[] nums2) {
            // 定义状态
            int m = nums1.length, n = nums2.length;
            int[][] dp = new int[m+1][n+1];

            // 初始状态
            // 数组元素默认初始化为0，可省略

            // 状态转移
            int max = 0;
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (nums1[i - 1] == nums2[j - 1]) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }
                    max = Math.max(max, dp[i][j]);
                }
            }
            return max;
        }

    }



    public static class Solution3 {

        /**
         空间压缩：dp[i][j]只与dp[i-1][j-1]有关，可以用滚动数组将二维状态数组压缩为一维状态数组
         */

    }



    public static class Solution4 {

        /**
         空间压缩：dp[i][j]只与dp[i-1][j-1]有关，可以用滚动数组将二维状态数组压缩为一维状态数组
         */

    }



    public static class Solution5 {

        /**
         滑动窗口(todo)
         */

    }



}
