package dsa.leetcode;

public class _33search {

    /**
     二分：首先根据二段性找旋转点，然后判断target在哪个单调区间，再在相应单调区间用二分查找target
     */
    public int search(int[] nums, int target) {
        int n = nums.length;

        // 方法1:找最小值
        // int minIndex = findMinIndex(nums);
        // if (nums[minIndex] <= target && target <= nums[n - 1]) {
        //     return binarySearch(nums, minIndex, n - 1, target);
        // } else {
        //     return binarySearch(nums, 0, minIndex - 1 < 0 ? n - 1 : minIndex - 1, target);
        // }

        // 方法2:找最大值
        int maxIndex = findMaxIndex(nums);
        if (nums[0] <= target && target <= nums[maxIndex]) {
            return binarySearch(nums, 0, maxIndex, target);
        } else {
            return binarySearch(nums, (maxIndex + 1) % n, n - 1, target);
        }
    }

    // 找最后一个大于等于nums[0]的元素的位置（参考153）
    public int findMaxIndex(int[] nums) {
        int n = nums.length, left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] >= nums[0]) {
                if (mid == n - 1 || nums[mid + 1] < nums[0]) {
                    return mid;
                } else {
                    left = mid + 1;
                }
            } else {
                right = mid - 1;
            }
        }
        return n - 1;
    }

    // 找第一个小于nums[0]的元素的位置（参考153）
    public int findMinIndex(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] < nums[0]) {
                if (mid == 0 || nums[mid - 1] >= nums[0]) {
                    return mid;
                } else {
                    right = mid - 1;
                }
            } else {
                left = mid + 1;
            }
        }
        return 0;
    }

    public int binarySearch(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) return mid;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

}
