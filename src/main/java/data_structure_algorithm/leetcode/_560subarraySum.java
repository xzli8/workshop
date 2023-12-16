package data_structure_algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class _560subarraySum {

    /**
     分析：因为数组中可能有负数，不能使用滑动窗口（负数使得单向性无法满足）
     */


    public static class Solution1 {

        /**
         暴力枚举：枚举区间的左右边界，然后计算区间内的子数组和
         时间复杂度：O(N^3)
         空间复杂度：O(1)
         */
         public int subarraySum(int[] nums, int k) {
             int n = nums.length, count = 0;
             for (int i = 0; i < n; i++) {
                 for (int j = i; j < n; j++) {
                     int sum = 0;
                     for (int l = i; l <= j; l++) {
                         sum += nums[l];
                     }
                     if (sum == k) count++;
                 }
             }
             return count;
         }

    }



    public static class Solution2 {

        /**
         暴力枚举-改进：在枚举右边界的同时计算子数组和(有点前缀和的意思)
         时间复杂度：O(N^2)
         空间复杂度：O(1)
         */
         public int subarraySum(int[] nums, int k) {
             int n = nums.length, count = 0;
             for (int left = 0; left < n; left++) {
                 int sum = 0;
                 for (int right = left; right < n; right++) {
                     sum += nums[right];
                     if (sum == k) {
                         count++;
                     }
                 }
             }
             return count;
         }

    }



    public static class Solution3 {

        /**
         前缀和：先构建前缀和数组，然后枚举区间边界，用前缀和之差代替区间求和
         时间复杂度：O(N^2)
         空间复杂度：O(N)
         */
         public int subarraySum(int[] nums, int k) {
             // 构建前缀和数组
             int n = nums.length;
             int[] preSums = new int[n+1];
             for (int i = 1; i <= n; i++) {
                 preSums[i] = preSums[i - 1] + nums[i - 1];
             }

             // 枚举区间左右边界，计算前缀和之差
             int count = 0;
             for (int left = 0; left < n; left++) {
                 for (int right = left; right < n; right++) {
                     if (preSums[right+1] - preSums[left] == k) {
                         count++;
                     }
                 }
             }
             return count;
         }

    }



    public static class Solution4 {

        /**
         前缀和+哈希表：继续用哈希表优化时间复杂度(空间换时间)，参考“1.两数之和”
         NOTE：这样做不行，因为当k=0时，会重复计数当前元素。
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public int subarraySum(int[] nums, int k) {
             // 计算前缀和
             int n = nums.length;
             int[] preSums = new int[n + 1];
             for (int i = 1; i <= n; i++) {
                 preSums[i] = preSums[i - 1] + nums[i - 1];
             }

             // 构建前缀和到出现次数的映射
             Map<Integer, Integer> preSum2Count = new HashMap<>();
             for (int preSum : preSums) {
                 preSum2Count.put(preSum, preSum2Count.getOrDefault(preSum, 0) + 1);
             }

             // 从后往前遍历前缀和数组，统计子数组出现的个数
             int count = 0;
             for (int i = n; i >= 0; i--) {
                 int remain = preSums[i] - k;
                 if (preSum2Count.containsKey(remain)) {
                     count += preSum2Count.get(remain);
                 }
             }
             return count;
         }

    }



    public static class Solution5 {

        /**
         前缀和+哈希表
         NOTE：一边遍历计算前缀和一边计数，避免重复计数当前元素
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int subarraySum(int[] nums, int k) {
            // 初始化前缀和与前缀和到出现次数的映射
            int preSum = 0, count = 0;
            HashMap<Integer, Integer> preSum2Count = new HashMap<>();
            preSum2Count.put(0, 1);

            // 开始遍历
            for (int i = 0; i < nums.length; i++) {
                preSum += nums[i];
                int remain = preSum - k;
                if (preSum2Count.containsKey(remain)) {
                    count += preSum2Count.get(remain);
                }
                preSum2Count.put(preSum, preSum2Count.getOrDefault(preSum, 0) + 1);
            }
            return count;
        }

    }



}
