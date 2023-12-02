package data_structure_algorithm.leetcode;

public class _738monotoneIncreasingDigits {

    public static class Solution1 {

        /**
         贪心(类似题："402.移掉k位数字")
         思路：第i位数字比第i+1位大时，将第i位减1，同时将第i位后面的每一位都变成9，
         就得到了小于当前数字的最大单调递增数字
         做法：逆序遍历n的每一位，进行如上调整。
         (顺序遍历不可以，因为后面的改动会影响前面，而逆序遍历先改后面再改前面，故后面的改动不会影响前面。)

         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int monotoneIncreasingDigits(int n) {
            char[] nums = String.valueOf(n).toCharArray();

            // 从倒数第二位开始逆序遍历
            int startIndex = nums.length;
            for (int i = nums.length - 2; i >= 0; i--) {
                // 进行调整
                if (nums[i] > nums[i + 1]) {
                    nums[i]--;
                    startIndex = i + 1;
                }
            }

            // 将startIndex后面的每一位都变成9
            for (int i = startIndex; i < nums.length; i++) {
                nums[i] = '9';
            }
            return Integer.parseInt(String.valueOf(nums));
        }

    }

}
