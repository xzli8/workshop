package data_structure_algorithm.leetcode;

public class _80removeDuplicates {

    public static class Solution1 {

        /**
         双指针（参考26）
         */
        public int removeDuplicates(int[] nums) {
            int n = nums.length;
            if (n == 1 || n == 2) return n;

            int i = 2;
            for (int j = 2; j < n; j++) {
                if (nums[j] != nums[i-2]) {
                    nums[i] = nums[j];
                    i++;
                }
            }
            return i;
        }

    }

}
