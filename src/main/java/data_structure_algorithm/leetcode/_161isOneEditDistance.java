package data_structure_algorithm.leetcode;

public class _161isOneEditDistance {

    /**
     * ref:https://www.cnblogs.com/grandyang/p/5184698.html
     */

    public static class Solution1 {

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

    }

}
