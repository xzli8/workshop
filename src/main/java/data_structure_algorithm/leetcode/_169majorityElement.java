package data_structure_algorithm.leetcode;

public class _169majorityElement {

    /**
     * 有多种方案，比如哈希表，排序等，其中最优的是投票法
     */

    public static class Solution1 {

        /**
         摩尔投票法：O(N), O(1)
         Note: 将众数记为+1，非众数记为-1，遍历求和，当和为0时，重新选举一个众数
         */
        public int majorityElement(int[] nums) {
            int major = nums[0], count = 1;
            for (int i = 1; i < nums.length; i++) {
                if (count == 0) major = nums[i];
                count += (nums[i] == major) ? 1 : -1;
            }
            return major;
        }

    }

}
