package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _280wiggleSort {

    /**
     * 参考题解：https://www.cnblogs.com/grandyang/p/5177285.html
     */

    public static class Solution1 {

        /**
         *  排序 + 交换：但不符合原地重排的要求
         *
         *      时间复杂度：O(NlogN)
         *      空间复杂度：O(logN)
         */
        public void wiggleSort(int[] nums) {
            Arrays.sort(nums);
            for (int i = 2; i < nums.length; i += 2) {
                swap(nums, i - 1, i);
            }
        }

        private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }

    }



    public static class Solution2 {

        /**
         *  奇偶交换
         *      思路：摆动排序后元素的规律是"当i为奇数时，nums[i] >= nums[i - 1]；当i为偶数时，nums[i] <= nums[i - 1]"，当不满足规律时交换当前元素和其前面元素。
         *      时间复杂度：O(N)
         *      空间复杂度：O(1)
         */
        public void wiggleSort(int[] nums) {
            for (int i = 1; i < nums.length; i++) {
                if ((i % 2 == 1 && nums[i] < nums[i - 1]) || (i % 2 == 0 && nums[i] > nums[i - 1])) {
                    swap(nums, i, i - 1);
                }
            }
        }

        private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }

    }

}
