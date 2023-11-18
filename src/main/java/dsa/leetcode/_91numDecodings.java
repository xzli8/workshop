package dsa.leetcode;

public class _91numDecodings {


    /**
     相同题：https://leetcode.cn/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/description/

     动态规划
     定义状态：dp[i]表示前i个字符s[1...i]的解码方法数
     状态转移：dp[i]可能由dp[i-1]或者dp[i-2]转换而来
     if s[i]属于[1, 9]，dp[i] = dp[i-1]
     if s[i-1]不等于0，并且s[i-1]s[i]属于[1,26]，dp[i] = dp[i-2]
     时间复杂度：O(n)
     空间复杂度：O(n)
     */
    public int numDecodings(String s) {
        // 定义并初始化状态
        int n = s.length();
        int[] dp = new int[n+1];
        dp[0] = 1;  // 空字符可以有一种解码方法

        // 状态转移
        for (int i = 1; i <= n; i++) {
            // 第i个字符在s中的下标是i-1
            if (s.charAt(i-1) != '0') {
                dp[i] += dp[i-1];
            }
            if (i > 1 && s.charAt(i-2) != '0' && ((s.charAt(i-2) - '0') * 10 + (s.charAt(i-1) - '0') <= 26)) {
                dp[i] += dp[i-2];
            }
        }
        return dp[n];
    }

}
