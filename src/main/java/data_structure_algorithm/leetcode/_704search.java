package data_structure_algorithm.leetcode;

public class _704search {

    public static class Solution1 {

        /**
         二分查找(迭代): O(logN), O(1)
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


    public static class Solution2 {

        /**
         二分查找(递归): O(logN), O(logN)
         */
        public int search(int[] nums, int target) {
            return bsearch(nums, 0, nums.length - 1, target);
        }

        private int bsearch(int[] nums, int left, int right, int target) {
            if (left > right) return -1;
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) return bsearch(nums, mid + 1, right, target);
            else return bsearch(nums, left, mid - 1, target);
        }

    }

}
