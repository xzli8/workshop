package data_structure_algorithm.leetcode;

public class _74searchMatrix {

    public static class Solution1 {

        /**
         二分查找
         时间复杂度：O(log m*n)
         空间复杂度：O(m*n)
         */
         public boolean searchMatrix(int[][] matrix, int target) {
             // 2D -> 1D
             int m = matrix.length, n = matrix[0].length;
             int[] nums = new int[m * n];
             for (int i = 0; i < m; i++) {
                 for (int j = 0; j < n; j++) {
                     nums[i * n + j] = matrix[i][j];
                 }
             }

             // BS
             int low = 0, high = m * n - 1;
             while (low <= high) {
                 int mid = low + ((high - low) >> 1);
                 if (nums[mid] == target) {
                     return true;
                 } else if (nums[mid] < target) {
                     low = mid + 1;
                 } else {
                     high = mid - 1;
                 }
             }
             return false;
         }

    }



    public static class Solution2 {

        /**
         一次二分查找
         时间复杂度：O(log m*n)
         空间复杂度：O(1)
         */
         public boolean searchMatrix(int[][] matrix, int target) {
             int m = matrix.length, n = matrix[0].length;
             int low = 0, high = m * n - 1;
             while (low <= high) {
                 int mid = low + ((high - low) >> 1);
                 int x = matrix[mid / n][mid % n];
                 if (x == target) {
                     return true;
                 } else if (x < target) {
                     low = mid + 1;
                 } else {
                     high = mid - 1;
                 }
             }
             return false;
         }

    }



    public static class Solution3 {


        /**
         两次二分查找
         思路：先定位在哪一行，再定位在这一行的哪一列
         时间复杂度：O(logM) + O(logN) = O(log M*N)
         空间复杂度：O(1)
         */
        public boolean searchMatrix(int[][] matrix, int target) {
            int row = binarySearchFirstColumn(matrix, target);
            if (row < 0) {
                return false;
            }
            return binarySearch(matrix[row], target);
        }

        // 对矩阵第一列的元素二分查找，找到最后一个小于等于target的元素
        private int binarySearchFirstColumn(int[][] matrix, int target) {
            int low = 0, high = matrix.length - 1;
            while (low <= high) {
                int mid = low + ((high - low) >> 1);
                if (matrix[mid][0] > target) {
                    high= mid - 1;
                } else {
                    if (mid == matrix.length - 1 || matrix[mid + 1][0] > target) {
                        return mid;
                    }
                    else {
                        low = mid + 1;
                    }
                }
            }
            return -1;
        }

        private boolean binarySearch(int[] nums, int target) {
            int low = 0, high = nums.length - 1;
            while (low <= high) {
                int mid = low + ((high - low) >> 1);
                if (nums[mid] == target) {
                    return true;
                } else if (nums[mid] < target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            return false;
        }


    }



}
