package data_structure_algorithm.leetcode;

public class LCR165crackNumber {

    /**
     相同题：https://leetcode.cn/problems/decode-ways

     动态规划（转换为跳台阶问题）
     分析：单独一位数字肯定可以被解密，两位数字放在一起可能被解密也可能无法解密
     定义状态：dp[i]表示前i个数字的解密结果数量
     状态转移：
     if (i和i-1能被放在一起解密) dp[i] = dp[i-1] + dp[i-2]
     else dp[i] = dp[i-1]
     初始状态：dp[0] = 1;

     时间复杂度：O(N)
     空间复杂度：O(N)，字符串s消耗O(N)，dp数组消耗O(N)
     */
     public int crackNumber1(int ciphertext) {
         String s = String.valueOf(ciphertext);
         int n = s.length();

         // 定义状态：dp[i]表示前i个数字的解密结果数量
         int[] dp = new int[n + 1];

         // 初始状态
         dp[0] = 1;  // 没有数字时，解密方案数量为1（充当哨兵的作用）
         dp[1] = 1;  // 只有一个数字时，解密方案数量为1

         // 状态转移
         for (int i = 2; i <= n; i++) {
             int num = Integer.valueOf(s.substring(i - 2, i));
             if (10 <= num && num <= 25) {
                 dp[i] = dp[i - 1] + dp[i - 2];
             } else {
                 dp[i] = dp[i - 1];
             }
         }
         return dp[n];
     }

    /**
     优化空间复杂度：dp[i]只与dp[i-1]和dp[i-2]有关，可以用临时变量代替数组
     空间复杂度：O(N)，字符串s消耗O(N)
     */
     public int crackNumber2(int ciphertext) {
         String s = String.valueOf(ciphertext);
         int a = 1, b = 1;
         for (int i = 2; i <= s.length(); i++) {
             int num = Integer.valueOf(s.substring(i - 2, i));
             int c = 10 <= num && num <= 25 ? a + b : b;
             a = b;
             b = c;
         }
         return b;
     }

    /**
     继续优化空间复杂度：用取余代替字符串
     空间复杂度：O(1)
     */
    public int crackNumber3(int ciphertext) {
        int a = 1, b = 1, x, y = ciphertext % 10;
        while (ciphertext > 9) {
            ciphertext /= 10;
            x = ciphertext % 10;
            int num = x * 10 + y;
            int c = 10 <= num && num <= 25 ? a + b : b;
            a = b;
            b = c;
            y = x;
        }
        return b;
    }

}
