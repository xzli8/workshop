package data_structure_algorithm.leetcode;

public class _303sumRange {


    /**
     * Your NumArray object will be instantiated and called as such:
     * NumArray obj = new NumArray(nums);
     * int param_1 = obj.sumRange(left,right);
     */

    public static class Solution1 {

        class NumArray {

            /**
             前缀和数组
             */
            private int[] preSum;

            /**
             时间复杂度：O(N)
             空间复杂度：O(N)
             */
            public NumArray(int[] nums) {
                int n = nums.length;
                preSum = new int[n+1];
                for (int i = 1; i <= n; i++) {
                    preSum[i] = preSum[i-1] + nums[i-1];
                }
            }

            /**
             时间复杂度：O(1)
             空间复杂度：O(1)
             */
            public int sumRange(int left, int right) {
                return preSum[right+1] - preSum[left];
            }

        }

    }

}
