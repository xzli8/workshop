package data_structure_algorithm.leetcode;

import org.junit.Test;

public class _306isAdditiveNumber {

    public static class Solution1 {

        /**
         回溯(组合)：由于过大整数会导致Integer.valueOf()产生溢出，所以无法通过所有用例，但思路正确
         */
        public boolean isAdditiveNumber(String num) {
            return backtrace(num, 0, 0, 0, 0);
        }

        private boolean backtrace(String num, int startIndex, int count, int pre1, int pre2) {
            if (startIndex == num.length())
                return count > 2;
            for (int i = startIndex; i < num.length(); i++) {
                if (isValid(num, startIndex, i, count, pre1, pre2)) {
                    System.out.println(startIndex + ", " + i + num.substring(startIndex, i + 1));
                    if (backtrace(num, i + 1, count + 1, pre2, Integer.valueOf(num.substring(startIndex, i + 1)))) return true;
                }
            }
            return false;
        }

        private boolean isValid(String num, int start, int end, int count, int pre1, int pre2) {
            // 除0外，以0开头的数字不合法
            if (num.charAt(start) == '0' && start != end) return false;

            // 前两个数不需要考虑序列是否合法，只要数字合法即可
            if (count < 2) return true;

            // 后面的数需要考虑序列是否合法
            return Integer.valueOf(num.substring(start, end + 1)) == pre1 + pre2;
        }

    }



    public static class Solution2 {

        /**
         回溯(组合)：用字符串加法处理由于过大整数可能导致的溢出
         */
        public boolean isAdditiveNumber(String num) {
            return backtrace(num, 0, 0, "", "");
        }

        private boolean backtrace(String num, int startIndex, int count, String pre1, String pre2) {
            if (startIndex == num.length()) return count > 2;
            for (int i = startIndex; i < num.length(); i++) {
                if (isValid(num, startIndex, i, count, pre1, pre2)) {
                    if (backtrace(num, i + 1, count + 1, pre2, num.substring(startIndex, i + 1))) return true;
                }
            }
            return false;
        }

        private boolean isValid(String num, int start, int end, int count, String pre1, String pre2) {
            // 除0外，以0开头的数字不合法
            if (num.charAt(start) == '0' && start != end) return false;

            // 前两个数不需要考虑序列是否合法，只要数字合法即可
            if (count < 2) return true;

            // 后面的数需要考虑序列是否合法
            return num.substring(start, end + 1).equals(addStrings(pre1, pre2));
        }

        // 字符串相加(415.字符串加法)：模拟竖式加法
        public String addStrings(String num1, String num2) {
            int i = num1.length() - 1, j = num2.length() - 1, add = 0;
            StringBuffer ans = new StringBuffer();
            while (i >= 0 || j >= 0 || add != 0) {
                int x = i >= 0 ? num1.charAt(i) - '0' : 0;
                int y = j >= 0 ? num2.charAt(j) - '0' : 0;
                int result = x + y + add;
                ans.append(result % 10);
                add = result / 10;
                i--;
                j--;
            }
            ans.reverse();
            return ans.toString();
        }

    }

}
