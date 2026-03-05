package data_structure_algorithm.leetcode;

public class _1869checkZeroOnes {

    public static class Solution1 {

        /**
         SlideWindow: O(N), O(1)
         */
        public boolean checkZeroOnes(String s) {
            return findLCS(s, '1') - findLCS(s, '0') > 0;
        }

        private int findLCS(String s, char c) {
            int n = s.length(), left = 0, right = 0, maxLen = 0;
            while (right < n) {
                while (right < n && s.charAt(right) == c) right++;
                maxLen = Math.max(maxLen, right - left);
                while (right < n && s.charAt(right) != c) right++;
                left = right;
            }
            return maxLen;
        }

    }

}
