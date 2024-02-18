package data_structure_algorithm.leetcode;

public class _169majorityElement {

    public static class Solution0 {

        /**
         摩根投票法：将众树记为+1，非众数记为-1，遍历求和，当和为0时，重新选举一个众数
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int majorityElement(int[] nums) {
            int major = nums[0], count = 1;
            for (int i = 1; i < nums.length; i++) {
                if (count == 0) major = nums[i];
                if (nums[i] == major) count++;
                else count--;
            }
            return major;
        }

    }



    public static class Solution1 {

        /**
         摩根投票法
         思路：将众数记为1，非众数记为-1，相加。当相加的结果为0时，重新选举一个众数
         时间复杂度：O(n)
         空间复杂度：O(1)
         */
        public int majorityElement(int[] nums) {
            int count = 0;
            Integer candidate = null;
            for (int num : nums) {
                if (count == 0) {
                    candidate = num;
                }
                count += (num == candidate) ? 1 : -1;
            }
            return candidate;
        }

    }

}
