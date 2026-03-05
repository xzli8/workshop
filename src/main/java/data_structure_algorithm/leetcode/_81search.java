package data_structure_algorithm.leetcode;

public class _81search {

    public static class Solution1 {

        /**
         二分搜索: O(N), O(1)
         Note: 首先根据二段性找旋转点，然后判断target在哪个单调区间，再在相应单调区间用二分查找target。
            二段性：经过旋转的数组，显然前半段满足 >= nums[0]，而后半段不满足 >= nums[0]。我们可以以此作为依据，通过「二分」找到旋转点。
            重复元素：如果旋转使得相同元素进行了分裂(首尾元素一样)，那么二段性不满足，需要首先去除尾部元素以恢复二段性。
         */
        public boolean search(int[] nums, int target) {
            // 去除尾部与nums[0]相同的元素，以保持二段性
            int n = nums.length, left = 0, right = n - 1;
            while (right > 0 && nums[right] == nums[0]) {
                right--;
                n--;
            }

            // 根据二段性找旋转点，用二分查找找第一个小于nums[0]的元素(或最后一个大于等于nums[0]的元素)
            // 二段性：旋转点之前的元素都大于等于nums[0]，旋转点之后的元素都小于nums[0]
            int minIdx = 0; // 初始化第一个小于nums[0]的元素下标为0(数组未旋转时最小值就是nums[0])
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (nums[mid] < nums[0]) {
                    if (mid == 0 || nums[mid - 1] >= nums[0]) {
                        minIdx = mid;
                        break;
                    } else {
                        right = mid - 1;
                    }
                } else {
                    left = mid + 1;
                }
            }

            // 先判断target落在哪个有序区间，然后二分查找
            if (nums[minIdx] <= target && target <= nums[n - 1]) {
                left = minIdx;
                right = n - 1;
            } else {
                left = 0;
                right = minIdx - 1;
            }
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (nums[mid] == target) return true;
                else if (nums[mid] < target) left = mid + 1;
                else right = mid - 1;
            }
            return false;
        }

    }


    public static class Solution2 {

        /**
         二分搜索：O(N), O(1)
         Note: 首先根据二段性找旋转点，然后判断target在哪个单调区间，再在相应单调区间用二分查找target。
            二段性：经过旋转的数组，显然前半段满足 >= nums[0]，而后半段不满足 >= nums[0]。我们可以以此作为依据，通过「二分」找到旋转点。
            重复元素：如果旋转使得相同元素进行了分裂(首尾元素一样)，那么二段性不满足，需要首先去除尾部元素以恢复二段性。
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

}
