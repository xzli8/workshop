package data_structure_algorithm.leetcode;

public class _2401longestNiceSubarray {

    public static class Solution1 {

        /**
         滑动窗口: O(N), O(1)
         */
        public int longestNiceSubarray(int[] nums) {
            int res = 0, or = 0, left = 0;
            for (int right = 0; right < nums.length; right++) {
                while ((or & nums[right]) > 0) {
                    or ^= nums[left++];
                }
                or |= nums[right];
                res = Math.max(res, right - left + 1);
            }
            return res;
        }

    }

}
