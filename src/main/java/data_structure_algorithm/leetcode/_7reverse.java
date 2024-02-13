package data_structure_algorithm.leetcode;

public class _7reverse {

    public static class Solution1 {

        /**
         取末尾数字拼接，注意（提前）判断溢出【用字符串或者栈也可以，但不好，时间复杂度相同的情况下，空间复杂度为O(N)】
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int reverse(int x) {
            int res = 0;
            while (x != 0) {
                // 每次取末尾数字
                int tmp = x % 10;
                // 判断是否大于最大的32位整数
                if (res > Integer.MAX_VALUE/10 || (res == Integer.MAX_VALUE/10 && tmp > 7)) return 0;
                // 判断是否小于最小的32位整数
                if (res < Integer.MIN_VALUE/10 || (res == Integer.MIN_VALUE/10 && tmp < -8)) return 0;
                res = res * 10 + tmp;
                x /= 10;
            }
            return res;
        }

    }

}
