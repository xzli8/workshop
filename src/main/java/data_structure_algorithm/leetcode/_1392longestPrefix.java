package data_structure_algorithm.leetcode;

public class _1392longestPrefix {

    public static class Solution0 {

        /**
         KMP: next数组，失效函数: O(N), O(N)
         */
        public String longestPrefix(String s) {
            int n = s.length(), k = 0;  // k表示已经匹配的前缀的长度
            int[] next = new int[n];    // next[i]表示s[0, i]的最长前缀=最长后缀的长度
            for (int i = 1; i < n; i++) {
                while (k > 0 && s.charAt(i) != s.charAt(k)) {
                    k = next[k - 1];    // 不匹配时回退
                }
                if (s.charAt(i) == s.charAt(k)) k++;    // 匹配成功时长度+1
                next[i] = k;
            }
            return s.substring(0, next[n - 1]);
        }

    }

    public static class Solution1 {

        /**
         RollingHash: O(N), O(1)
         */
        public String longestPrefix(String s) {
            int n = s.length(), index = 0;
            long prefix = 0, suffix = 0, base = 31, mod = 1000000007, pow = 1;
            // i表示正在匹配的串长度而不是下标，同时排除整串比较
            for (int i = 1; i < n; i++) {
                prefix = (prefix * base + (s.charAt(i - 1) - 'a')) % mod;
                suffix = (suffix + (s.charAt(n - i) - 'a') * pow) % mod;
                if (prefix == suffix) index = i;
                pow = pow * base % mod;
            }
            return s.substring(0, index);
        }

    }


}
