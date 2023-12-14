package data_structure_algorithm.leetcode;

public class _26removeDuplicates {

    public static class Solution1 {

        /**
         双指针：慢指针指向不重复元素的下一个元素，快指针指向遍历位置
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int removeDuplicates(int[] nums) {
            int n = nums.length;
            if (n <= 1) return n;

            int slow = 1, fast = 1;
            while (fast < nums.length) {
                if (nums[fast] != nums[slow - 1]) {
                    nums[slow++] = nums[fast];
                }
                fast++;
            }
            return slow;
        }

    }

}
