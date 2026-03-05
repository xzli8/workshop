package data_structure_algorithm.leetcode;

public class _2511captureForts {

    public static class Solution1 {

        /**
         SlideWindow: O(N), O(1)
         */
        public int captureForts(int[] forts) {
            int n = forts.length, left = 0, right = 0, maxLen = 0;
            while (left < n && forts[left] == 0) left++;
            right = left + 1;
            while (right < n) {
                while (right < n && forts[right] == 0) right++;
                if (right < n && forts[right] * forts[left] < 0) {
                    maxLen = Math.max(maxLen, right - left - 1);
                }
                left = right++;
            }
            return maxLen;
        }

    }

}
