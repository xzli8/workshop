package data_structure_algorithm.leetcode;

public class _233countDigitOne {

    public static class Solution1 {

        /**
         数学：找规律，计算每一位上1出现的次数
         参考题解：https://leetcode.cn/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof/solutions/229751/mian-shi-ti-43-1n-zheng-shu-zhong-1-chu-xian-de-2/

         时间复杂度：O(logN)
         空间复杂度：O(1)
         */
        public int countDigitOne(int n) {
            int tmp = n, count = 0, digit = 1;
            while (tmp > 0) {
                int cur = n / digit % 10, low = n % digit, high = n / digit / 10;
                if (cur == 0) {
                    count += high * digit;
                } else if (cur == 1) {
                    count += high * digit + low + 1;
                } else {
                    count += (high + 1) * digit;
                }
                tmp /= 10;
                digit *= 10;
            }
            return count;
        }

    }

}
