package data_structure_algorithm.leetcode;

public class _154findMin {

    public static class Solution1 {

        /**
         二分查找: O(N), O(1)
         Note：二段性，旋转点(最小值)之前的元素都比nums[0]大，旋转点之后的元素都比nums[0]小。找第一个小于nums[0]的元素。
         注意：如果旋转使得相同元素进行了分裂(首尾元素一样)，那么二段性不满足，需要首先去除尾部元素以恢复二段性。
         */
        public int findMin(int[] nums) {
            int left = 0, right = nums.length - 1;
            while (right >= 0 && nums[right] == nums[0]) right--;   // 将数组尾部与nums[0]相同的元素忽略，恢复二段性
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (nums[mid] < nums[0]) {
                    if (mid == 0 || nums[mid - 1] >= nums[0]) return nums[mid];
                    else right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return nums[0];
        }

    }


}
