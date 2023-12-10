package data_structure_algorithm.leetcode;

public class _260singleNumber {

    public static class Solution1 {

        /**
         异或，然后通过异或的位结果将两个数区分开
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int[] singleNumber(int[] nums) {
            // 异或
            int xor = 0;
            for (int i = 0; i < nums.length; i++) {
                xor ^= nums[i];
            }

            // 取最低有效位（补码！取异或结果中的任意一位都可以！）
            // 防止溢出：因为二进制有正负0，负零用于多表示一位负数，这个负数如果取相反数，会产生溢出，所以不能用 a & (-a) 取最低有效位。那怎么求负0的最低有效位呢？负0的特点是第一位是1，其余位是0，所以它的最低有效位就是自己
            int mask = (xor == Integer.MIN_VALUE) ? xor : xor & (-xor);

            // 计算两个元素
            int num1 = 0, num2 = 0;
            for (int i = 0; i < nums.length; i++) {
                if ((nums[i] & mask) == 0) {
                    num1 ^= nums[i];
                } else {
                    num2 ^= nums[i];
                }
            }
            return new int[] {num1, num2};
        }

    }

}
