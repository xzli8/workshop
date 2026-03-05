package data_structure_algorithm.leetcode;

public class _2414longestContinuousSubstring {

    public static class Solution1 {

        /**
         SlideWindow: O(N), O(1)
         */
        public int longestContinuousSubstring(String s) {
            int n = s.length(), left = 0, right = 1, maxLen = 1;
            while (right < n) {
                while (right < n && s.charAt(right) == s.charAt(right - 1) + 1) {
                    right++;
                }
                maxLen = Math.max(maxLen, right - left);
                left = right++;
            }
            return maxLen;
        }

    }

}
