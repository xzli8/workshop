package data_structure_algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class _3026maximumSubarraySum {

    public static class Solution1 {

        /**
         PreSum + HashMap
         TC: O(N)
         SC: O(N)
         */
        public long maximumSubarraySum(int[] nums, int k) {
            long preSum = 0L, maxSum = Long.MIN_VALUE;
            Map<Integer, Long> num2PreSum = new HashMap<>();
            for (int num : nums) {
                preSum += num;
                if (num2PreSum.containsKey(num + k)) {
                    maxSum = Math.max(maxSum, preSum - num2PreSum.get(num + k));
                }
                if (num2PreSum.containsKey(num - k)) {
                    maxSum = Math.max(maxSum, preSum - num2PreSum.get(num - k));
                }
                if (num2PreSum.containsKey(num)) {
                    num2PreSum.put(num, Math.min(num2PreSum.get(num), preSum - num));
                } else {
                    num2PreSum.put(num, preSum - num);
                }
            }
            return maxSum == Long.MIN_VALUE ? 0 : maxSum;
        }

    }

}
