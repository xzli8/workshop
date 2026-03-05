package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _31nextPermutation {

    public static class Solution1 {

        /**
         从后往前遍历找第一个逆序对，交换逆序对中的第一个数与已遍历区间中大于该数的最小数，再将已遍历区间升序排列
         ref:https://leetcode.cn/problems/next-permutation/solutions/80560/xia-yi-ge-pai-lie-suan-fa-xiang-jie-si-lu-tui-dao-/
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public void nextPermutation(int[] nums) {
            int n = nums.length;

            // 从后往前遍历
            for (int i = n - 2; i >= 0; i--) {
                // 找第一个逆序对
                if (nums[i] < nums[i + 1]) {
                    // 将逆序对中较小数与已遍历区间中的最小的大于该数的数交换
                    for (int j = n - 1; j > i; j--) {
                        if (nums[j] > nums[i]) {
                            swap(nums, i, j);
                            break;
                        }
                    }

                    // i+1到末尾升序排列
                    reverse(nums, i + 1, n - 1);
                    break;
                }

                // i == 0说明没找到逆序对，此时为降序序列（最大值），需要重新排序为升序序列
                if (i == 0) reverse(nums, 0, n - 1);
            }
        }

        private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }

        private void reverse(int[] nums, int i, int j) {
            while (i < j) {
                swap(nums, i++, j--);
            }
        }

    }

}
