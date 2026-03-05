package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _204countPrimes {


    public static class Solution1 {

        /**
         模拟：根据质数定义，除了1和本身没有其他的因子(TLE)
         时间复杂度：O(N^2)
         空间复杂度：O(1)
         */
         public int countPrimes(int n) {
             int count = 0;
             for (int i = 2; i < n; i++) {
                 if (isPrime(i)) count++;
             }
             return count;
         }

         private boolean isPrime(int n) {
             for (int i = 2; i < n; i++) {
                 if (n % i == 0) return false;
             }
             return true;
         }

    }


    public static class Solution2 {

        /**
         模拟：根据质数定义，除了1和本身没有其他的因子(TLE)
         时间复杂度：O(N * sqrt(N))
         空间复杂度：O(1)
         */
         public int countPrimes(int n) {
             int count = 0;
             for (int i = 2; i < n; i++) {
                 if (isPrime(i)) count++;
             }
             return count;
         }

         private boolean isPrime(int n) {
             int sqrt = (int) Math.sqrt(n);
             for (int i = 2; i <= sqrt; i++) {
                 if (n % i == 0) return false;
             }
             return true;
         }

    }


    public static class Solution3 {

        /**
         厄拉多塞筛法(埃式筛): O(NloglogN), O(N)
         */
        public int countPrimes(int n) {
            // 将所有的数都初始化为质数
            boolean[] isPrimes = new boolean[n];
            Arrays.fill(isPrimes, true);

            int count = 0;
            for (int i = 2; i < n; i++) {
                if (isPrimes[i]) {
                    count++;
                }
                // 将当前数的所有倍数都标记为合数(可以继续优化：从 2x 开始标记其实是冗余的，应该直接从 x⋅x 开始标记，因为 2x,3x,… 这些数一定在 x 之前就被其他数的倍数标记过了，例如 2 的所有倍数，3 的所有倍数等。)
                for (int j = i + i; j < n; j += i) {
                    isPrimes[j] = false;
                }
            }
            return count;
        }


        /**
         埃式筛(优化版):O(NloglogN), O(N)
         Ref: https://leetcode.cn/problems/count-primes/solutions/507445/kuai-lai-miao-dong-shai-zhi-shu-by-sweetiee/
         */
        public int countPrimesII(int n) {
            // 初始化长度 O(n) 的标记数组，表示这个数组是否为质数。数组初始化所有的数都是质数.
            boolean[] isPrim = new boolean[n];
            Arrays.fill(isPrim, true);

            // 从 2 开始枚举到 sqrt(n)，将当前数字的倍数全都标记为合数。
            for (int i = 2; i * i < n; i++) {
                // 如果当前是素数
                if (isPrim[i]) {
                    // 就把从 i*i 开始，i 的所有倍数都设置为 false。(每次找当前素数 x 的倍数时，是从 x^2 开始的。因为如果 x>2，那么 2 * x 肯定被素数 2 给过滤了，最小未被过滤的肯定是 x ^2 )
                    for (int j = i * i; j < n; j+=i) {
                        isPrim[j] = false;
                    }
                }
            }

            // 计数
            int cnt = 0;
            for (int i = 2; i < n; i++) {
                if (isPrim[i]) {
                    cnt++;
                }
            }
            return cnt;
        }

    }


    public static class Solution4 {

        /**
         * 线性筛
         */

    }

}
