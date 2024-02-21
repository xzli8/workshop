package data_structure_algorithm.leetcode;

public class _9isPalindrome {

    public static class Solution1 {

        /**
         转换成字符串后用双指针比较
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public boolean isPalindrome(int x) {
            String s = String.valueOf(x);
            int left = 0, right = s.length() - 1;
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



    public static class Solution3 {

        /**
         反转一半数字
         时间复杂度：O(logN)
         空间复杂度：O(1)
         */
         public boolean isPalindrome(int x) {
             if (x < 0 || (x % 10 == 0 && x != 0)) return false;
             int reverse = 0;
             while (x > reverse) {
                 reverse = reverse * 10 + x % 10;
                 x /= 10;
             }
             return x == reverse || x == reverse / 10;
         }

    }

}
