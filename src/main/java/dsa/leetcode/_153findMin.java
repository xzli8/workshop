package dsa.leetcode;

public class _153findMin {

    /**
     二分：找第一个值小于nums[0]的元素
     */
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] < nums[0]) {
                if (mid == 0 || nums[mid - 1] >= nums[0]) {
                    return nums[mid];
                } else {
                    right = mid - 1;
                }
            } else {
                left = mid + 1;
            }
        }
        // 没找到说明不满足二段性，数组未旋转，最小值就是nums[0]，返回即可
        return nums[0];
    }

}
