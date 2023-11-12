package dsa.leetcode;

public class _540singleNonDuplicate {

    /**
     异或（出现偶数次的元素会被置0，出现奇数次的元素会被保留）
     时间复杂度：O(N)
     空间复杂度：O(1)
     */
    // public int singleNonDuplicate(int[] nums) {
    //     int res = 0;
    //     for (int num : nums) {
    //         res = res ^ num;
    //     }
    //     return res;
    // }

    /**
     二分
     关键在于找二段性：
     target前的元素，第一次出现的元素下标为偶数，第二次出现的下标为奇数；
     target后的元素，第一次出现的元素下标为奇数，第二次出现的下标为偶数。

     时间复杂度：O(logN)
     空间复杂度：O(1)
     */
    public int singleNonDuplicate(int[] nums) {
        int n = nums.length, left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            // 当mid是只出现一次的元素时，返回
            if ((mid == 0 || nums[mid - 1] != nums[mid]) && (mid == n - 1 || nums[mid + 1] != nums[mid])) {
                return nums[mid];
            }

            // 当mid出现在target前面时，调整左边界
            if (before(nums, mid)) {
                left = mid + 1;
            }
            // 否则，调整右边界
            else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public boolean before(int[] nums, int mid) {
        int n = nums.length;
        // 当mid为偶数时并且是第一次出现 or 当mid为奇数时并且是第二次出现
        return (mid % 2 == 0 && nums[mid + 1] == nums[mid])
                || (mid % 2 != 0 && nums[mid - 1] == nums[mid]);

        // 这里不用再判断边界了
        // return (mid % 2 == 0 && (mid + 1 < n && nums[mid + 1] == nums[mid]))
        //     || (mid % 2 != 0 && (mid - 1 >= 0 && nums[mid - 1] == nums[mid]));
    }

}
