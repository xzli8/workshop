package data_structure_algorithm.leetcode;

public class _1529minFlips {

    public static class Solution1 {

        /**
         Greedy:等同于将target转换为s(类似题：3192)
         */
        public int minFlips(String target) {
            // convert
            int n = target.length();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = target.charAt(i) - '0';
            }

            // flip
            int operations = 0;
            for (int num : nums) {
                if (num != operations % 2) {
                    operations++;
                }
            }
            return operations;
        }

    }

}
