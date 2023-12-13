package data_structure_algorithm.leetcode;

public class _209minSubArrayLen {

    public static class Solution1 {

        /**
         滑动窗口
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int minSubArrayLen(int target, int[] nums) {
            int n = nums.length, left = 0, right = 0, sum = 0, minLen = Integer.MAX_VALUE;
            while (right < n) {
                sum += nums[right++];
                while (sum >= target) {
                    minLen = Math.min(minLen, right - left);
                    sum -= nums[left++];
                }
            }
            return minLen == Integer.MAX_VALUE ? 0 : minLen;
        }

    }

}
