package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class _10004designRateLimiterSystem {

    public static class Solution1 {

        class RateLimiter {

            private int n, t;
            private Deque<Integer> q = new ArrayDeque<>();

            public RateLimiter(int n, int t) {
                this.n = n;
                this.t = t;
            }

            public boolean shouldAllow(int timestamp) {
                while (!q.isEmpty() && q.peek() <= timestamp - t) {
                    q.poll();
                }
                boolean shouldAllow = q.size() + 1 <= n;
                if (shouldAllow) {
                    q.offer(timestamp);
                }
                return shouldAllow;
            }

        }

        /**
         * Your RateLimiter object will be instantiated and called as such:
         * RateLimiter obj = new RateLimiter(n, t);
         * boolean param_1 = obj.shouldAllow(timestamp);
         */

    }
}
