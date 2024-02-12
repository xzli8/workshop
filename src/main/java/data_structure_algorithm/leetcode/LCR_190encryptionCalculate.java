package data_structure_algorithm.leetcode;

public class LCR_190encryptionCalculate {

    public static class Solution1 {

        /**
         位运算：首先计算不进位和，然后计算进位，最后将前两步结果相加(不断重复前两步直到进位结果为0)
         类似问题：不用新变量交换两个变量的值
         时间复杂度：O(logN)
         空间复杂度：O(1)
         */
        public int encryptionCalculate(int dataA, int dataB) {
            do {
                int sum = dataA ^ dataB;    // 用异或计算不进位和
                int carry = (dataA & dataB) << 1;   // 用位与和移位计算进位
                dataA = sum;
                dataB = carry;
            } while (dataB != 0);
            return dataA;
        }

    }

}
