package data_structure_algorithm.leetcode;

public class _377combinationSum4 {

    /**
     动态规划：转换为完全背包求排列数问题
     思路：元素可以重复使用 -> 完全背包；顺序不同的序列被视为不同的组合 -> 排列数

     定义状态：dp[i]表示和为i的排列数
     状态转移：dp[i] += dp[i - num], num in nums
     初始状态：dp[0] = 1，总和为0的排列数有1种

     时间复杂度：O(N * M)
     空间复杂度：O(M)

     进阶：如果给定的数组中含有负数会发生什么？问题会产生何种变化？如果允许负数出现，需要向题目中添加哪些限制条件
     (如果给定的数组中含有负数，则会导致出现无限长度的排列。例如，假设数组nums中含有正整数a和负整数−b（其中 a>0,b>0,−b<0），
     则有 a×b+(−b)×a=0，对于任意一个元素之和等于target的排列，在该排列的后面添加b个a和a个−b之后，得到的新排列的元素之和仍然等于target，
     而且还可以在新排列的后面继续b个a和a个−b。因此只要存在元素之和等于target的排列，就能构造出无限长度的排列。
     如果允许负数出现，则必须限制排列的最大长度，避免出现无限长度的排列，才能计算排列数。)
     */
    public int combinationSum4(int[] nums, int target) {
        // 定义状态
        int[] dp = new int[target + 1];

        // 初始状态
        dp[0] = 1;

        // 状态转移
        // 求排列数：先遍历背包，再遍历物品
        for (int i = 0; i <= target; i++) {
            //完全背包：内循环从小到大遍历
            for (int num : nums) {
                if (i - num >= 0) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }

}
