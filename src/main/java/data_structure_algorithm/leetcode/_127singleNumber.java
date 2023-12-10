package data_structure_algorithm.leetcode;

public class _127singleNumber {

    public static class Solution1 {

        /**
         遍历统计每个二进制位上1出现的次数，然后对3取余，最后左移恢复（可扩展到“其余每个元素都出现k次”）
         分析：如果没有空间限制，可以用一个hash表记录每个元素及其出现的次数
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int singleNumber(int[] nums) {
            // 遍历统计
            int[] counts = new int[32];
            for (int i = 0; i < nums.length; i++) {
                for (int j = 0; j < 32; j++) {
                    counts[j] += nums[i] & 1;   // 更新第j位
                    nums[i] >>>= 1;     // 右移一位（无符号）
                }
            }

            // 对3取余
            for (int i = 0; i < 32; i++) {
                counts[i] %= 3;
            }

            // 左移恢复
            int res = 0;
            for (int i = 0; i < 32; i++) {
                res <<= 1;
                res |= counts[31-i];
            }
            return res;
        }

    }

}
