package data_structure_algorithm.leetcode;

public class _674findLengthOfLCIS {

    public static class Solution1 {

        /**
         贪心：找尽可能长的连续递增子序列，用滑动窗口实现
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int findLengthOfLCIS(int[] nums) {
            int n = nums.length;
            if (n == 1) return 1;

            int left = 0, right = 1;
            int maxLen = Integer.MIN_VALUE;
            while (right < n) {
                while (right < n && nums[right] > nums[right-1]) {
                    right++;
                }
                maxLen = Math.max(maxLen, right - left);
                left = right;
                right++;
            }
            return maxLen;
        }

    }

}
