package data_structure_algorithm.leetcode;

public class _1049lastStoneWeightII {

    public static class Solution1 {

        /**
         动态规划：将石头分成总重量最接近两堆，转换为01背包问题，与“416-分割等和子集”类似
         一堆总重量为x，另一堆总重量就是sum - x，两堆的差值就是sum - 2x
         （因为x的最大值是向下取整，所以sum - x肯定大于x）

         NOTE：与416的差别，416要求背包装满，本题不要求背包装满

         时间复杂度：O(N * M)
         空间复杂度：O(M)
         */
        public int lastStoneWeightII(int[] stones) {
            // 计算总重量
            int n = stones.length;
            int sum = 0;
            for (int stone : stones) {
                sum += stone;
            }

            // 定义状态
            int capacity = sum >> 1;
            boolean[] dp = new boolean[capacity + 1];

            // 初始状态
            dp[0] = true;
            if (stones[0] <= capacity) {
                dp[stones[0]] = true;
            }

            // 状态转移
            for (int i = 1; i < n; i++) {
                for (int j = capacity; j >= 0; j--) {
                    if (j - stones[i] >= 0 && dp[j - stones[i]]) {
                        dp[j] = true;
                    }
                }
            }

            // 找最大值
            int max = 0;
            for (int j = capacity; j >= 0; j--) {
                if (dp[j]) {
                    max = j;
                    break;
                }
            }

            // 计算差值（也就是最小重量）
            return sum - 2 * max;
        }

    }


    public static class Solution2 {

        /**
         动态规划：01背包问题，不要求背包必须装满(另一种写法)
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int lastStoneWeightII(int[] stones) {
            // 前置处理
            int n = stones.length, sum = 0;
            for (int stone : stones) sum += stone;

            // 定义状态
            int target = sum >>> 1;
            boolean[] dp = new boolean[target + 1];

            // 初始状态
            dp[0] = true;

            // 状态转移
            for (int i = 0; i < n; i++) {
                for (int j = target; j >= 0; j--) {
                    if (j - stones[i] >= 0 && dp[j - stones[i]]) {
                        dp[j] = true;
                    }
                }
            }

            // 找最大重量
            int x = target;
            while (x >= 0) {
                if (dp[x]) break;
                x--;
            }

            // 两堆石头，较重的一堆为sum - x，较轻的一堆为x，差为sum - x - x = sum - 2x
            return sum - 2 * x;
        }

    }

}
