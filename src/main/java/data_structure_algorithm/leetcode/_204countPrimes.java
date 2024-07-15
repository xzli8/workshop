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
         厄拉多塞筛法
         时间复杂度：O(NloglogN)
         空间复杂度：O(N)
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
                // 将当前数的所有倍数都标记为合数
                for (int j = i + i; j < n; j += i) {
                    isPrimes[j] = false;
                }
            }
            return count;
        }

    }



    public static class Solution4 {


        /**
         素数筛(用空间换时间):进一步优化
         时间复杂度：O(NloglogN)
         空间复杂度：O(N)
         */

    }

}
