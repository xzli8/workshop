package data_structure_algorithm.leetcode;

public class _367isPerfectSquare {

    public static class Solution1 {

        /**
         二分查找
         时间复杂度：O(logN)
         空间复杂度：O(1)
         */
        public boolean isPerfectSquare(int num) {
            int left = 1, right = num;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                // 写成商的形式防止溢出
                if ((num % mid == 0) && (mid == num / mid)) return true;
                else if (mid < num / mid) left = mid + 1;
                else right = mid - 1;
            }
            return false;
        }

    }

}
