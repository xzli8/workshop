package data_structure_algorithm.leetcode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _2781longestValidSubstring {

    public static class Solution1 {

        /**
         SlideWindow
         */
        public int longestValidSubstring(String word, List<String> forbidden) {
            this.forbidden.addAll(forbidden);
            int n = word.length(), left = 0, right = 0, maxLen = 0;
            while (right < n) {
                right++;
                while (hasForbidden(word, left, right)) {
                    left++;
                }
                maxLen = Math.max(maxLen, right - left);
            }
            return maxLen;
        }

        private Set<String> forbidden = new HashSet<>();
        private boolean hasForbidden(String word, int left, int right) {
            for (int start = right - 1; start >= Math.max(left, right - 11); start--) {
                if (forbidden.contains(word.substring(start, right))) {
                    return true;
                }
            }
            return false;
        }

    }

}
