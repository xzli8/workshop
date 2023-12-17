package data_structure_algorithm.leetcode;

public class _485findMaxConsecutiveOnes {

    public static class Solution1 {

        /**
         滑动窗口
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int findMaxConsecutiveOnes(int[] nums) {
            int n = nums.length, left = 0, right = 0, maxLen = 0;
            while (right < n) {
                while (right < n && nums[right] == 1) {
                    right++;
                    maxLen = Math.max(maxLen, right - left);
                }
                left = right;
                while (left < n && nums[left] == 0) {
                    left++;
                }
                right = left;
            }
            return maxLen;
        }

    }

}
