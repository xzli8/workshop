package data_structure_algorithm.leetcode;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class _264nthUglyNumber {


    public static class Solution0 {

        /**
         动态规划
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int nthUglyNumber(int n) {
            // 定义状态：dp[i]表示第i个丑数
            int[] dp = new int[n];

            // 初始状态
            dp[0] = 1;

            // 状态转移
            int i2 = 0, i3 = 0, i5 = 0;
            for (int i = 1; i < n; i++) {
                int n2 = 2 * dp[i2], n3 = 3 * dp[i3], n5 = 5 * dp[i5];
                dp[i] = Math.min(Math.min(n2, n3), n5);
                if (dp[i] == n2) i2++;
                if (dp[i] == n3) i3++;
                if (dp[i] == n5) i5++;
            }
            return dp[n - 1];
        }

    }



    public static class Solution1 {

        /**
         1.优先队列（小顶堆）
         1.先将最小的丑数1加入堆中；
         2.取出堆顶元素x，依次将2x, 3x, 5x加入；
         3.因为有重复元素，所以注意要用set去重；
         4.第n次取出的元素就是第n个丑数

         时间复杂度：O(NlogN)
         空间复杂度：O(N)
         */
         public int nthUglyNumber(int n) {
             int[] factors = new int[]{2, 3, 5};
             PriorityQueue<Long> pq = new PriorityQueue<>();
             Set<Long> set = new HashSet<>();
             pq.offer(1L);
             set.add(1L);
             int ugly = 0;
             for (int i = 0; i < n; i++) {
                 long x = pq.poll();
                 ugly = (int) x;
                 for (int factor : factors) {
                     long num = x * factor;
                     if (set.add(num)) {
                         pq.offer(num);
                     }
                 }
             }
             return ugly;
         }

    }



    public static class Solution2 {

        /**
         动态规划
         思路：https://leetcode.cn/problems/ugly-number-ii/solutions/110653/san-zhi-zhen-fang-fa-de-li-jie-fang-shi-by-zzxn
         1.定义状态：dp[i]表示第i个丑数
         2.状态转移：dp[i] = min(dp[p2]*2, dp[p3]*3, dp[p5]*5)，pi表示有资格与i相乘的最小丑数的位置
         分别比较dp[i]与dp[p2]*2, dp[p3]*3, dp[p5]*5是否相等，如果相等，那么相应指针++
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int nthUglyNumber(int n) {
            int[] dp = new int[n+1];
            dp[1] = 1;
            int p2 = 1, p3 = 1, p5 = 1;
            for (int i = 2; i <= n; i++) {
                int num2 = dp[p2] * 2, num3 = dp[p3] * 3, num5 = dp[p5] * 5;
                dp[i] = Math.min(Math.min(num2, num3), num5);
                if (dp[i] == num2) {
                    p2++;
                }
                if (dp[i] == num3) {
                    p3++;
                }
                if (dp[i] == num5) {
                    p5++;
                }
            }
            return dp[n];
        }

    }



}
