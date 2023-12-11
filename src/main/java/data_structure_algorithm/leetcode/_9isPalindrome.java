package data_structure_algorithm.leetcode;

public class _9isPalindrome {

    public static class Solution1 {

        /**
         反转数字后比较
         时间复杂度：O(logN)
         空间复杂度：O(1)
         */
        public boolean isPalindrome(int x) {
            // 负数肯定不是回文数
            if (x < 0) return false;

            int old = x, res = 0;
            while (x != 0) {
                int digit = x % 10;
                // 这里没有判断是否溢出，因为能溢出的数字肯定不是回文数，并且溢出后结果肯定是 res != old
                res = res * 10 + digit;
                x /= 10;
            }
            return res == old;
        }

    }

}
