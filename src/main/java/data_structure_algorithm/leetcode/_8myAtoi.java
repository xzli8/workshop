package data_structure_algorithm.leetcode;

public class _8myAtoi {

    public static class Solution1 {

        /**
         游标法，注意（提前）判断是否溢出，类似“7-整数反转”
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int myAtoi(String s) {
            if (s == null) return 0;

            // 第一步，跳过前导空格
            int n = s.length(), i = 0;
            while (i < n && s.charAt(i) == ' ') {
                i++;
            }
            // 如果全部是空格，返回0
            if (i == n) return 0;

            // 第二步，判断正负号
            boolean negative = false;
            if (s.charAt(i) == '-') {
                negative = true;
            }
            // 如果有正负号，游标i前进一位
            if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                i++;
            }

            // 第三步，逐位处理每一位数字
            int res = 0;
            while (i < n && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                int digit = s.charAt(i) - '0';
                // 这里要提前判断是否溢出
                if (negative) {
                    // 如果是负数，判断是否小于最小的32位整数
                    if (-res < Integer.MIN_VALUE/10 || (-res == Integer.MIN_VALUE/10 && digit >= 8)) {
                        return Integer.MIN_VALUE;
                    }
                } else {
                    // 如果是正数，判断是否大于最大的32位整数
                    if (res > Integer.MAX_VALUE/10 || (res == Integer.MAX_VALUE/10 && digit >= 7)) {
                        return Integer.MAX_VALUE;
                    }
                }
                res = res * 10 + digit;
                i++;
            }

            // 第四步，如果有负号，标记返回负数
            return negative ? -res : res;
        }

    }

}
