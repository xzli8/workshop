package data_structure_algorithm.leetcode;

public class _34searchRange {

    public static class Solution1 {

        /**
         二分查找
         时间复杂度：O(logN)
         空间复杂度：O(1)
         */
        public int[] searchRange(int[] nums, int target) {

            // 初始化下标
            int firstIndex = -1;
            int lastIndex = -1;

            // 找第一个位置
            int left = 0, right = nums.length - 1;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (nums[mid] == target) {
                    if ((mid == 0) || (nums[mid - 1] != target)) {
                        firstIndex = mid;
                        break;
                    }
                    else {
                        right = mid - 1;
                    }
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            // 找最后一个位置
            left = 0;
            right = nums.length - 1;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (nums[mid] == target) {
                    if ((mid == (nums.length-1)) || nums[mid+1] != target) {
                        lastIndex = mid;
                        break;
                    }
                    else {
                        left = mid + 1;
                    }
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            // 返回结果
            return new int[]{firstIndex, lastIndex};
        }

    }

}
