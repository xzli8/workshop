package data_structure_algorithm.leetcode;

import java.util.HashMap;

public class _70climbStairs {

    public static class Solution0 {

        /**
         动态规划:f(n) = f(n - 1) + f(n - 2)，转换为斐波那契数列
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int climbStairs(int n) {
            if (n == 1 || n == 2) return n;
            int f1 = 1, f2 = 2;
            for (int i = 3; i <= n; i++) {
                int f3 = f1 + f2;
                f1 = f2;
                f2 = f3;
            }
            return f2;
        }

    }



    public static class Solution1 {

        /**
         思路：到达i阶有两种方式，从i-1 or i-2
         可以从上到下求解（递归）
         也可以从下到上求解（递推 or 动态规划）
         */
        // 递归(时间复杂度太高，超出时间限制...)
        public int climbStairs(int n) {
            if (n == 1) return 1;
            if (n == 2) return 2;
            return climbStairs(n-1) + climbStairs(n-2);
        }

    }



    public static class Solution2 {

        /**
         递归 + 备忘录
         时间复杂度：O(n)
         空间复杂度：O(n)
         */
        public int climbStairs(int n) {
            map.put(1, 1);
            map.put(2, 2);
            return climbStairsCore(n);
        }

        private HashMap<Integer, Integer> map = new HashMap<>();
        private int climbStairsCore(int n) {
            if (map.containsKey(n)) {
                return map.get(n);
            }
            int count = climbStairsCore(n-1) + climbStairsCore(n-2);
            map.put(n, count);
            return count;
        }

    }



    public static class Solution3 {

        /**
         递归 + 备忘录(另一种写法)
         时间复杂度：O(n)
         空间复杂度：O(n)
         */
        public int climbStairs(int n) {
            return climbStairsCore(n);
        }

        private HashMap<Integer, Integer> map = new HashMap<>();
        private int climbStairsCore(int n) {
            if (n == 1 || n == 2) return n;
            if (map.containsKey(n)) return map.get(n);
            int count = climbStairsCore(n-1) + climbStairsCore(n-2);
            map.put(n, count);
            return count;
        }

    }



    public static class Solution4 {

        /**
         动态规划
         定义状态：dp[i]表示到达i阶的方法数
         状态转移方程：dp[i] = dp[i-1] + dp[i-2]
         时间复杂度：O(n)
         空间复杂度：O(n)
         */
        public int climbStairs(int n) {
            if (n == 1) return 1;
            if (n == 2) return 2;

            // 定义并初始化状态数组
            int[] dp = new int[n+1];
            dp[1] = 1;
            dp[2] = 2;
            for (int i = 3; i <= n; i++) {
                dp[i] = dp[i-1] + dp[i-2];
            }
            return dp[n];
        }

    }



    public static class Solution5 {

        /**
         递推(动态规划的空间优化方案)
         时间复杂度：O(n)
         空间复杂度：O(1)
         */
        public int climbStairs3(int n) {
            if (n == 1) return 1;
            if (n == 2) return 2;

            int n1 = 1;
            int n2 = 2;
            int sum = 0;
            for (int i = 3; i <= n; i++) {
                sum = n1 + n2;
                n1 = n2;
                n2 = sum;
            }
            return sum;
        }

    }

}
