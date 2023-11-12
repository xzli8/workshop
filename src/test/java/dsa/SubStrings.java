package dsa;

import org.junit.Assert;
import org.junit.Test;

public class SubStrings {

    @Test
    public void test() {
        Assert.assertEquals("1607", subStrings("9999", "8392"));
        Assert.assertEquals("-1607", subStrings("8392", "9999"));
        Assert.assertEquals("0", subStrings("8392", "8392"));
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
    public String subStrings(String num1, String num2) {
        String res;
        // 如果是小数减大数，交换位置，然后前面加负号
        if (less(num1, num2)) {
            res = sub(num2, num1);
            if (!res.equals("0")) res = "-" + res;
        } else {
            res = sub(num1, num2);
        }
        return res;
    }

    private boolean less(String num1, String num2) {
        if (num1.length() == num2.length()) {
            return num1.compareTo(num2) < 0;
        }
        return num1.length() < num2.length();
    }

    /**
     *  计算num1 - num2，这里num1 > num2
     */
    private String sub(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, borrow = 0;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int tmp = x - borrow - y;
            borrow = tmp < 0 ? 1 : 0;
            sb.append(tmp < 0 ? tmp + 10 : tmp);
            i--;
            j--;
        }
        String res = sb.reverse().toString();

        // 删除前导0
        int pos = 0;
        while (pos < res.length() - 1) {
            if (res.charAt(pos) != '0') break;
            pos++;
        }
        return res.substring(pos);
    }

}
