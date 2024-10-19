package data_structure_algorithm.leetcode;

public class _3192minOperations {

    public static class Solution1 {

        /**
         Greedy:翻转两次等于没有翻转，可以记录操作次数，根据操作次数判断该元素是否需要翻转
         */
        public int minOperations(int[] nums) {
            int operations = 0;
            for (int num : nums) {
                if (num == operations % 2) {
                    operations++;
                }
            }
            return operations;
        }

    }

}
