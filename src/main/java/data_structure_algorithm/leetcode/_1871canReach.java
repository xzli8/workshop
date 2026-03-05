package data_structure_algorithm.leetcode;

public class _1871canReach {

    public static class Solution1 {

        /**
         DP: O(N^2), O(N) -> TLE
         */
        public boolean canReach(String s, int minJump, int maxJump) {
            int n = s.length();
            boolean[] dp = new boolean[n];  // default false
            dp[0] = true;
            for (int i = 1; i < n; i++) {
                if (s.charAt(i) != '0') continue;
                for (int j = 0; j < i; j++) {
                    if (dp[j] && i - maxJump <= j && j <= i - minJump) {
                        dp[i] = true;
                        break;
                    }
                }
            }
            return dp[n - 1];
        }

        // 稍微更高效一点儿的写法
        public boolean canReachII(String s, int minJump, int maxJump) {
            int n = s.length();
            boolean[] dp = new boolean[n];  // default false
            dp[0] = true;
            for (int i = 1; i < n; i++) {
                if (s.charAt(i) != '0') continue;
                for (int j = Math.max(0, i - maxJump); j <= Math.min(i - minJump, n - 1); j++) {
                    if (dp[j]) {
                        dp[i] = true;
                        break;
                    }
                }
            }
            return dp[n - 1];
        }

    }


    public static class Solution2 {

        /**
         DP + PreSum: O(N), O(N)
         Note: 将dp中的true看作1，false看作0，那么dp[i]=true等价于"s[i]=0且preSum[left, right] != 0",
         其中left = i - maxJump, right = i - minJump.
         */
        public boolean canReach(String s, int minJump, int maxJump) {
            int n = s.length();
            boolean[] dp = new boolean[n];  // default false
            dp[0] = true;
            int[] preSum = new int[n];
            for (int i = 0; i < minJump; i++) preSum[i] = 1;
            for (int i = minJump; i < n; i++) {
                int left = i - maxJump, right = i - minJump;
                if (s.charAt(i) == '0') {
                    int total = preSum[right] - (left <= 0 ? 0 : preSum[left - 1]);
                    if (total != 0) dp[i] = true;
                }
                preSum[i] = dp[i] ? preSum[i - 1] + 1 : preSum[i - 1];
            }
            return dp[n - 1];
        }

    }

}
