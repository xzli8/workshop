package data_structure_algorithm.leetcode;

public class _680validPalindrome {

    public static class Solution1 {

        /**
         最原始的想法：枚举每一个字符删除，然后用双指针判断剩下的字符串是否是回文，时间复杂度O(N)
         双指针 + 贪心
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public boolean validPalindrome(String s) {
            int i = 0, j = s.length() - 1;
            while (i <= j) {
                if (s.charAt(i) == s.charAt(j)) {
                    i++;
                    j--;
                }
                // 当遇到不相等的字符时，左指针右移一位继续比较 or 右指针左移一位继续比较
                else {
                    return isPalindrome(s, i+1, j) || isPalindrome(s, i, j-1);
                }
            }
            return true;
        }

        private boolean isPalindrome(String s, int i, int j) {
            while (i <= j) {
                if (s.charAt(i) != s.charAt(j)) {
                    return false;
                }
                i++;
                j--;
            }
            return true;
        }

    }

}
