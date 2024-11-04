package data_structure_algorithm.leetcode;

public class _2778sumOfSquares {

    public static class Solution1 {

        /**
         Math + Enum
         */
        public int sumOfSquares(int[] nums) {
            int n = nums.length, sum = 0;
            for (int i = 0; i < n; i++) {
                if (n % (i + 1) == 0) {
                    sum += nums[i] * nums[i];
                }
            }
            return sum;
        }

    }

}
