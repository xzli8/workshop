package data_structure_algorithm.leetcode;

public class _190reverseBits {

    public static class Solution1 {

        /**
         逐位颠倒(位运算)
         时间复杂度：O(1)
         空间复杂度：O(1)
         */
         public int reverseBits(int n) {
             int res = 0;
             for (int i = 0; i < 32; i++) {
                 res = (res << 1) | (n & 1);     // res = (res << 1) + (n & 1)
                 n >>>= 1;   // 无符号右移
             }
             return res;
         }

    }



    public static class Solution2 {

        /**
         分治:https://leetcode.cn/problems/reverse-bits/solutions/686503/fu-xue-ming-zhu-xun-huan-yu-fen-zhi-jie-hoakf/
         时间复杂度：O(1)
         空间复杂度：O(1)
         */
        public int reverseBits(int n) {
            n = n >>> 1 & M1 | (n & M1) << 1;
            n = n >>> 2 & M2 | (n & M2) << 2;
            n = n >>> 4 & M4 | (n & M4) << 4;
            n = n >>> 8 & M8 | (n & M8) << 8;
            return n >>> 16 | n << 16;
        }

        private static final int M1 = 0x55555555; // 01010101010101010101010101010101
        private static final int M2 = 0x33333333; // 00110011001100110011001100110011
        private static final int M4 = 0x0f0f0f0f; // 00001111000011110000111100001111
        private static final int M8 = 0x00ff00ff; // 00000000111111110000000011111111

    }

}
