package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _448findDisappearedNumbers {

    public static class Solution1 {

        /**
         原地哈希：将数组下标为i的数字nums[i]交换成i+1
         时间复杂度：O(N)
         空间复杂度：O(1)

         相似题：https://leetcode.cn/problems/find-all-duplicates-in-an-array/description/
         */
        public List<Integer> findDisappearedNumbers(int[] nums) {
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                // 数组下标i的数字nums[i]交换为i+1
                while (nums[i] != i+1) {
                    // 因为存在重复元素，如果不break，会产生死循环
                    if (nums[i] == nums[nums[i]-1]) break;
                    swap(nums, i, nums[i]-1);
                }
            }

            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                // 数组下标i的数字不是i+1，就是我们要找的结果
                if (nums[i] != i+1) {
                    res.add(i+1);
                }
            }
            return res;
        }

        private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }


    }

}
