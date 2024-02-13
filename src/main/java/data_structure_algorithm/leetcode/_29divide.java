package data_structure_algorithm.leetcode;

public class _29divide {

    public static class Solution1 {

        /**
         注意：溢出：如果转换为正数会发生溢出(-2^31转换为正数溢出)，所以转换为负数运算。div逻辑需要做相应修改
         */
        public int divide(int dividend, int divisor) {
            if (divisor == -1 && dividend == Integer.MIN_VALUE) return Integer.MAX_VALUE;    // 溢出

            boolean isNeg = true;
            if (dividend > 0) {
                dividend = -dividend;
                isNeg = !isNeg;
            }
            if (divisor > 0) {
                divisor = -divisor;
                isNeg = !isNeg;
            }

            int res = div(dividend, divisor);
            return isNeg ? res : -res;
        }

        /**
         计算x/y的商(x,y均为负数)
         原理：和正数运算相似，修改相应的判断条件
         注意：正数右移可以实现向下取整，负数右移实现的是向上取整，要想实现向下取整，需要先加1。所以有((x+1)>>1)
         */
        public int div(int x, int y) {
            if (x > y) return 0;
            int count = 1, tmp = y;
            while (tmp >= ((x+1) >> 1)) {
                count <<= 1;
                tmp <<= 1;
            }
            return count + div(x - tmp, y);
        }



        /**
         计算x/y的商(x,y均为正数)
         分析：除法的本质是减法，但用减法太慢。可用位运算实现倍增来加速。
         原理：不断左移被除数，直到其大于除数的一半；然后用被除数减去除数左移后的值，用这个差值继续除以被除数，直到被除数小于除数
         技巧：用位运算实现倍增
         */
        // public int div(int x, int y) {
        //     if (x < y) return 0;
        //     int count = 1, tmp = y;
        //     while (tmp <= (x >> 1)) {
        //         count <<= 1;
        //         tmp <<= 1;
        //     }
        //     return count + div(x - tmp, y);
        // }

    }

}
