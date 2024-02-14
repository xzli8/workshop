package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _887superEggDrop {

    // ref:https://leetcode.cn/problems/super-egg-drop/solutions/44427/ji-ben-dong-tai-gui-hua-jie-fa-by-labuladong/

    public static class Solution1 {

        /**
         动态规划：带备忘录的递归
         时间复杂度：O(K * N * N)
         空间复杂度：O(K * N)

         超出时间限制，case：k = 6, n = 10000
         */
         public int superEggDrop(int k, int n) {
             memo = new int[k+1][n+1];
             for (int i = 0; i < k+1; i++) {
                 Arrays.fill(memo[i], -1);
             }
             return doDrop(k, n);
         }

         private int[][] memo;

         private int doDrop(int k, int n) {
             // 递归终止条件
             if (k == 1) return n;   // k=1表示只有一枚鸡蛋，只能挨个楼层测试
             if (n == 0) return 0;   // n=0表示楼层为0，不需要测试

             if (memo[k][n] != -1) {
                 return memo[k][n];
             }

             int res = Integer.MAX_VALUE;
             // 遍历楼层，挨个尝试，选择取哪一层丢鸡蛋才能得到最少的尝试次数
             for (int i = 1; i <= n; i++) {
                 // (k-1, i-1)表示从i层丢鸡蛋时，鸡蛋碎了，那么还剩下k-1个鸡蛋和i-1层需要测试
                 // (k, n-i)表示从i层丢鸡蛋时，鸡蛋没碎，那么还剩下k个鸡蛋和n-i层需要测试
                 // 两者取最大值是因为需要考虑最坏情况
                 res = Math.min(res, Math.max(doDrop(k-1, i-1), doDrop(k, n-i)) + 1);
             }
             memo[k][n] = res;
             return res;
         }

    }


    public static class Solution2 {

        /**
         动态规划 + 二分搜索
         时间复杂度：O(K * N * logN)
         空间复杂度：O(K * N)
         */
        public int superEggDrop(int k, int n) {
            memo = new int[k+1][n+1];
            for (int i = 0; i < k+1; i++) {
                Arrays.fill(memo[i], -1);
            }
            return doDrop(k, n);
        }

        private int[][] memo;

        private int doDrop(int k, int n) {
            // 递归终止条件
            if (k == 1) return n;   // k=1表示只有一枚鸡蛋，只能挨个楼层测试
            if (n == 0) return 0;   // n=0表示楼层为0，不需要测试
            if (memo[k][n] != -1) {
                return memo[k][n];
            }

             // 遍历楼层，挨个尝试，选择取哪一层丢鸡蛋才能得到最少的尝试次数
            int res = Integer.MAX_VALUE;
             for (int i = 1; i <= n; i++) {
                 // (k-1, i-1)表示从i层丢鸡蛋时，鸡蛋碎了，那么还剩下k-1个鸡蛋和i-1层需要测试
                 // (k, n-i)表示从i层丢鸡蛋时，鸡蛋没碎，那么还剩下k个鸡蛋和n-i层需要测试
                 // 两者取最大值是因为需要考虑最坏情况
                 res = Math.min(res, Math.max(doDrop(k-1, i-1), doDrop(k, n-i)) + 1);
             }
            memo[k][n] = res;
            return res;
        }

    }



    public static class Solution3 {

        /**
         动态规划 + 二分搜索
         时间复杂度：O(K * N * logN)
         空间复杂度：O(K * N)
         */
        public int superEggDrop(int k, int n) {
            memo = new int[k+1][n+1];
            for (int i = 0; i < k+1; i++) {
                Arrays.fill(memo[i], -1);
            }
            return doDrop(k, n);
        }

        private int[][] memo;

        private int doDrop(int k, int n) {
            // 递归终止条件
            if (k == 1) return n;   // k=1表示只有一枚鸡蛋，只能挨个楼层测试
            if (n == 0) return 0;   // n=0表示楼层为0，不需要测试
            if (memo[k][n] != -1) {
                return memo[k][n];
            }


            // 用二分查找代替线性搜索
            int res = Integer.MAX_VALUE;
            int low = 1, high = n;
            while (low <= high) {
                int mid = low + ((high - low) >> 1);
                int broken = doDrop(k-1, mid - 1);
                int notBroken = doDrop(k, n - mid);
                if (broken > notBroken) {
                    high = mid - 1;
                    res = Math.min(res, broken + 1);
                } else {
                    low = mid + 1;
                    res = Math.min(res, notBroken + 1);
                }
            }
            memo[k][n] = res;
            return res;
        }

    }



}
