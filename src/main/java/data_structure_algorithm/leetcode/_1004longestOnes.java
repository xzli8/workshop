package data_structure_algorithm.leetcode;

public class _1004longestOnes {

    public static class Solution1 {

        /**
         滑动窗口
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int longestOnes(int[] nums, int k) {
            int n = nums.length;
            int left = 0, right = 0, maxCount = 0;
            while (right < n) {
                if (nums[right++] == 0) k--;
                while (k < 0) {
                    if (nums[left++] == 0) k++;
                }
                maxCount = Math.max(maxCount, right - left);
            }
            return maxCount;
        }

    }

}
