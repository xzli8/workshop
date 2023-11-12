package leetcode;

public class _81search {

    /**
     二分：如果旋转使得相同元素进行了分裂，那么二段性不满足，需要首先恢复二段性
     */
    public boolean search(int[] nums, int target) {
        int n = nums.length, left = 0, right = n - 1;

        // 当尾部元素与nums[0]相同时，破坏了二段性，需要首先排除尾部元素恢复二段性
        while (right > 0 && nums[right] == nums[0]) {
            right--;
            n--;
        }

        // 用二分找旋转点（找最后一个大于等于nums[0]的元素）
        int maxIndex = n - 1;   // 默认值为数组没有旋转时
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] >= nums[0]) {
                if (mid == n - 1 || nums[mid + 1] < nums[0]) {
                    maxIndex = mid;
                    break;
                } else {
                    left = mid + 1;
                }
            } else {
                right = mid - 1;
            }
        }

        // 首先确定target在哪个有序区间，然后再二分查找
        if (nums[0] <= target && target <= nums[maxIndex]) {
            left = 0;
            right = maxIndex;
        } else {
            left = maxIndex + 1;
            right = n - 1;
        }

        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) return true;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

}
