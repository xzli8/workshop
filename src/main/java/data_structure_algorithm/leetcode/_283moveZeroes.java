package data_structure_algorithm.leetcode;

public class _283moveZeroes {

    public static class Solution0 {

        /**
         一次遍历: O(N), O(1)
         */
        public void moveZeroes(int[] nums) {
            int i = 0;
            for (int num : nums) {
                if (num != 0) {
                    nums[i++] = num;
                }
            }
            for (int j = i; j < nums.length; j++) {
                nums[j] = 0;
            }
        }

    }

    public static class Solution1 {

        public void moveZeroes(int[] nums) {
            int firstZero = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    int temp = nums[firstZero];
                    nums[firstZero] = nums[i];
                    nums[i] = temp;
                    firstZero++;
                }
            }
        }

    }

}
