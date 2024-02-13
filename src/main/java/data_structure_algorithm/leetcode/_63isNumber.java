package data_structure_algorithm.leetcode;

public class _63isNumber {

    public static class Solution1 {

        /**
         模拟(分场景判断)
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public boolean isNumber(String s) {
            // 根据e/E将s分为两部分
            int n = s.length(), i = 0;
            while (i < n) {
                char c = s.charAt(i);
                if (c == 'e' || c == 'E') break;
                i++;
            }

            // 后半部分是整数
            if (i + 1 <= n && !validInteger(s.substring(i + 1, n))) return false;

            // 前半部分是整数或小数
            String first = s.substring(0, i);
            return validInteger(first) || vaildDecimal(first);
        }

        // 判断是否是有效的小数
        private boolean vaildDecimal(String s) {
            // 小数至少有两位
            int n = s.length();
            if (n < 2) return false;

            // 跳过符号位
            int i = 0;
            if (s.charAt(i) == '+' || s.charAt(i) == '-') i++;

            // 除了符号位，至少有一个数字和一个点
            if (i == n - 1)  return false;

            // 判断剩下的是否包含一个点，并且全是数字
            int dotNum = 0;
            while (i < n) {
                char c = s.charAt(i);
                if (c == '.') {
                    if (dotNum == 1) return false;
                    dotNum++;
                } else {
                    if (c < '0' || c > '9') return false;
                }
                i++;
            }
            return true;
        }

        // 判断是否是有效的整数
        private boolean validInteger(String s) {
            // 整数至少有一位
            int n = s.length();
            if (n < 1) return false;

            // 跳过符号位
            int i = 0;
            if (s.charAt(i) == '+' || s.charAt(i) == '-') i++;

            // 除了符号位，至少还有一位数字
            if (i == n) return false;

            // 判断剩下的每一位是否全都是数字
            while (i < n) {
                char c = s.charAt(i);
                if (c < '0' || c > '9') return false;
                i++;
            }
            return true;
        }

    }

}
