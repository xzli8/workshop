package data_structure_algorithm.leetcode;

public class _231isPowerOfTwo {

    public static class Solution1 {

        /**
         位运算：利用 n & (n - 1)消除n末位1
         时间复杂度：O(1)
         空间复杂度：O(1)
         */
        public boolean isPowerOfTwo(int n) {
            return (n > 0) && ((n & (n-1)) == 0);
        }

    }

}
