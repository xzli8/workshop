package data_structure_algorithm.leetcode;

public class _136singleNumber {

    public static class Solution0 {

        /**
         位运算：相同的元素异或结果为0
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int singleNumber(int[] nums) {
            int res = 0;
            for (int num : nums) res ^= num;
            return res;
        }

    }



    public static class Solution1 {

        /**
         异或
         分析：如果没有空间限制，可以用hash表记录元素及其出现次数
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int singleNumber(int[] nums) {
            int res = 0;
            for (int i = 0; i < nums.length; i++) {
                res ^= nums[i];
            }
            return res;
        }

    }

}
