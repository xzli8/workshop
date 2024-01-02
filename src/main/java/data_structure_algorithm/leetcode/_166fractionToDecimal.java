package data_structure_algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class _166fractionToDecimal {

    public static class Solution1 {

        /**
         模拟竖式除法
         时间复杂度：O(N)
         空间复杂度：O(N)

         数学知识：无理数，无限不循环小数，不能写成两个整数之比；有理数，整数和分数的集合，一定是循环小数
         */
        public String fractionToDecimal(int numerator, int denominator) {
            // 转long计算，防止溢出
            long a = numerator, b = denominator;

            // 如果能够整除，直接返回计算结果
            if(a % b == 0) return String.valueOf(a / b);

            // 如果结果为负数，先追加负号
            StringBuilder sb = new StringBuilder();
            if (a * b < 0) sb.append('-');
            a = Math.abs(a);
            b = Math.abs(b);

            // 计算整数部分
            sb.append(String.valueOf(a / b) + ".");

            // key:余数, value:位置(下标)
            Map<Long, Integer> map = new HashMap<>();

            // 计算小数部分(模拟竖式除法)
            a %= b;
            while (a != 0) {
                // 记录余数对应的下标
                map.put(a, sb.length());

                // 余数*10
                a *= 10;

                // 将商添加到答案
                sb.append(a / b);

                // 重新计算余数
                a %= b;

                // 如果余数之前出现过，说明出现了循环，将[出现位置 到 当前位置]的部分抠出来
                if (map.containsKey(a)) {
                    int idx = map.get(a);
                    return String.format("%s(%s)", sb.substring(0, idx), sb.substring(idx));
                }
            }
            return sb.toString();
        }

    }

}
