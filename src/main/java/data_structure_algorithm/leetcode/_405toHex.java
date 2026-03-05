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
            StringBuilder sb = new StringBuilder();
            while (n != 0) {
                long mod = n % 16;
                char c = (char) (mod + '0');
                if (mod >= 10) c = (char) (mod - 10 + 'a');
                sb.append(c);
                n /= 16;
            }
            return sb.reverse().toString();
        }

    }

}
