package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _131partition {

    public static class Solution1 {

        /**
         回溯
         时间复杂度：O(N^2 * 2^N)
         空间复杂度：O(N)
         */
         public List<List<String>> partition(String s) {
             backtrace(s, 0, new ArrayList<>());
             return res;
         }

         private List<List<String>> res = new ArrayList<>();
         private void backtrace(String s, int startIndex, List<String> path) {
             if (startIndex == s.length()) {
                 res.add(new ArrayList<>(path));
                 return;
             }

             for (int i = startIndex; i < s.length(); i++) {
                 if (isPalindrome(s, startIndex, i)) {
                     path.add(s.substring(startIndex, i + 1));
                     backtrace(s, i + 1, path);
                     path.remove(path.size() - 1);
                 }
                 // 这里不能有else break，因为往后可能有可行解，需要继续遍历
             }
         }

         private boolean isPalindrome(String s, int left, int right) {
             while (left < right) {
                 if (s.charAt(left) != s.charAt(right)) return false;
                 left++;
                 right--;
             }
             return true;
         }

    }



    public static class Solution2 {

        /**
         回溯 + 动态规划：用空间换时间，提前用动态规划预处理好字符串，使得判断回文串的时间复杂度由O(N)下降为O(1)
         时间复杂度：O(N * 2^N)
         空间复杂度：O(N^2)
         */
        public List<List<String>> partition(String s) {
            dp = calcultePalindorme(s);
            backtrace(s, 0, new ArrayList<>());
            return res;
        }

        private boolean[][] dp;
        private List<List<String>> res = new ArrayList<>();

        // 回溯
        private void backtrace(String s, int startIndex, List<String> path) {
            if (startIndex == s.length()) {
                res.add(new ArrayList<>(path));
                return;
            }

            for (int i = startIndex; i < s.length(); i++) {
                if (dp[startIndex][i]) {
                    path.add(s.substring(startIndex, i + 1));
                    backtrace(s, i + 1, path);
                    path.remove(path.size() - 1);
                }
                // 这里不能有else break，因为往后可能有可行解，需要继续遍历
            }
        }

        /**
         计算s的回文串数组
         定义状态：dp[i][j]表示s[i, ..., j]是否是回文串
         状态转移：if (i == j) dp[i][j] = true; if (i + 1 == j) dp[i][j] = s[i] == s[j];
         if (i + 1 < j) dp[i][j] = dp[i+1][j-1] && s[i] == s[j]
         初始状态：dp[i][i] = true
         */
        private boolean[][] calcultePalindorme(String s) {
            // 定义状态
            int n = s.length();
            boolean[][] dp = new boolean[n][n];

            // 初始状态
            for (int i = 0; i < n; i++) Arrays.fill(dp[i], true);

            // 状态转移
            for (int i = n - 1; i >= 0; i--) {
                for (int j = i + 1; j < n; j++) {
                    if (j == i + 1) dp[i][j] = s.charAt(i) == s.charAt(j);
                    else dp[i][j] = dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
                }
            }
            return dp;
        }

    }

}
