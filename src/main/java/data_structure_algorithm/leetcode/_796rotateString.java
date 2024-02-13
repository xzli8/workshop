package data_structure_algorithm.leetcode;

public class _796rotateString {

    public static class Solution1 {

        /**
         转换为搜索子串 + 库函数
         时间复杂度：O(N)，取决于contains函数的时间复杂度，java中使用indexOf，而非KMP
         空间复杂度：O(N)
         */
         public boolean rotateString(String s, String goal) {
             return s.length() == goal.length() && (s + s).contains(goal);
         }

    }



    public static class Solution2 {

        /**
         模拟 + 暴力匹配
         时间复杂度：O(N^2)
         空间复杂度：O(1)
         */
         public boolean rotateString(String s, String goal) {
             int m = s.length(), n = goal.length();
             if (m != n) return false;
             for (int start = 0; start < m; start++) {
                 String main = s.substring(start, m) + s.substring(0, start);
                 if (bf(main, goal)) return true;
             }
             return false;
         }

         private boolean bf(String main, String pattern) {
             int n = main.length(), m = pattern.length();
             for (int start = 0; start < n; start++) {
                 int i = start, j = 0;
                 while (i < n && j < m) {
                     if (main.charAt(i) != pattern.charAt(j)) break;
                     i++;
                     j++;
                 }
                 if (j == m) return true;
             }
             return false;
         }

    }



    public static class Solution3 {

        /**
         模拟 + RK匹配(RollingHash)
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public boolean rotateString(String s, String goal) {
            int m = s.length(), n = goal.length();
            if (m != n) return false;
            for (int start = 0; start < m; start++) {
                String main = s.substring(start, m) + s.substring(0, m);
                if (rk(main, goal)) return true;
            }
            return false;
        }

        private boolean rk(String main, String pattern) {
            // 计算pow
            int n = main.length(), m = pattern.length(), radix = 26;
            long pow = 1;
            for (int i = 1; i < m; i++) pow *= radix;

            // 计算模式串的哈希值
            long patternHash = 0;
            for (int i = 0; i < m; i++) patternHash = patternHash * 26 + pattern.charAt(i) - 'a';

            // 滑动哈希
            int left = 0, right = 0;
            long windowHash = 0;
            while (right < n) {
                windowHash = windowHash * radix + main.charAt(right++) - 'a';
                if (right - left == m) {
                    if (windowHash == patternHash && main.substring(left, right).equals(pattern)) return true;
                    windowHash -= (main.charAt(left++) - 'a') * pow;
                }
            }
            return false;
        }

    }

}
