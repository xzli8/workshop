package data_structure_algorithm.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class LCR_185statisticsProbability {

    public static class Solution1 {

        @Test
        public void test() {
            System.out.println(statisticsProbability(3));
        }

        /**
         暴力枚举DFS：总共有num个色子，每次掷一个，每次的结果有6种可能
         时间复杂度：O(6^N)
         空间复杂度：O(N)
         */
        public double[] statisticsProbability(int num) {
            dfs(num, 0);
            int total = sum2Count.size(), sum = sum2Count.values().stream().mapToInt(Integer::valueOf).sum();
            double[] res = new double[total];
            for (int i = 0; i < total; i++) {
                res[i] =  sum2Count.get(i + num) / (double) sum;
            }
            return res;
        }

        private Map<Integer, Integer> sum2Count = new HashMap<>();
        private void dfs(int num, int sum) {
            if (num == 0) {
                sum2Count.put(sum, sum2Count.getOrDefault(sum, 0) + 1);
                return;
            }
            for (int i = 1; i <= 6; i++) {
                dfs(num - 1, sum + i);
            }
        }

    }



    public static class Solution2 {

        @Test
        public void test() {
            System.out.println(statisticsProbability(3));
        }

        /**
         动态规划
         思路：依次掷骰子，每次将当前点数和之前点数出现的次数累加
         定义状态：dp[i][j]表示第i个色子掷完后，点数j出现的次数
         状态转移：dp[i][j + k] += dp[i - 1][j] (k = 1, 2, 3, 4, 5, 6)
         初始状态：dp[0][k] = 1 (k = 1, 2, 3, 4, 5, 6)

         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public double[] statisticsProbability(int num) {
            // 定义状态
            int max = 6 * num;
            int[][] dp = new int[num][max + 1];

            // 初始状态
            for (int k = 1; k <= 6; k++) dp[0][k] = 1;

            // 状态转移
            for (int i = 1; i < num; i++) {
                // 第i个色子掷完后，点数范围是[i, 6i]
                for (int j = i; j <= 6 * i; j++) {
                    for (int k = 1; k <= 6; k++) {
                        dp[i][j + k] += dp[i - 1][j];
                    }
                }
            }

            // 计算结果
            int total = max - num + 1, sum = 0;
            for (int j = num; j <= max; j++) sum += dp[num - 1][j];
            double[] res = new double[total];
            for (int i = 0; i < total; i++) res[i] = dp[num - 1][i + num] / (double) sum;
            return res;
        }

    }



    public static class Solution3 {

        @Test
        public void test() {
            System.out.println(statisticsProbability(3));
        }

        /**
         动态规划：因为dp[i]只与dp[i-1]有关，可以优化空间复杂度为O(1)
         */
        public double[] statisticsProbability(int num) {
            // 定义状态
            int max = 6 * num;
            int[] dp = new int[max + 1], tmp = new int[max + 1];

            // 初始状态
            for (int k = 1; k <= 6; k++) dp[k] = 1;

            // 状态转移
            for (int i = 1; i < num; i++) {
                for (int j = i; j <= 6 * i; j++) tmp[j] = dp[j];
                Arrays.fill(dp, 0);
                for (int j = i; j <= 6 * i; j++) {
                    for (int k = 1; k <= 6; k++) {
                        dp[j + k] += tmp[j];
                    }
                }
            }

            // 计算结果
            int sum = 0;
            for (int j = num; j <= 6 * num; j++) sum += dp[j];
            int total = 6 * num - num + 1;
            double[] res = new double[total];
            for (int i = 0; i < total; i++) res[i] = dp[i + num] / (double) sum;
            return res;
        }

    }

}
