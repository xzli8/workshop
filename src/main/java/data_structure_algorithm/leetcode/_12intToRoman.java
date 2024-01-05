package data_structure_algorithm.leetcode;

public class _12intToRoman {

    public static class Solution1 {

        /**
         哈希表 + 贪心：优先将大的基数拿来匹配(罗马数字的表示遵循加法法则)
         时间复杂度：O(logN)
         空间复杂度：O(1)
         */
        public String intToRoman(int num) {
            // 阿拉伯数字和罗马数字的对应关系(基数)
            int[] nums = new int[] {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
            String[] romans = new String[] {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};

            // 开始匹配
            StringBuilder res = new StringBuilder();
            int i = nums.length - 1;
            while (i >= 0) {
                while (num - nums[i] >= 0) {
                    res.append(romans[i]);
                    num -= nums[i];
                }
                i--;
            }
            return res.toString();
        }

    }

}
