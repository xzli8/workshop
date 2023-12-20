package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class _1124longestWPI {

    public static class Solution1 {

        /**
         前缀和 + 单调(递减)栈
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int longestWPI(int[] hours) {
            // 计算前缀和
            int n = hours.length;
            int[] preSum = new int[n+1];
            for (int i = 0; i < n; i++) {
                // 工作时间超过8小时记为1，不足8小时记为-1
                preSum[i+1] = preSum[i] + (hours[i] > 8 ? 1 : -1);
            }

            // 构建前缀和的单调(递减)栈，栈中元素为前缀和数组的下标
            Deque<Integer> s = new ArrayDeque<>(n);
            s.push(0);  // preSum[0]的下标
            for (int i = 1; i < n+1; i++) {
                if (preSum[i] < preSum[s.peek()]) {
                    s.push(i);
                }
            }

            // 逆序遍历，找最大区间(注意这里遍历的是前缀和数组，所以其下标范围是[n, 0))
            int res = 0;
            for (int i = n; i > 0; i--) {
                while (!s.isEmpty() && preSum[i] > preSum[s.peek()]) {
                    res = Math.max(res, i - s.pop());
                }
            }
            return res;
        }

    }

}
