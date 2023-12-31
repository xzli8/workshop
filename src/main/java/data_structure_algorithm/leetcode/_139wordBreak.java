package data_structure_algorithm.leetcode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _139wordBreak {

    public static class Solution1 {

        /**
         DFS：超出时间限制
         */
         public boolean wordBreak(String s, List<String> wordDict) {
             return backtrace(s, 0, new HashSet<>(wordDict));
         }

         private boolean backtrace(String s, int startIndex, Set<String> wordDict) {
             if (startIndex == s.length()) return true;
             for (int i = startIndex; i < s.length(); i++) {
                 String substring = s.substring(startIndex, i + 1);
                 if (wordDict.contains(substring)) {
                     if (backtrace(s, i + 1, wordDict)) return true;
                 }
             }
             return false;
         }

    }



    public static class Solution2 {

        /**
         DFS + memo
         */
        public boolean wordBreak(String s, List<String> wordDict) {
            return backtrace(s, 0, new HashSet<>(wordDict), new Boolean[s.length()]);
        }

        private boolean backtrace(String s, int startIndex, Set<String> wordDict, Boolean[] memo) {
            if (startIndex == s.length()) return true;
            if (memo[startIndex] != null) return memo[startIndex];

            for (int i = startIndex; i < s.length(); i++) {
                String substring = s.substring(startIndex, i + 1);
                if (wordDict.contains(substring) && backtrace(s, i + 1, wordDict, memo)) {
                    if (backtrace(s, i + 1, wordDict, memo)) {
                        memo[startIndex] = true;
                        return true;
                    }
                }
            }
            memo[startIndex] = false;
            return false;
        }

    }




    public static class Solution3 {

    }



    public static class Solution4 {

    }



    public static class Solution5 {

        /**
         动态规划：完全背包求true/false
         状态定义：dp[i]表示s的前i个字符是否可以被拼接
         状态转移：if (dp[j]为true 并且 s[j, i)在字典中) dp[i] = true
         初始状态：dp[0] = true

         时间复杂度：O(N^3)
         空间复杂度：O(N)
         */
        public boolean wordBreak(String s, List<String> wordDict) {
            // 定义状态
            int n = s.length();
            boolean[] dp = new boolean[n + 1];

            // 初始状态
            dp[0] = true;

            // 状态转移
            Set<String> dict = new HashSet<>(wordDict);
            // 这里要先遍历背包，再遍历物品
            for (int i = 1; i <= n; i++) {
                // 完全背包：内循环从小到大遍历
                for (int j = 0; j <= i; j++) {
                    if (dp[j] && dict.contains(s.substring(j, i))) {
                        dp[i] = true;
                    }
                }
            }
            return dp[n];
        }

    }



}
