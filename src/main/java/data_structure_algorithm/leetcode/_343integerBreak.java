package data_structure_algorithm.leetcode;

public class _343integerBreak {

    public static class Solution0 {

        /**
         贪心：每次尽量拆分成尽量多的3
         时间复杂度：O(1)
         空间复杂度：O(1)
         */
        public int integerBreak(int n) {
            if (n <= 3) return n - 1;
            int mod = n % 3, multi = n / 3;
            if (mod == 0) return (int) Math.pow(3, multi);
            else if (mod == 1) return (int) Math.pow(3, multi - 1) * 4;
            else return (int) Math.pow(3, multi) * 2;
        }

    }



    public static class Solution1 {

        /**
         递归穷举
         时间复杂度：指数级别(超时)
         空间复杂度：O(N)，递归栈深度
         */
        public int integerBreak(int n) {
            if (n == 2) return 1;
            if (n == 3) return 2;
            int res = 0;
            for (int i = 2; i <= n-2; i++) {
                // 拆成两个数：i和n-i，或者继续拆分：i * ...
                res = Math.max(res, Math.max(i * (n - i), i * integerBreak(n - i)));
            }
            return res;
        }

    }



    public static class Solution2 {

        /**
         记忆化递归
         时间复杂度：O(N^2)
         空间复杂度：O(N)
         */
        public int integerBreak1(int n) {
            memo = new int[n+1];
            return integerBreakHelper(n);
        }

        int[] memo;
        int integerBreakHelper(int n) {
            if (n == 2) return 1;
            if (memo[n] != 0) return memo[n];

            int res = 0;
            for (int i = 1; i < n; i++) {
                // 不拆分：i * (n - i)；拆分：i * f(n - i)
                res = Math.max(res, Math.max(i * (n - i), i * integerBreakHelper(n - i)));
            }
            memo[n] = res;
            return res;
        }

    }



    public static class Solution3 {


        /**
         动态规划
         定义状态：dp[i]表示正整数i拆分为k个正整数后的最大乘积
         状态转移：dp[i] = max(j * (i - j), j * dp[i - j]). j = 1, ..., i-1
         拆分成j和(i-j) or (i-j)继续拆分
         初始状态：dp[1] = 0

         时间复杂度：O(N^2)
         空间复杂度：O(N)
         */
        public int integerBreak2(int n) {
            int[] dp = new int[n+1];
            for (int i = 2; i <= n; i++) {
                int max = 0;
                for (int j = 1; j < i; j++) {
                    max = Math.max(max, Math.max(j * (i - j), j * dp[i - j]));
                }
                dp[i] = max;
            }
            return dp[n];
        }

    }



    public static class Solution4 {

        /**
         动态规划
         定义状态：dp[i]表示数字i拆分后的最大乘积
         状态转移：dp[i] = max(dp[i-j] * dp[j]), 1 <= j <= i/2
         将i拆分成j和i-j两个数，取乘积的最大值
         初始状态：dp[2] = 1, dp[3] = 2

         时间复杂度：O(N^2)
         空间复杂度：O(N)
         */
        public int integerBreak3(int n) {
            if (n == 2) return 1;
            if (n == 3) return 2;

            int[] dp = new int[n + 1];
            dp[1] = 1;
            dp[2] = 2;
            dp[3] = 3;
            for (int i = 4; i <= n; i++) {
                int max = 0;
                for (int j = 1; j <= i / 2; j++) {
                    max = Math.max(max, dp[j] * dp[i - j]);
                }
                dp[i] = max;
            }
            return dp[n];
        }

    }



    public static class Solution5 {

        /**
         数学：拆成尽可能多的3，余数为1时用4，余数为2时用2
         数学分析：
         (1)当n <= 3时，肯定是不拆分比拆分的乘积大
         n = 2: 2 = 1 + 1, 1 * 1 = 1 < 2
         n = 3: 3 = 1 + 2, 1 * 2 = 2 < 3
         (2)当n >= 5时，肯定是拆分比不拆分的乘积大
         拆分成2和n-2：n = 2 + (n - 2), 2 * (n - 2) > n
         拆分成3和n-3: n = 3 + (n - 3), 3 * (n - 3) > n
         并且：3(n - 3) >= 2(n - 2)
         当n >= 5时，应该拆成更多的3
         (3)当n = 4时，拆成2个2乘积最大

         时间复杂度：O(1)
         空间复杂度：O(1)
         */
        public int integerBreak(int n) {
            if (n <= 3) {
                return n - 1;
            }

            int count = n / 3, mod = n % 3;
            if (mod == 0) {
                return (int) Math.pow(3, count);
            } else if (mod == 1) {
                return (int) Math.pow(3, count - 1) * 4;
            } else {
                return (int) Math.pow(3, count) * 2;
            }
        }

    }

}
