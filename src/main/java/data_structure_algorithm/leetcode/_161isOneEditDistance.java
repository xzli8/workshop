package data_structure_algorithm.leetcode;

public class _161isOneEditDistance {

    /**
     * Ref:
     *      https://leetcode.doocs.org/lc/161/
     *      https://www.cnblogs.com/grandyang/p/5184698.html
     */

    public static class Solution1 {

        /**
         * 动态规划：O(M*N), O(M*N)
         * Note：先计算s和t的编辑距离，然后判断是否为1
         */
        public boolean isOneEditDistance(String s, String t) {
            int n1 = s.length(), n2 = t.length();
            int[][] dp = new int[n1 + 1][n2 + 1];
            for (int i = 0; i <= n1; i++) dp[i][0] = i;
            for (int j = 0; j <= n2; j++) dp[0][j] = j;
            for (int i = 1; i <= n1; i++) {
                for (int j = 1; j <= n2; j++) {
                    dp[i][j] = s.charAt(i - 1) == t.charAt(j - 1) ? dp[i - 1][j - 1] :
                            Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
            return dp[n1][n2] == 1;
        }

    }

    public static class Solution2 {

        /**
         * 遍历：分为三种情况考虑
         * 时间复杂度：O(N)
         * 空间复杂度：O(1)
         */
        public boolean isOneEditDistance(String s, String t) {
            if (s.length() < t.length()) return isOneEditDistance(t, s);
            int m = s.length(), n = t.length(), diff = m - n;
            if (diff >= 2) return false;
            else if (diff == 1) {
                for (int i = 0; i < n; i++) {
                    if (s.charAt(i) != t.charAt(i)) return s.substring(i + 1).equals(t.substring(i));
                }
                return true;
            } else {
                int cnt = 0;
                for (int i = 0; i < m; i++) {
                    if (s.charAt(i) != t.charAt(i)) cnt++;
                }
                return cnt == 1;
            }
        }

        // 第二种写法
        public boolean isOneEditDistanceII(String s, String t) {
            int m = s.length(), n = t.length();
            if (m > n) return isOneEditDistanceII(t, s);   // 保证 s 不长于 t, 少写一半分支
            if (n - m > 1) return false;                  // 长度差>1, 不可能

            for (int i = 0; i < m; i++) {
                if (s.charAt(i) != t.charAt(i)) {         // 第一个不同处
                    if (m == n) {
                        return s.substring(i + 1).equals(t.substring(i + 1));  // 替换: 后面要全同
                    } else {
                        return s.substring(i).equals(t.substring(i + 1));      // 删/插: 跳过t这个字符
                    }
                }
            }
            // 前 m 个字符都相同 → 只有 t 恰好多 1 个字符才算 (末尾插入)
            return n - m == 1;
        }

    }

}
