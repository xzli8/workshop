package data_structure_algorithm.leetcode;

public class _96numTrees {

    /**
     *  参考题解：https://blog.csdn.net/Cyril_KI/article/details/108412075?spm=1001.2101.3001.6650.3&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-3-108412075-blog-88906534.pc_relevant_multi_platform_whitelistv3&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-3-108412075-blog-88906534.pc_relevant_multi_platform_whitelistv3&utm_relevant_index=6
     */

    public static class Solution1 {

        /**
         动态规划（原问题可以分解为规模较小的子问题，并且子问题的解可以复用）
         令g(n)表示由n个节点对应的BST数量，f(i)表示以节点i为根节点的BST数量，那么：
         g(n) = f(1) + f(2) + ... + f(i) + ... + f(n)
         f(i) = g(i-1) * g(n-i)
         则：g(n) = g(0) * g(n-1) + ... + g(i-1) * g(n-i) + ... + g(n-1) * g(0)
         */
         public int numTrees(int n) {
             int[] dp = new int[n+1];
             dp[0] = 1; // dp[0]本身并没有意义，为了保持相乘结果不变故赋值为1
             dp[1] = 1;
             for (int i = 2; i <= n; i++) {
                 for (int j = 1; j <= i; j++) {
                     dp[i] += dp[j-1] * dp[i-j];
                 }
             }
             return dp[n];
         }

    }



    public static class Solution2 {

        // 以上推导结果为卡兰特数，有解析解
        public int numTrees(int n) {
            long res = 1;   // 这里用long防止计算过程中的溢出
            for (int i = 0; i < n; i++) {
                res = res * 2 * (2 * i + 1) / (i + 2);
            }
            return (int) res;
        }

    }

}
