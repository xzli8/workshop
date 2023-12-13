package data_structure_algorithm.leetcode;

public class _26removeDuplicates {

    public static class Solution1 {

        /**
         双指针：慢指针指向不重复元素中的最后一个，快指针指向遍历位置
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int removeDuplicates(int[] nums) {
            // 因为第一个元素num[0]前面没有元素，所以nums[0]不可能重复，nums[1]是第一个可能重复的元素
            // 所以slow初始化为0, fast初始化为1
            int slow = 0, fast = 1;
            while (fast < nums.length) {
                if (nums[fast] != nums[slow]) {
                    nums[++slow] = nums[fast];
                }
                fast++;
            }
            return slow + 1;
        }

    }

}
