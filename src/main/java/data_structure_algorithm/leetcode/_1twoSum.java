package data_structure_algorithm.leetcode;

import java.util.HashMap;

public class _1twoSum {

    public static class Solution1 {

        /**
         哈希表
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int[] twoSum(int[] nums, int target) {
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (map.containsKey(target - nums[i])) {
                    return new int[] {i, map.get(target - nums[i])};
                }
                map.put(nums[i], i);
            }
            return new int[0];
        }

    }

}
