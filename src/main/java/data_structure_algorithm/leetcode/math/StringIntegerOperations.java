package data_structure_algorithm.leetcode.math;

import org.junit.Test;

/**
 * Add/Subtract/Multiply/Divide (From AI)
 */
public class StringIntegerOperations {

    // 参考：415
    public String addStrings(String num1, String num2) {
        StringBuilder result = new StringBuilder();
        int carry = 0;
        int i = num1.length() - 1;
        int j = num2.length() - 1;

        while (i >= 0 || j >= 0 || carry != 0) {
            int digit1 = i >= 0 ? num1.charAt(i--) - '0' : 0;
            int digit2 = j >= 0 ? num2.charAt(j--) - '0' : 0;
            int sum = digit1 + digit2 + carry;
            result.append(sum % 10);
            carry = sum / 10;
        }

        return result.reverse().toString();
    }

    // 参考：补充题21
    public String subtractStrings(String num1, String num2) {
        // 被减数小于减数时，需要交换顺序并且结果加负号
        if (less(num1, num2)) {
            return "-" + subtractStrings(num2, num1);
        }

        StringBuilder result = new StringBuilder();
        int borrow = 0;
        int i = num1.length() - 1;
        int j = num2.length() - 1;

        while (i >= 0 || j >= 0) {
            int digit1 = i >= 0 ? num1.charAt(i--) - '0' : 0;
            int digit2 = j >= 0 ? num2.charAt(j--) - '0' : 0;
            int difference = digit1 - digit2 - borrow;
            if (difference < 0) {
                difference += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }
            result.append(difference);
        }
        String res = result.reverse().toString();

        // 去除前导零
        int pos = 0;
        while (pos < result.length() && res.charAt(pos) == '0') {
            pos++;
        }
        return pos == res.length() ? "0" : res.substring(pos);
    }

    // 参考：43
    public String multiplyStrings(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";

        // 将num2的每一位分别与num1相乘，再将每一步的结果相加
        String result = "0";
        for (int i = num2.length() - 1; i >= 0; i--) {
            // 构建当前位置的中间结果
            StringBuilder intermediateResult = new StringBuilder();
            for (int j = i; j < num2.length() - 1; j++) {
                intermediateResult.append("0");
            }

            int digit2 = num2.charAt(i) - '0';
            int carry = 0;
            int j = num1.length() - 1;
            while (j >= 0 || carry != 0) {
                int digit1 = j >= 0 ? num1.charAt(j--) - '0' : 0;
                int product = digit1 * digit2 + carry;
                carry = product / 10;
                product %= 10;
                intermediateResult.append(product);
            }

            // 将当前位置的中间结果与最终结果相加
            result = addStrings(result, intermediateResult.reverse().toString());
        }
        return result;
    }

    // 参考：166
    public String[] divideStrings(String dividend, String divisor) {
        // 除数为0直接抛异常
        if (divisor.equals("0")) {
            throw new IllegalArgumentException("Division by zero");
        }

        // 处理符号位
        boolean isNegative = (dividend.startsWith("-") != divisor.startsWith("-"));
        dividend = dividend.startsWith("-") ? dividend.substring(1) : dividend;
        divisor = divisor.startsWith("-") ? divisor.substring(1) : divisor;

        // 被除数小于除数时，直接返回结果
        if (less(dividend, divisor)) {
            return new String[] {"0", isNegative ? "-" + dividend : dividend};
        }

        // 计算商和余数
        String remainder = dividend;
        int quotient = 0;
        while (less(divisor, remainder) || remainder.equals(divisor)) {
            remainder = subtractStrings(remainder, divisor);
            quotient++;
        }
        return new String[] {isNegative ? "-" + quotient : "+" + quotient, remainder};
    }

    private boolean less(String num1, String num2) {
        return num1.length() < num2.length() || (num1.length() == num2.length() && num1.compareTo(num2) < 0);
    }

    @Test
    public void test() {
        System.out.println(addStrings("123", "456")); // Output: "579"
        System.out.println(subtractStrings("789", "456")); // Output: "333"
        System.out.println(multiplyStrings("12", "34")); // Output: "408"
        String[] result = divideStrings("100", "3");
        System.out.println("Quotient: " + result[0] + ", Remainder: " + result[1]); // Output: Quotient: 33, Remainder: 1
    }

}
