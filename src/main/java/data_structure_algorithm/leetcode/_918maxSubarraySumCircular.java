package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class _918maxSubarraySumCircular {

    public static class Solution1 {

        /**
         问题转换 + 动态规划(贪心)
         ref:https://leetcode.cn/problems/maximum-sum-circular-subarray/solutions/2351107/mei-you-si-lu-yi-zhang-tu-miao-dong-pyth-ilqh/
         思路：可以分为两种情况：
         1.最大子数组没有跨越边界，此时和"53.最大子数组和"一样，求最大子数组和maxSum即可；
         2.最大子数组跨越边界，转换为求未跨越边界的最小子数组和minSum，然后用数组总和totalSum减去最小子数组和minSum即可；
         综合考虑以上两种情况可得：最大子数组和res = max(maxSum, totalSum - minSum)
         需要考虑的特殊情况：如果最小子数组是整个数组，那么最大子数组就是空，不允许，需要特殊处理
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
         public int maxSubarraySumCircular(int[] nums) {
             // total为数组元素和，maxSum为最大子数组和，minSum为最小子数组和，max为包含当前元素的最大子数组和，min为包含当前元素的最小子数组和
             int totalSum = 0, maxSum = nums[0], minSum = nums[0], max = 0, min = 0;
             for (int num : nums) {
                 max = Math.max(max + num, num);
                 maxSum = Math.max(maxSum, max);
                 min = Math.min(min + num, num);
                 minSum = Math.min(minSum, min);
                 totalSum += num;
             }
             // 通过"minSum == totalSum"来判断最小子数组是整个数组
             return minSum == totalSum ? maxSum : Math.max(maxSum, totalSum - minSum);
         }

    }



    public static class Solution2 {

        /**
         前缀和 + 单调队列
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int maxSubarraySumCircular(int[] nums) {
            int n = nums.length, preSum = nums[0], max = nums[0];
            Deque<int[]> q = new ArrayDeque<>();
            q.offer(new int[] {0, preSum});
            for (int i = 1; i < 2 * n; i++) {
                // 区间元素不能重复，即区间长度不大于n
                while (!q.isEmpty() && i - q.peekFirst()[0] > n) q.pollFirst();

                // 更新前缀和以及子数组的最大值
                preSum += nums[i % n];
                max = Math.max(max, preSum - q.peekFirst()[1]);

                // 更新单调队列，将前缀和较大的剔除(前面的元素作为被减数越小，得到的子数组和越大)
                while (!q.isEmpty() && q.peekLast()[1] >= preSum) q.pollLast();
                q.offer(new int[] {i, preSum});
            }
            return max;
        }

    }

}
