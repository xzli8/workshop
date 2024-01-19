package data_structure_algorithm.leetcode;

public class _633judgeSquareSum {

    public static class Solution1 {

        /**
         双指针
         时间复杂度：O(sqrt(N))
         空间复杂度：O(1)
         */
        public boolean judgeSquareSum(int c) {
            long left = 0, right = (long) Math.sqrt(c);
            while (left <= right) {
                long sum = left * left + right * right;
                if (sum == c) return true;
                else if (sum > c) right--;
                else left++;
            }
            return false;
        }

    }

}
