package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _724pivotIndex {

    public static class Solution0 {

        /**
         前缀和
         思路：
         已知：
         (1)左侧所有元素和(sumLeft) + 中心下标处的数(nums[mid]) + 右侧所有元素和(sumRight) = 总和(sumTotal)
         (2)sumLeft = sumRight
         可得：2 * sumLeft + nums[mid] = sumTotal
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int pivotIndex(int[] nums) {
            int total = Arrays.stream(nums).sum(), sum = 0;
            for (int i = 0; i < nums.length; i++) {
                if (2 * sum + nums[i] == total) return i;
                sum += nums[i];
            }
            return -1;
        }

    }

    public static class Solution1 {

        /**
         前缀和
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int pivotIndex(int[] nums) {
            // 计算数组所有元素和
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }

            // 计算前缀和，同时判断是否能二分数组
            int preSum = 0;
            sum -= nums[0];
            if (preSum == sum) return 0;
            for (int i = 0; i < nums.length - 1; i++) {
                preSum += nums[i];
                sum -= nums[i + 1];
                if (preSum == sum) {
                    return i + 1;
                }
            }
            return -1;
        }

    }



    public static class Solution2 {

        /**
         前缀和: 左求和 * 2 + 中心索引值 = total
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int pivotIndex(int[] nums) {
            int total = Arrays.stream(nums).sum(), sum = 0;
            for (int i = 0; i < nums.length; i++) {
                if (sum * 2 + nums[i] == total) {
                    return i;
                }
                sum += nums[i];
            }
            return -1;
        }

    }

}
