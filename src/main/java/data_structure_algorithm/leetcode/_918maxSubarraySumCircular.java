package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class _918maxSubarraySumCircular {

    public static class Solution1 {

        /**
         贪心(类似题：53)：O(N), O(1)
         ref:https://leetcode.cn/problems/maximum-sum-circular-subarray/solutions/2351107/mei-you-si-lu-yi-zhang-tu-miao-dong-pyth-ilqh/

         思路：可以分为两种情况：
         1.最大子数组没有跨越边界，此时和"53.最大子数组和"一样，求最大子数组和maxSum即可；
         2.最大子数组跨越边界，转换为求未跨越边界的最小子数组和minSum，然后用数组总和totalSum减去最小子数组和minSum即可；
         综合考虑以上两种情况可得：最大子数组和res = max(maxSum, totalSum - minSum)，但需要考虑的特殊情况：如果最小子数组是整个数组，那么最大子数组就是空，不允许，需要特殊处理
         */
        public int maxSubarraySumCircular(int[] nums) {
            int maxSum = 0, minSum = 0, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE, total = 0;
            for (int num : nums) {
                // 1.最大子数组没有跨越边界，此时和"53.最大子数组和"一样，求最大子数组和max即可
                maxSum += num;
                max = Math.max(max, maxSum);
                maxSum = Math.max(0, maxSum);

                // 2.最大子数组跨越边界，转换为求未跨越边界的最小子数组和min，然后用数组总和total减去最小子数组和min即可；
                minSum += num;
                min = Math.min(min, minSum);
                minSum = Math.min(0, minSum);

                total += num;
            }
            // 如果最小子数组是整个数组，那么最大子数组就是空，不允许，需要特殊处理
            return min == total ? max : Math.max(max, total - min);
        }
    }



    public static class Solution2 {

        /**
         前缀和 + 单调队列: O(N), O(N)
         Note: 遇到循环数组，一般思路是将其扩展到2倍长度求解(503)，这里也可以采用类似做法。
         */
        public int maxSubarraySumCircular(int[] nums) {
            int n = nums.length, preSum = nums[0], max = nums[0];
            Deque<int[]> q = new ArrayDeque<>();
            q.offer(new int[] {0, preSum}); // 存下标和前缀和，保证队列中的前缀和从队头到队尾单调递增

            // 扩展到2倍长度进行遍历
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
