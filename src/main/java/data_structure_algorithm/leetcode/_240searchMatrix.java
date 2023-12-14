package data_structure_algorithm.leetcode;

public class _240searchMatrix {

    public static class Solution1 {

        /**
         1.逐行二分查找
         时间复杂度：O(mlogn)
         空间复杂度：O(1)
         */
         public boolean searchMatrix(int[][] matrix, int target) {
             int m = matrix.length, n = matrix[0].length;
             for (int i = 0; i < m; i++) {
                 int low = 0, high = n - 1;
                 while (low <= high) {
                     int mid = low + ((high - low) >> 1);
                     if (matrix[i][mid] == target) {
                         return true;
                     } else if (matrix[i][mid] < target) {
                         low = mid + 1;
                     } else {
                         high = mid - 1;
                     }
                 }
             }
             return false;
         }

    }



    public static class Solution2 {

        /**
         2.从右上开始遍历(左下也是同理)，一次排除一行或者一列
         思路：
         if matrix[i][j] == target，完成
         if matrix[i][j] < target, i++
         if matrix[i][j] > target, j--
         时间复杂度：O(m+n)
         空间复杂度：O(1)
         */
        public boolean searchMatrix(int[][] matrix, int target) {
            int m = matrix.length, n = matrix[0].length;
            int i = 0, j = n-1;
            while (i < m && j >= 0) {
                if (matrix[i][j] == target) {
                    return true;
                } else if (matrix[i][j] < target) {
                    i++;
                } else {
                    j--;
                }
            }
            return false;
        }

    }



}
