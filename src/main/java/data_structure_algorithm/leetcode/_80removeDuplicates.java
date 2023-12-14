package data_structure_algorithm.leetcode;

public class _80removeDuplicates {

    public static class Solution1 {

        /**
         双指针：(类似题："26.删除有序数组中的重复项")
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int removeDuplicates(int[] nums) {
            int n = nums.length;
            if (n <= 2) return n;

            int slow = 2, fast = 2;
            while (fast < n) {
                if (nums[fast] != nums[slow - 2]) {
                    nums[slow++] = nums[fast];
                }
                fast++;
            }
            return slow;
        }

    }

}
