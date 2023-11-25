package data_structure_algorithm.leetcode;

public class _474findMaxForm {

    /**
     动态规划：转换为二维01背包(类似："322.零钱兑换")
     思路：有两个维度的背包，分别是0的个数和1的个数
     定义状态：dp[i][j]表示最多有i个0和j个1时的最大子集的长度
     状态转移：dp[i][j] = max(dp[i][j], dp[i - zeroNum][j - oneNum] + 1)
     初始状态：dp[i][j] = 0

     时间复杂度：O(K * M * N)，K为字符串长度
     空间复杂度：O(M * N)
     */
    public int findMaxForm(String[] strs, int m, int n) {
        // 定义状态
        int[][] dp = new int[m + 1][n + 1];

        // 初始状态
        // 省略，数组元素默认初始化为0

        // 状态转移
        // 求最值，内外循环的遍历顺序无所谓，这里先遍历物品，求出0和1的个数后，再遍历背包
        for (String str : strs) {
            int zeroNum = 0, oneNum = 0;
            for (char c : str.toCharArray()) {
                if (c == '0') zeroNum++;
                else oneNum++;
            }

            // 01背包，使用一维数组时，需要从大到小遍历背包重量
            for (int i = m; i >= zeroNum; i--) {
                for (int j = n; j >= oneNum; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeroNum][j - oneNum] + 1);
                }
            }
        }
        return dp[m][n];
    }

}
