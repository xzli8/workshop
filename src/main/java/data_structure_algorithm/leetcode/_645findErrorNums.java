package data_structure_algorithm.leetcode;

public class _645findErrorNums {

    public static class Solution1 {

        /**
         原地哈希：将第i个位置放置元素i
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int[] findErrorNums(int[] nums) {
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                while (nums[i] != i + 1 && nums[i] != nums[nums[i] - 1]) {
                    swap(nums, i, nums[i] - 1);
                }
            }
            for (int i = 0; i < n; i++) {
                if (nums[i] != i + 1) {
                    return new int[] {nums[i], i + 1};
                }
            }
            return null;
        }

        private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }

    }

}
