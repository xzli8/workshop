package data_structure_algorithm.leetcode;

public class _1480runningSum {

    public static class Solution1 {

        /**
         前缀和
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int[] runningSum(int[] nums) {
            int n = nums.length;
            int[] res = new int[n];
            res[0] = nums[0];
            for (int i = 1; i < n; i++) res[i] = res[i - 1] + nums[i];
            return res;
        }

    }

}
