package data_structure_algorithm.leetcode;

public class KamaCoder_56maxValue {

    // ref:https://kamacoder.com/problempage.php?pid=1066

    /*
    import java.util.Scanner;
    import java.util.Arrays;

    // 注意类名必须为 Main, 不要有任何 package xxx 信息
    public class Main {

        public static void main(String[] args) {
            // 处理输入
            Scanner in = new Scanner(System.in);
            int capacity = in.nextInt(), n = in.nextInt();
            int[] weights = new int[n], values = new int[n], nums = new int[n];
            for (int i = 0; i < n; i++) weights[i] = in.nextInt();
            for (int i = 0; i < n; i++) values[i] = in.nextInt();
            for (int i = 0; i < n; i++) nums[i] = in.nextInt();

            // 输出
            // System.out.println(maxValue1(capacity, weights, values, nums));
            // System.out.println(maxValue2(capacity, weights, values, nums));
            System.out.println(maxValue3(capacity, weights, values, nums));
        }


        // 二维数组
        private static int maxValue1(int capacity, int[] weights, int[] values, int[] nums) {
            // 定义状态
            int n = weights.length;
            int[][] dp = new int[n][capacity + 1];

            // 初始状态
            Arrays.fill(dp[0], -1);
            dp[0][0] = 0;
            for (int k = 0; k <= nums[0]; k++) {
                if (k * weights[0] <= capacity) {
                    dp[0][k * weights[0]] = k * values[0];
                }
            }

            // 状态转移
            for (int i = 1; i < n; i++) {
                for (int j = 0; j <= capacity; j++) {
                    dp[i][j] = dp[i - 1][j];
                    for (int k = 0; k <= nums[i]; k++) {
                        if (j - k * weights[i] >= 0 && dp[i - 1][j - k * weights[i]] >= 0) {
                            dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k * weights[i]] + k * values[i]);
                        }
                    }
                }
            }

            // 返回最大值
            int maxValue = -1;
            for (int j = 0; j <= capacity; j++) {
                maxValue = Math.max(maxValue, dp[n - 1][j]);
            }
            return maxValue;
        }


        // 一维数组
        private static int maxValue2(int capacity, int[] weights, int[] values, int[] nums) {
            // 定义状态
            int n = weights.length;
            int[] dp = new int[capacity + 1];

            // 初始状态
            Arrays.fill(dp, -1);
            dp[0] = 0;
            for (int k = 0; k <= nums[0]; k++) {
                if (k * weights[0] <= capacity) {
                    dp[k * weights[0]] = k * values[0];
                }
            }

            // 状态转移
            for (int i = 1; i < n; i++) {
                for (int j = capacity; j >= 0; j--) {
                    for (int k = 0; k <= nums[i]; k++) {
                        if (j - k * weights[i] >= 0 && dp[j - k * weights[i]] >= 0) {
                            dp[j] = Math.max(dp[j], dp[j - k * weights[i]] + k * values[i]);
                        }
                    }
                }
            }

            // 返回最大值
            int maxValue = -1;
            for (int j = 0; j <= capacity; j++) {
                maxValue = Math.max(maxValue, dp[j]);
            }
            return maxValue;
        }


        // 一维数组
        private static int maxValue3(int capacity, int[] weights, int[] values, int[] nums) {
            // 定义状态
            int n = weights.length;
            int[] dp = new int[capacity + 1];

            // 初始状态
            Arrays.fill(dp, -1);
            dp[0] = 0;

            // 状态转移
            for (int i = 0; i < n; i++) {
                for (int j = capacity; j >= 0; j--) {
                    for (int k = 0; k <= nums[i]; k++) {
                        if (j - k * weights[i] >= 0 && dp[j - k * weights[i]] >= 0) {
                            dp[j] = Math.max(dp[j], dp[j - k * weights[i]] + k * values[i]);
                        }
                    }
                }
            }

            // 返回最大值
            int maxValue = -1;
            for (int j = 0; j <= capacity; j++) {
                maxValue = Math.max(maxValue, dp[j]);
            }
            return maxValue;
        }

    }
     */

}
