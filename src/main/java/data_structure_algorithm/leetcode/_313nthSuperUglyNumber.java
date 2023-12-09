package data_structure_algorithm.leetcode;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class _313nthSuperUglyNumber {

    public static class Solution1 {

        /**
         优先队列 + 哈希表(超出时间限制)
         时间复杂度：O(NMlogN)
         空间复杂度：O(N)
         */
         public int nthSuperUglyNumber(int n, int[] primes) {
             int m = primes.length;

             PriorityQueue<Long> pq = new PriorityQueue<>();
             Set<Long> s = new HashSet<>();
             pq.offer(1L);
             s.add(1L);
             for (int i = 1; i < n; i++) {
                 long min = pq.poll();
                 for (int prime : primes) {
                     long tmp = min * prime;
                     if (s.add(tmp)) {
                         pq.offer(tmp);
                     }
                 }
             }
             long res = pq.peek();
             return (int) res;
         }

    }



    public static class Solution2 {

        /**
         动态规划
         */
        public int nthSuperUglyNumber(int n, int[] primes) {
            int m = primes.length;
            int[] idx = new int[m];
            long[] res = new long[m];

            int[] dp = new int[n];
            dp[0] = 1;
            for (int i = 1; i < n; i++) {
                long min = Long.MAX_VALUE;
                for (int j = 0; j < m; j++) {
                    res[j] = (long) primes[j] * dp[idx[j]];
                    min = Math.min(min, res[j]);
                }
                dp[i] = (int) min;
                for (int j = 0; j < m; j++) {
                    if (res[j] == min) {
                        idx[j]++;
                    }
                }
            }
            return dp[n-1];
        }

    }



}
