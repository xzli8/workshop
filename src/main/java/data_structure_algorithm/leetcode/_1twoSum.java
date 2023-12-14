package data_structure_algorithm.leetcode;

import java.util.HashMap;

public class _1twoSum {

    public static class Solution1 {

        /**
         暴力搜索
         时间复杂度：O(N^2)
         空间复杂度：O(1)
         */
        public int[] twoSum(int[] nums, int target) {
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[i] + nums[j] == target) {
                        return new int[] {i, j};
                    }
                }
            }
            return new int[] {};
        }

    }



    public static class Solution2 {

        /**
         哈希表：构建从元素到下标的映射，再找元素对
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public int[] twoSum(int[] nums, int target) {
             HashMap<Integer, Integer> num2Index = new HashMap<>();
             for (int i = 0; i < nums.length; i++) {
                 num2Index.put(nums[i], i);
             }
             for (int i = 0; i < nums.length; i++) {
                 if (num2Index.containsKey(target - nums[i]) && num2Index.get(target - nums[i]) != i) {
                     return new int[] {i, num2Index.get(target - nums[i])};
                 }
             }
             return new int[] {};
         }

    }



    public static class Solution3 {

        /**
         哈希表：构建从元素到下标的映射，同时找元素对
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public int[] twoSum(int[] nums, int target) {
             HashMap<Integer, Integer> num2Index = new HashMap<>();
             for (int i = 0; i < nums.length; i++) {
                 if (num2Index.containsKey(target - nums[i])) {
                     return new int[] {i, num2Index.get(target - nums[i])};
                 }
                 num2Index.put(nums[i], i);
             }
             return new int[] {};
         }

    }



}
