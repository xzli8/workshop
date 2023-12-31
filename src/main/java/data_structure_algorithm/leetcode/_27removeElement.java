package data_structure_algorithm.leetcode;

public class _27removeElement {

    public static class Solution1 {

        /**
         双指针：慢指针指向第一个可能被移除的位置，快指针指向遍历位置。需要移动更多的元素，但不会改变元素位置。
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int removeElement(int[] nums, int val) {
            int slow = 0, fast = 0;
            while (fast < nums.length) {
                if (nums[fast] != val) {
                    nums[slow++] = nums[fast];
                }
                fast++;
            }
            return slow;
        }

    }



    public static class Solution2 {

        /**
         相向双指针：左右两个指针从两端开始遍历，这样会改变元素的顺序，但能保证移动最少数量的元素
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
         public int removeElement(int[] nums, int val) {
             int i = 0, j = nums.length;
             while (i < j) {
                 if (nums[i] == val) {
                     nums[i] = nums[j-1];
                     j--;
                 } else {
                     i++;
                 }
             }
             return i;
         }

    }



    public static class Solution3 {

        /**
         双指针：左指针从左往右移，找第一个等于val的元素；右指针从右往左移，找第一个不等于val的元素，交换
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int removeElement(int[] nums, int val) {
            int left = 0, right = nums.length - 1;
            while (left <= right) {
                while (left <= right && nums[left] != val) {
                    left++;
                }
                while (left <= right && nums[right] == val) {
                    right--;
                }
                if (left <= right) swap(nums, left++, right--);
            }
            return left;
        }

        private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }

    }



}
