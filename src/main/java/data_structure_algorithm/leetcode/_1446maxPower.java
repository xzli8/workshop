package data_structure_algorithm.leetcode;

public class _1446maxPower {

    public static class Solution1 {

        /**
         SlideWindow
         */
        public int maxPower(String s) {
            int n = s.length(), left = 0, right = 1, maxLen = 1;
            while (right < n) {
                while (right < n && s.charAt(right) == s.charAt(left)) right++;
                maxLen = Math.max(maxLen, right - left);
                left = right++;
            }
            return maxLen;
        }

    }

}
