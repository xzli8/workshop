package data_structure_algorithm.algorithm.search;

public class BinarySearch {

    /**
     * Case1: 在无重复元素的升序数组中，找值等于target的元素，返回其下标，找不到返回-1
     */
    public static class Solution1 {

        public int search(int[] nums, int target) {
            int left = 0, right = nums.length - 1;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (nums[mid] == target) return mid;
                if (nums[mid] < target) left = mid + 1;
                else right = mid - 1;
            }
            return -1;
        }

    }



    /**
     * Case2: 在有重复元素的非降序数组中，找第一个值等于target的元素，返回其下标，找不到返回-1
     */
    public static class Solution2 {

        public int search(int[] nums, int target) {
            int left = 0, right = nums.length - 1;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (nums[mid] == target) {
                    if (mid == 0 || nums[mid - 1] != target) {
                        return mid;
                    } else {
                        right = mid - 1;
                    }
                }
                if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return -1;
        }

    }



    /**
     * Case3: 在有重复元素的非降序数组中，找最后一个值等于target的元素，返回其下标，找不到返回-1
     */
    public static class Solution3 {

        public int search(int[] nums, int target) {
            int left = 0, right = nums.length - 1;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (nums[mid] == target) {
                    if (mid == nums.length - 1 || nums[mid + 1] != target) {
                        return mid;
                    } else {
                        left = mid + 1;
                    }
                }
                if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return -1;
        }

    }



    /**
     * Case4: 在有重复元素的非降序数组中，找第一个值大于等于target的元素，返回其下标，找不到返回-1
     */
    public static class Solution4 {

        public int search(int[] nums, int target) {
            int left = 0, right = nums.length - 1;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (nums[mid] >= target) {
                    if (mid == 0 || nums[mid - 1] < target) {
                        return mid;
                    } else {
                        right = mid - 1;
                    }
                } else {
                    left = mid + 1;
                }
            }
            return -1;
        }

    }



    /**
     * Case5: 在有重复元素的非降序数组中，找最后一个值小于等于target的元素，返回其下标，找不到返回-1
     */
    public static class Solution5 {

        public int search(int[] nums, int target) {
            int left = 0, right = nums.length - 1;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (nums[mid] <= target) {
                    if (mid == nums.length - 1 || nums[mid + 1] > target) {
                        return mid;
                    } else {
                        left = mid + 1;
                    }
                } else {
                    right = mid - 1;
                }
            }
            return -1;
        }

    }


}
