package data_structure_algorithm.leetcode;

public class _2330makePalindrome {

    public static class Solution1 {

        /**
         * 双指针：O(N), O(1)
         * ref: https://leetcode.doocs.org/lc/2330/
         */
        public boolean makePalindrome(String s) {
            int cnt = 0;
            int i = 0, j = s.length() - 1;
            for (; i < j; ++i, --j) {
                if (s.charAt(i) != s.charAt(j)) {
                    ++cnt;
                }
            }
            return cnt <= 2;
        }

    }

}
