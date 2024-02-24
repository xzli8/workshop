package data_structure_algorithm.leetcode;

public class _461hammingDistance {

    public static class Solution1 {

        /**
         位运算：先进行异或，然后计算异或结果中位1的数量
         时间复杂度：O(1)
         空间复杂度：O(1)
         */
         public int hammingDistance(int x, int y) {
             return Integer.bitCount(x ^ y);
         }

    }



    public static class Solution2 {

        /**
         位运算：用"n & (n - 1)"消除n末尾的1
         时间复杂度：O(logN)
         空间复杂度：O(1)
         */
        public int hammingDistance(int x, int y) {
            int n = x ^ y;
            int count = 0;
            while (n != 0) {
                count++;
                n &= (n-1);
            }
            return count;
        }

    }

}
