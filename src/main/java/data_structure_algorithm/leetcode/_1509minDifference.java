package data_structure_algorithm.leetcode;

public class _1509minDifference {

    public static class Solution1 {

        /**
         贪心 + 排序：将最大的三个数变成最小的三个数，或者将最小的三个数变成最大的三个数
         时间复杂度：O(NlogN)
         空间复杂度：O(1)
         */
        public int minDifference(int[] nums) {
            int n = nums.length;
            if (n <= 3) return 0;

            Arrays.sort(nums);
            int res = Integer.MAX_VALUE;
            for (int i = 0; i < 4; i++) {
                res = Math.min(res, nums[n - 4 + i] - nums[i]);
            }
            return res;
        }

    }

}
