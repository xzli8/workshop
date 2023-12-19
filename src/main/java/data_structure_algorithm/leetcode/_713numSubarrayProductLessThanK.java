package data_structure_algorithm.leetcode;

public class _713numSubarrayProductLessThanK {

    public static class Solution1 {

        /**
         滑动窗口
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int numSubarrayProductLessThanK(int[] nums, int k) {
            int n = nums.length, left = 0, right = 0, product = 1, count = 0;
            while (right < n) {
                product *= nums[right++];
                while (product >= k && left < right) {
                    product /= nums[left++];
                }
                count += right - left;
            }
            return count;
        }

    }

}
