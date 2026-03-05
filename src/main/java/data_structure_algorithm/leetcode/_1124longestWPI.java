package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class _1124longestWPI {

    /**
     * Note: 将劳累的一天(hour > 8)记为1，不劳累的一天(hour <= 8)记为-1，转换为计算nums的最长子数组，其元素和大于0
     */

    public static class Solution1 {

        /**
         PreSum + Enum: O(N^2), O(N)
         */
        public int longestWPI(int[] hours) {
            // 计算前缀和数组
            int n = hours.length;
            int[] preSum = new int[n + 1];
            for (int i = 0; i < n; i++) {
                preSum[i + 1] = preSum[i] + (hours[i] > 8 ? 1 : -1);
            }

            // 枚举区间
            int maxLen = 0;
            for (int i = 0; i <= n; i++) {
                for (int j = i + 1; j <= n; j++) {
                    if (preSum[j] - preSum[i] > 0) {
                        maxLen = Math.max(maxLen, j - i);
                    }
                }
            }
            return maxLen;
        }

    }

    public static class Solution2 {

        /**
         前缀和 + 单调(递减)栈
         ref: https://leetcode.cn/problems/longest-well-performing-interval/solutions/2110211/liang-chong-zuo-fa-liang-zhang-tu-miao-d-hysl/
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
            for (int i = 1; i <= n; i++) {
                // 假设preSum[i] > preSum[s.peek()], 如果i想要成为最长子数组的左端点，后面必然有preSum[k] - preSum[i] > 0，
                // 那么preSum[k] > preSum[i] > preSum[s.peek()]，并且s.peek() < i可以形成一个更长的子数组，
                // 所以preSum[i]没用不用入栈。这就是为什么构建单调递减栈的原因。
                if (preSum[i] < preSum[s.peek()]) {
                    s.push(i);
                }
            }

            // 对于单调递减栈中的每个元素下标j，需要找到其后面最远的preSum[i]，满足preSum[i] > preSum[j]
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
