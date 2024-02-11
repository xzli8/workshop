package data_structure_algorithm.leetcode;

public class _915partitionDisjoint {

    public static class Solution1 {

        /**
         模拟：一次遍历，在遍历过程中记录已遍历区间的最大值max，同时记录左子数组的边界left及其最大值leftMax
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int partitionDisjoint(int[] nums) {
            int n = nums.length, max = nums[0], left = 0, leftMax = max;
            for (int i = 1; i < n; i++) {
                max = Math.max(max, nums[i]);
                if (nums[i] < leftMax) {
                    left = i;
                    leftMax = max;
                }
            }
            return left + 1;
        }

    }

}
