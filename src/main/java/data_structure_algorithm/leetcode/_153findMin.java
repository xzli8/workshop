package data_structure_algorithm.leetcode;

public class _153findMin {

    /**
     二分搜索: O(logN), O(1)
     Note: 经过旋转的数组，显然前半段满足 >= nums[0]，而后半段不满足 >= nums[0]。我们可以以此作为依据，通过「二分」找到旋转点。(二分查找的二段性)
        所以，我们需要找第一个值小于nums[0]的元素.
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
