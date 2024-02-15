package data_structure_algorithm.leetcode;

public class _154findMin {

    public static class Solution0 {

        /**
         二分查找
         二段性：旋转点(最小值)之前的元素都比nums[0]大，旋转点之后的元素都比nums[0]小。找第一个小于nums[0]的元素。
         注意：重复元素出现在尾部时会破坏二段性，需要去除
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int findMin(int[] nums) {
            int left = 0, right = nums.length - 1;
            while (right >= 0 && nums[right] == nums[0]) right--;
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



    public static class Solution1 {

        /**
         二分法：如果旋转使得相同元素进行了分裂，那么二段性不满足，需要首先恢复二段性
         */
        public int findMin(int[] nums) {
            int n = nums.length;
            int left = 0, right = n - 1;

            // 将数组尾部与nums[0]相同的元素忽略，恢复二段性
            while (right >= 0 && nums[right] == nums[0]) {
                right--;
            }

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
            return nums[0];
        }

    }

}
