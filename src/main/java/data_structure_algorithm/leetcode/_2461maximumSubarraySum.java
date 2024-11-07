package data_structure_algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class _2461maximumSubarraySum {

    public static class Solution1 {

        /**
         SlidingWindow + HashMap
         TC: O(N)
         SC: O(1)
         */
        public long maximumSubarraySum(int[] nums, int k) {
            // init window
            int n = nums.length;
            long windowSum = 0, maxSum = 0;
            Map<Integer, Integer> num2Count = new HashMap<>();
            for (int i = 0; i < k; i++) {
                num2Count.put(nums[i], num2Count.getOrDefault(nums[i], 0) + 1);
                windowSum += nums[i];
            }
            if (num2Count.size() == k) maxSum = windowSum;

            // move window
            for (int i = k; i < n; i++) {
                // remove left item
                int leftNumCount = num2Count.get(nums[i - k]);
                if (leftNumCount == 1) {
                    num2Count.remove(nums[i - k]);
                } else {
                    num2Count.put(nums[i - k], leftNumCount - 1);
                }

                // add right item
                num2Count.put(nums[i], num2Count.getOrDefault(nums[i], 0) + 1);

                // update sum
                windowSum += nums[i] - nums[i - k];
                if (num2Count.size() == k) {
                    maxSum = Math.max(maxSum, windowSum);
                }
            }
            return maxSum;
        }

    }

}
