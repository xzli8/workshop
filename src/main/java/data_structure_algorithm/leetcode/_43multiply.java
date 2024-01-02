package data_structure_algorithm.leetcode;

public class _43multiply {

    public static class Solution1 {

        /**
         1.模拟：将num2的每一位与num1相乘，然后再将每一位相乘的结果相加
         */
        public String multiply(String num1, String num2) {
            if (num1.equals("0") || num2.equals("0")) return "0";

            String res = "0";
            for (int i = num2.length() - 1; i >= 0; i--) {
                // tmp保存num2[i]与num1相乘的结果，注意进位补0
                StringBuffer tmp = new StringBuffer();
                for (int j = i; j < num2.length() - 1; j++) {
                    tmp.append("0");
                }

                // 将num2[i]与num1逐位相乘
                int n2 = num2.charAt(i) - '0';
                int carry = 0;
                int j = num1.length() - 1;
                while (j >= 0 || carry != 0) {
                    int n1 = j >= 0 ? num1.charAt(j) - '0' : 0;
                    int val = n1 * n2 + carry;
                    carry = val / 10;
                    val %= 10;
                    tmp.append(val);
                    j--;
                }

                // 将每次相乘的结果相加
                res = sum(res, tmp.reverse().toString());
            }
            return res;
        }

        // 字符串相加（415）
        private String sum(String num1, String num2) {
            StringBuffer sb = new StringBuffer();
            int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
            while (i >= 0 || j >= 0 || carry != 0) {
                int n1 = i >= 0 ? num1.charAt(i) - '0' : 0;
                int n2 = j >= 0 ? num2.charAt(j) - '0' : 0;
                int val = n1 + n2 + carry;
                carry = val / 10;
                val %= 10;
                sb.append(val);
                i--;
                j--;
            }
            sb.reverse();
            return sb.toString();
        }

    }

}
