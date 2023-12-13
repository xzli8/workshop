package data_structure_algorithm.leetcode;

public class _283moveZeroes {

    public static class Solution1 {

        /**
         /**
         双指针：慢指针指向第一个未被处理的元素，快指针指向当前遍历元素
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public void moveZeroes(int[] nums) {
            int slow = 0, fast = 0;
            while (fast < nums.length) {
                if (nums[fast] != 0) {
                    nums[slow++] = nums[fast];
                }
                fast++;
            }

            // 尾部元素赋值为0
            while (slow < nums.length) {
                nums[slow++] = 0;
            }
        }

    }

}
