package data_structure_algorithm.leetcode;

public class _915partitionDisjoint {

    public static class Solution1 {

        /**
         动态规划：O(N), O(N)
         */
        public int partitionDisjoint(int[] nums) {
            int n = nums.length;
            int[] leftMax = new int[n], rightMin = new int[n];
            for (int i = 0, max = Integer.MIN_VALUE; i < n; i++) {
                max = Math.max(max, nums[i]);
                leftMax[i] = max;
            }
            for (int i = n - 1, min = Integer.MAX_VALUE; i >= 0; i--) {
                min = Math.min(min, nums[i]);
                rightMin[i] = min;
            }
            for (int i = 0; i < n - 1; i++) {
                if (leftMax[i] <= rightMin[i + 1]) {
                    return i + 1;
                }
            }
            return -1;
        }

    }

    public static class Solution2 {

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
