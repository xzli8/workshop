package data_structure_algorithm.leetcode;

public class _240searchMatrix {

    public static class Solution1 {

        /**
         遍历查找：O(M + N), O(1)
         Note: 从左下/右上开始，每次排除一行或者一列
         */
        public boolean searchMatrix(int[][] matrix, int target) {
            int m = matrix.length, n = matrix[0].length;
            int i = 0, j = n - 1;
            while (i < m && j >= 0) {
                if (matrix[i][j] == target) return true;
                else if (matrix[i][j] < target) i++;
                else j--;
            }
            return false;
        }

    }


    public static class Solution2 {

        /**
         逐行二分查找: O(MlogN), O(1)
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

}
