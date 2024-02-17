package data_structure_algorithm.leetcode;

public class _371getSum {

    public static class Solution1 {

        /**
         位运算：异或^计算不进位和，与&计算进位和，二者
         时间复杂度：O(logN)
         空间复杂度：O(1)
         */
        public int getSum(int a, int b) {
            do {
                int sum = a ^ b, carry = (a & b) << 1;
                a = sum;
                b = carry;
            } while (b != 0);
            return a;
        }

    }

}
