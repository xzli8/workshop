package data_structure_algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class _405toHex {

    public static class Solution1 {

        /**
         模拟 + 进制转换
         时间复杂度：O(logN)
         空间复杂度：O(1)
         */
        public String toHex(int num) {
            if (num == 0) return "0";

            // 如果num为负数，先转换成补码
            long n = num;
            if (n < 0) n += (long)(Math.pow(2, 32));

            // 进制转换
            Map<Long, Character> digit2Char = new HashMap<>();
            digit2Char.put(0L, '0');
            digit2Char.put(1L, '1');
            digit2Char.put(2L, '2');
            digit2Char.put(3L, '3');
            digit2Char.put(4L, '4');
            digit2Char.put(5L, '5');
            digit2Char.put(6L, '6');
            digit2Char.put(7L, '7');
            digit2Char.put(8L, '8');
            digit2Char.put(9L, '9');
            digit2Char.put(10L, 'a');
            digit2Char.put(11L, 'b');
            digit2Char.put(12L, 'c');
            digit2Char.put(13L, 'd');
            digit2Char.put(14L, 'e');
            digit2Char.put(15L, 'f');

            StringBuilder sb = new StringBuilder();
            while (n != 0) {
                sb.append(digit2Char.get(n % 16));
                n /= 16;
            }
            return sb.reverse().toString();
        }

    }

}
