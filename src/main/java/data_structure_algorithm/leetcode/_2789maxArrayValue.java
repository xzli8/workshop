package data_structure_algorithm.leetcode;

public class _2789maxArrayValue {

    public static class Solution1 {

        /**
         贪心：为了使数组的最大值最大，我们可以贪心地做尽可能多的合并，直到整个数组都不能进行合并。合并的要求是后面的数字不小于前面的数字，我们就尽可能先合并靠后的数字，使其尽快能大，才能够合并前面的数字。
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public long maxArrayValue(int[] nums) {
            // 倒序遍历
            int n = nums.length;
            long sum = nums[n - 1];
            for (int i = n - 2; i >= 0; i--) {
                sum = nums[i] <= sum ? nums[i] + sum : nums[i];
            }
            return sum;
        }

    }

}
