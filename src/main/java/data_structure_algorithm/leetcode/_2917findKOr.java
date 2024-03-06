package data_structure_algorithm.leetcode;

public class _2917findKOr {

    public static class Solution1 {

        /**
         位运算：枚举所有二进制位，统计每个二进制位的结果
         时间复杂度：O(NlogC)
         空间复杂度：O(1)
         */
        public int findKOr(int[] nums, int k) {
            int res = 0;
            for (int i = 0; i < 31; i++) {
                int cnt = 0;
                for (int num : nums) {
                    if (((num >> i) & 1) != 0) cnt++;
                }
                if (cnt >= k) res |= (1 << i);
            }
            return res;
        }

    }

}
