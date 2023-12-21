package data_structure_algorithm.leetcode;

public class _75sortColors {

    public static class Solution1 {

        /**
         双指针
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public void sortColors(int[] nums) {
            int n = nums.length;

            // 第一趟扫描，将红色和非红色分开
            int left = 0, right = n - 1;
            while (left < right) {
                while (nums[left] == 0 && left < right) {
                    left++;
                }
                while (nums[right] != 0 && left < right) {
                    right--;
                }
                swap(nums, left, right);
            }

            // 第二趟扫描，将白色和蓝色分开
            right = n - 1;
            while (left < right) {
                while (nums[left] == 1 && left < right) {
                    left++;
                }
                while (nums[right] != 1 && left < right) {
                    right--;
                }
                swap(nums, left, right);
            }
        }

        private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }

    }

}
