package data_structure_algorithm.leetcode;

public class _191hammingWeight {

    public static class Solution1 {

        /**
         右移32次
         */
         public int hammingWeight(int n) {
             int count = 0;
             for (int i = 0; i < 32; i++) {
                 count += (n & 1);
                 n >>= 1;
             }
             return count;
         }

    }



    public static class Solution2 {

        /**
         利用 n & (n-1) 来消除n末尾的1
         */
        public int hammingWeight(int n) {
            int count = 0;
            while (n != 0) {
                count++;
                n &= n-1;
            }
            return count;
        }

    }

}
