package data_structure_algorithm.leetcode;

public class _704search {

    public static class Solution1 {

        /**
         二分查找(迭代)
         时间复杂度：O(logN)
         空间复杂度：O(1)
         */
        public int search(int[] nums, int target) {
            int left = 0, right = nums.length - 1;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return -1;
        }

    }

}
