package dsa.leetcode;

public class _343integerBreak {

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


    /**
     数学：拆成尽可能多的3，余数为1时用4，余数为2时用2
     参考题解：https://leetcode.cn/problems/integer-break/solutions/29098/343-zheng-shu-chai-fen-tan-xin-by-jyd/
     时间复杂度：O(1)
     空间复杂度：O(1)
     */
    public int integerBreak4(int n) {
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
