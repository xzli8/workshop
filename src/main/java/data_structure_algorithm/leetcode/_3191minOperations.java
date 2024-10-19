package data_structure_algorithm.leetcode;

public class _3191minOperations {

    public static class Solution1 {

        /**
         Greedy
         */
        public int minOperations(int[] nums) {
            int n = nums.length, res = 0;
            for (int i = 0; i < n - 2; i++) {
                // 遇到0就连带后面的两个数一起反转
                if (nums[i] == 0) {
                    nums[i + 1] ^= 1;
                    nums[i + 2] ^= 1;
                    res++;
                }
            }
            return nums[n - 2] != 0 && nums[n - 1] != 0 ? res : -1;
        }

    }

}
