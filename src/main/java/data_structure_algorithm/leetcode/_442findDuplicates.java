package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _442findDuplicates {

    public static class Solution1 {

        /**
         原地哈希：将数组下标i的数字nums[i]交换为i+1
         时间复杂度：O(N)
         空间复杂度：O(1)

         相似题：https://leetcode.cn/problems/find-all-numbers-disappeared-in-an-array/description/
         */
        public List<Integer> findDuplicates(int[] nums) {

            // 按照下标交换元素
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                // 将数组下标i的数字nums[i]交换为i+1
                while (nums[i] != i+1) {
                    // 因为可能存在重复元素，所以这里要break检测，否则会产生死循环
                    if (nums[i] == nums[nums[i]-1]) break;
                    swap(nums, i, nums[i]-1);
                }
            }

            // 遍历数组，找到重复元素
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (nums[i] != i+1) res.add(nums[i]);
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
