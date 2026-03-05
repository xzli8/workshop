package data_structure_algorithm.leetcode;

import java.util.Arrays;
import java.util.TreeSet;

public class _2733findNonMinOrMax {

    public static class Solution1 {

        /**
         排序：O(NlogN), O(1)
         */
         public int findNonMinOrMax(int[] nums) {
             int n = nums.length;
             if (n <= 2) return -1;
             Arrays.sort(nums);
             return nums[1];
         }


        /**
         部分排序：O(1), O(1)
         Note: 由于数组元素各不相同，所以答案一定在前三个数中，不妨取前三个数的中间值
         */
        public int findNonMinOrMaxII(int[] nums) {
            if (nums.length < 3) return -1;
            Arrays.sort(nums, 0, 3);    // 只对前三个数排序
            return nums[1];
        }

    }


    public static class Solution2 {

        /**
         有序集合TreeSet: O(NlogN), O(N)
         */
         public int findNonMinOrMax(int[] nums) {
             int n = nums.length;
             if (n <= 2) return -1;

             TreeSet<Integer> s = new TreeSet<>();
             for (int num : nums) {
                 s.add(num);
             }
             s.pollFirst();
             return s.first();
         }

    }


    public static class Solution3 {

        /**
         两变量: O(N), O(1)
         */
         public int findNonMinOrMax(int[] nums) {
             int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
             for (int num : nums) {
                 min = Math.min(min, num);
                 max = Math.max(max, num);
             }
             for (int num : nums) {
                 if (num != min && num != max) return num;
             }
             return -1;
         }

    }

}
