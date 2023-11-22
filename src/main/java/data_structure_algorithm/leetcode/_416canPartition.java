package data_structure_algorithm.leetcode;

public class _416canPartition {

    /**
     动态规划（转换为0-1背包问题：从数组中选出部分元素，使得这些元素的和等于数组中所有元素和的一半）
     时间复杂度：O(N * M)
     空间复杂度：O(M)

     Q：如果nums中有负数会怎么样？
     */
    public boolean canPartition(int[] nums) {
        // 数组长度小于2，不可分割，返回false
        int n = nums.length;
        if (n < 2) return false;

        // 计算数组中所有元素和
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // 如果和为奇数，返回false
        if ((sum & 1) == 1) {
            return false;
        }

        // 定义状态：dp[j]表示被选中元素的和是否等于j
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];

        // 初始状态
        dp[0] = true;
        if (nums[0] <= target) {
            dp[nums[0]] = true;
        }

        // 状态转移
        for (int i = 1; i < n; i++) {
            for (int j = target; j >= 0; j--) {
                if (j - nums[i] >= 0 && dp[j - nums[i]]) {
                    dp[j] = true;
                }
            }
        }
        return dp[target];
    }

}
