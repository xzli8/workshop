package data_structure_algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class _727minWindow {

    /**
     * 题目描述：给定字符串 S and T，找出 S 中最短的（连续）子串 W ，使得 T 是 W 的 子序列 。
     *         如果 S 中没有窗口可以包含 T 中的所有字符，返回空字符串 “”。如果有不止一个最短长度的窗口，返回开始位置最靠左的那个。
     *
     * 示例 1：
     * 输入：
     * S = "abcdebdde", T = "bde"
     * 输出："bcde"
     * 解释：
     * "bcde" 是答案，因为它在相同长度的字符串 "bdde" 出现之前。
     * "deb" 不是一个更短的答案，因为在窗口中必须按顺序出现 T 中的元素。
     *
     * 注：
     * 所有输入的字符串都只包含小写字母。
     * S 长度的范围为 [1, 20000]。
     * T 长度的范围为 [1, 100]。
     */

    public static class Solution1 {

        /**
         * 滑动窗口：双指针，把T覆盖以后，两个字符串的指针同时向前移，一直找到T耗尽为止。然后下一轮再从上次匹配到位置的下一个开始。
         *  时间复杂度：O(M * N)
         *  空间复杂度：O(1)
         */
        public String minWindow(String s, String t) {
            int m = s.length(), n = t.length(), i = 0, j = 0, minLen = Integer.MAX_VALUE, start = -1;
            while (i < m) {
                if (s.charAt(i) == t.charAt(j)) {
                    j++;

                    // 当T覆盖完全以后，两指针同时向前移，直到T被完全匹配
                    if (j == n) {
                        j--;
                        int end = i;
                        while (j >= 0) {
                            if (s.charAt(i) == t.charAt(j)) {
                                j--;
                            }
                            i--;
                        }
                        i++;
                        j++;

                        // 计算最短子串
                        if (end - i + 1 < minLen) {
                            minLen = end - i + 1;
                            start = i;
                        }
                    }
                }
                i++;
            }
            return start == -1 ? "" : s.substring(start, start + minLen);
        }

        @Test
        public void test() {
            Assert.assertEquals("bcde", minWindow("abcdebdde", "bde"));
        }

    }



    public static class Solution2 {

        /**
         *  动态规划
         *      定义状态：dp[i][j]表示s的前i-1个字符组成的子串包含t的前j-1个字符组成的子串的起始位置
         *      状态转移：dp[i][j] = s[i-1] == t[j-1] ? dp[i-1][j-1] : dp[i-1][j]
         *      初始状态；dp[i][0] = i, dp[0][j] = -1
         *
         *      时间复杂度：O(M * N)
         *      空间复杂度：O(M * N)
         */
        public String minWindow(String s, String t) {
            // 定义状态
            int m = s.length(), n = t.length();
            int[][] dp = new int[m + 1][n + 1];

            // 初始状态
            for (int i = 0; i <= m; i++) {
                Arrays.fill(dp[i], -1);
                dp[i][0] = i;
            }

            // 状态转移
            int minLen = Integer.MAX_VALUE, start = -1;
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    dp[i][j] = s.charAt(i - 1) == t.charAt(j - 1) ? dp[i - 1][j - 1] : dp[i - 1][j];
                }

                // 找最小子串
                if (dp[i][n] != -1 && i - dp[i][n] < minLen) {
                    minLen = i - dp[i][n];
                    start = dp[i][n];
                }
            }
            return start == -1 ? "" : s.substring(start, start + minLen);
        }

        @Test
        public void test() {
            Assert.assertEquals("bcde", minWindow("abcdebdde", "bde"));
        }

    }



}
