package data_structure_algorithm.leetcode;

public class LCR_192myAtoi {

    public static class Solution1 {

        /**
         模拟
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int myAtoi(String str) {
            // 丢弃无用的前导空格
            int n = str.length(), i = 0;
            while (i < n && str.charAt(i) == ' ') {
                i++;
            }
            if (i == n) return 0;

            // 处理符号位
            boolean negative = false;
            if (str.charAt(i) == '+' || str.charAt(i) == '-') {
                negative = str.charAt(i) == '-';
                i++;
            }

            // 处理剩余的数字部分
            int res = 0;
            while (i < n && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                int num = str.charAt(i++) - '0';
                if (negative) {
                    if (-res < Integer.MIN_VALUE / 10 || (-res == Integer.MIN_VALUE / 10 && num >= 8)) {
                        return Integer.MIN_VALUE;
                    }
                } else {
                    if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && num >= 7)) {
                        return Integer.MAX_VALUE;
                    }
                }
                res = res * 10 + num;
            }
            return negative ? -res : res;
        }

    }

}
