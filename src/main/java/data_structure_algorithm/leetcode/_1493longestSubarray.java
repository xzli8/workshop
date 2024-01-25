package data_structure_algorithm.leetcode;

public class _1493longestSubarray {

    public static class Solution1 {

        /**
         滑动窗口
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int longestSubarray(int[] nums) {
            int n = nums.length, left = 0, right = 0, remain = 1, maxLen = 0;
            while (right < n) {
                int zeroIndex = right;
                while (right < n && (nums[right] == 1 || remain > 0)) {
                    if (nums[right] != 1) {
                        remain--;
                        zeroIndex = right;
                    }
                    right++;
                }
                maxLen = Math.max(maxLen, right - left - 1);
                left = zeroIndex + 1;
                remain++;
            }
            return maxLen;
        }

    }

}
