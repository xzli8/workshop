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



}
