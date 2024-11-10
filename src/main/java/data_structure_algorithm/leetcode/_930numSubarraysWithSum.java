package data_structure_algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class _930numSubarraysWithSum {

    public static class Solution1 {

        /**
         PreSum + HashMap
         TC: O(N)
         SC: O(N)
         */
        public int numSubarraysWithSum(int[] nums, int goal) {
            int preSum = 0, count = 0;
            Map<Integer, Integer> preSum2Count = new HashMap<>();
            preSum2Count.put(0, 1);
            for (int num : nums) {
                preSum += num;
                if (preSum2Count.containsKey(preSum - goal)) {
                    count += preSum2Count.get(preSum - goal);
                }
                preSum2Count.put(preSum, preSum2Count.getOrDefault(preSum, 0) + 1);
            }
            return count;
        }

    }

}
