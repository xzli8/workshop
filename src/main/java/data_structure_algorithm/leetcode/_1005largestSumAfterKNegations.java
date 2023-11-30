package data_structure_algorithm.leetcode;

import java.util.Arrays;
import java.util.stream.IntStream;

public class _1005largestSumAfterKNegations {

    public static class Solution1 {

        /**
         贪心：先尽可能将绝对值大的负数反转，如果全部反转为正数后还有反转次数，则不断反转绝对值最小的正数
         时间复杂度：O(NlogN)
         空间复杂度：O(1)
         */
        public int largestSumAfterKNegations(int[] nums, int k) {
            // 按绝对值从大到小排序
            // Arrays.sort(nums, (num1, num2) -> Math.abs(num2) - Math.abs(num1));
            nums = IntStream.of(nums).boxed().sorted((n1, n2) -> Math.abs(n2) - Math.abs(n1))
                    .mapToInt(Integer::intValue).toArray();

            // 反转负数
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                if (nums[i] < 0 && k > 0) {
                    nums[i] *= -1;
                    k--;
                }
            }

            // 如果k还有剩余，反转最小的正数
            if (k % 2 == 1) nums[n - 1] *= -1;

            // 计算数组元素和
            // int sum = 0;
            // for (int num : nums) {
            //     sum += num;
            // }
            // return sum;
            return Arrays.stream(nums).sum();
        }

    }



}
