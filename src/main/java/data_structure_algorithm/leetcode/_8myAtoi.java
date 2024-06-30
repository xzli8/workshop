package data_structure_algorithm.leetcode;

public class _8myAtoi {

    public static class Solution0 {

         /**
         模拟 + 遍历
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int myAtoi(String s) {
            // 去除前导空格
            int n = s.length(), i = 0;
            while (i < n && s.charAt(i) == ' ') i++;
            if (i == n) return 0;

            // 处理符号位
            boolean negative = false;
            if (s.charAt(i) == '+' || s.charAt(i) == '-') negative = s.charAt(i++) == '-';

            // 处理剩下的字符
            int res = 0;
            while (i < n && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                int num = s.charAt(i++) - '0';
                if (!negative && (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && num >= 7))) return Integer.MAX_VALUE;
                if (negative && (-res < Integer.MIN_VALUE / 10 || (-res == Integer.MIN_VALUE / 10 && num >= 8))) return Integer.MIN_VALUE;
                res = res * 10 + num;
            }
            return negative ? -res : res;
        }

    }

    public static class Solution1 {

        /**
         游标法，注意（提前）判断是否溢出，类似“7-整数反转”
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int myAtoi(String s) {
            // 跳过前导空格
            int n = s.length(), i = 0;
            while (i < n && s.charAt(i) == ' ') {
                i++;
            }
            if (i == n) return 0;

            // 处理符号位
            boolean negative = false;
            if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                negative = s.charAt(i) == '-';
                i++;
            }

            // 处理剩余的数字
            int res = 0;
            while (i < n && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                int digit = s.charAt(i++) - '0';
                // 这里要提前判断是否溢出
                if (negative) {
                    // 如果是负数，判断是否小于最小的32位整数
                    if (-res < Integer.MIN_VALUE / 10 || (-res == Integer.MIN_VALUE / 10 && digit >= 8)) {
                        return Integer.MIN_VALUE;
                    }
                } else {
                    // 如果是正数，判断是否大于最大的32位整数
                    if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && digit >= 7)) {
                        return Integer.MAX_VALUE;
                    }
                }
                res = res * 10 + digit;
            }
            return negative ? -res : res;
        }

    }

}
