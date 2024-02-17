package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _509fib {

    public static class Solution0 {

        /**
         递推
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int fib(int n) {
            if (n == 0 || n == 1) return n;
            int n1 = 0, n2 = 1;
            for (int i = 2; i <= n; i++) {
                int n3 = n1 + n2;
                n1 = n2;
                n2 = n3;
            }
            return n2;
        }

    }



    public static class Solution1 {

        /**
         带备忘录的递归：从上往下解
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int fib(int n) {
            memo = new int[n + 1];
            Arrays.fill(memo, -1);
            return recursive(n);
        }

        private int[] memo;

        private int recursive(int n) {
            if (n == 0 || n == 1) return n;
            if (memo[n] != -1) return memo[n];
            int res = recursive(n-1) + recursive(n-2);
            memo[n] = res;
            return res;
        }

    }



    public static class Solution2 {

        /**
         动态规划：从下往上解
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int fib(int n) {
            if (n == 0) return n;

            int[] dp = new int[n+1];
            dp[0] = 0;
            dp[1] = 1;
            for (int i = 2; i <= n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            return dp[n];
        }

    }



    public static class Solution3 {

        /**
         动态规划：用变量替代数组，压缩空间
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int fib(int n) {
            if (n == 0 || n == 1) return n;

            int f0 = 0, f1 = 1, f2 = 0;
            for (int i = 2; i <= n; i++) {
                f2 = f0 + f1;
                f0 = f1;
                f1 = f2;
            }
            return f2;
        }

    }

}
