package data_structure_algorithm.leetcode;

public class _125isPalindrome {

    public static class Solution1 {

        /**
         双指针
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public boolean isPalindrome(String s) {
            // 去除非字母数字字符，并且将大写字母转换成小写字母
            int n = s.length(), i = 0;
            StringBuilder sb = new StringBuilder();
            while (i < n) {
                char c = s.charAt(i++);
                if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                    if (c >= 'A' && c <= 'Z') {
                        c = (char) (c + 32) ;
                    }
                    sb.append(c);
                }
            }

            // 双指针判断是否是回文串
            String res = sb.toString();
            int left = 0, right = res.length() - 1;
            while (left < right) {
                if (res.charAt(left++) != res.charAt(right--)) {
                    return false;
                }
            }
            return true;
        }

    }

}
