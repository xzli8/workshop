package data_structure_algorithm.leetcode;

public class _459repeatedSubstringPattern {

    public static class Solution1 {

        /**
         枚举
         时间复杂度：O(N^2)
         空间复杂度：O(1)
         */
         public boolean repeatedSubstringPattern(String s) {
             int n = s.length();
             // i表示子串的长度
             for (int i = 1; i * 2 <= n; i++) {
                 if (n % i == 0) {
                     boolean match = true;
                     for (int j = i; j < n; j++) {
                         if (s.charAt(j) != s.charAt(j - i)) {
                             match = false;
                             break;
                         }
                     }
                     if (match) {
                         return true;
                     }
                 }
             }
             return false;
         }

    }



    public static class Solution2 {

        /**
         移动匹配
         思路:
         1.假设字符串s是由s1+s2组成的，s+s后，str就变成了s1+s2+s1+s2，
         2.去掉首尾，破环了首尾的s1和s2，变成了s3+s2+s1+s4,此时str中间就是s2+s1,
         3.如果s是循环字串，也就是s1=s2,所以str中间的s2+s1就和原字符串相等。
         4.如果s不是循环字串，s1!=s2，那么s1+s2是不等于s2+s1的，也就是str中间不包含s
         */
        public boolean repeatedSubstringPattern(String s) {
            String str = s + s;
            return str.substring(1, str.length()-1).contains(s);
        }

    }




}
