package data_structure_algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;

public class _202isHappy {

    public static class Solution1 {

        /**
         哈希表：记录遍历过的数，防止陷入无限循环
         时间复杂度：O(logN)
         空间复杂度：O(logN)
         */
         public boolean isHappy(int n) {
             Set<Integer> set = new HashSet<>();
             while (n != 1) {
                 set.add(n);
                 n = squareSum(n);
                 if (set.contains(n)) {
                     return false;
                 }
             }
             return true;
         }

        /**
         计算数字n各个位上数字的平方和
         时间复杂度：O(logN)
         空间复杂度：O(1)
         */
         private int squareSum(int n) {
             int sum = 0;
             while (n != 0) {
                 int mod = n % 10;
                 sum += mod * mod;
                 n = n / 10;
             }
             return sum;
         }

    }



    public static class Solution2 {

        /**
         快慢指针(类似题："141.环形链表；287.寻找重复数")
         时间复杂度：O(logN)
         空间复杂度：O(1)
         */
        public boolean isHappy(int n) {
            int slow = n, fast = n;
            while (fast != 1 && next(fast) != 1) {
                slow = next(slow);
                fast = next(next(fast));
                if (slow == fast) return false;
            }
            return true;
        }

        /**
         计算数字n各个位上数字的平方和(也就是下一个数)
         时间复杂度：O(logN)
         空间复杂度：O(1)
         */
        private int next(int n) {
            int sum = 0;
            while (n != 0) {
                int mod = n % 10;
                sum += mod * mod;
                n = n / 10;
            }
            return sum;
        }

    }


}
