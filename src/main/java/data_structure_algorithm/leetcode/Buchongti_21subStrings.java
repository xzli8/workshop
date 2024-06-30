package data_structure_algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

public class Buchongti_21subStrings {

    @Test
    public void test() {
        Assert.assertEquals("1607", subtractStrings("9999", "8392"));
        Assert.assertEquals("-1607", subtractStrings("8392", "9999"));
        Assert.assertEquals("0", subtractStrings("8392", "8392"));
        Assert.assertEquals("97", subtractStrings("100", "3"));
    }

    /**
     *  题目描述：
     *      给定两个字符串形式的非负整数 num1 和num2 ，计算它们的差并同样以字符串形式返回。
     *      你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。
     *
     *  相似题：https://leetcode.cn/problems/add-strings/
     *
     *  NOTE：
     *      1.如果是小数减大数，交换次序，最后结果加负号；
     *      2.删除前导0，当结果是"00000"时，保留一个0即可；
     */
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

    private boolean less(String num1, String num2) {
        return num1.length() < num2.length() || (num1.length() == num2.length() && num1.compareTo(num2) < 0);
    }


}
