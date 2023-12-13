package data_structure_algorithm.leetcode;

public class _35searchInsert {

    public static class Solution1 {

        /**
         二分查找：查找第一个大于等于目标值的位置
         时间复杂度：O(logN)
         空间复杂度：O(1)
         */
        public int searchInsert(int[] nums, int target) {
            int left = 0, right = nums.length - 1;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                }
                else {
                    if ((mid == 0) || (nums[mid - 1] < target)) return mid;
                    else right = mid - 1;
                }
            }
            return nums.length; // 这里注意，target > nums[lastIndex]时，返回
        }

    }

}
