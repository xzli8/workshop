package data_structure_algorithm.leetcode;

public class _674findLengthOfLCIS {

    public static class Solution1 {

        /**
         滑动窗口
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int findLengthOfLCIS(int[] nums) {
            int n = nums.length, left = 0, right = 1, maxLen = 1;
            while (right < n) {
                while (right < n && nums[right] > nums[right-1]) {
                    right++;
                }
                maxLen = Math.max(maxLen, right - left);
                left = right++;
            }
            return maxLen;
        }

    }

}
