package data_structure_algorithm.leetcode;

public class _378kthSmallest {

    public static class Solution1 {

        /**
         二分查找（参考官方题解）
         ref:https://leetcode.cn/problems/kth-smallest-element-in-a-sorted-matrix/solutions/311472/you-xu-ju-zhen-zhong-di-kxiao-de-yuan-su-by-leetco/
         思路：取一个mid，其中矩阵左上角的所有元素都小于等于mid，右下角的所有元素都大于mid
         注意：如何确保最后找到的数一定在矩阵中？
         */
         public int kthSmallest(int[][] matrix, int k) {
             int n = matrix.length;
             int left = matrix[0][0], right = matrix[n-1][n-1];
             while (left < right) {
                 int mid = left + ((right - left) >> 1);
                 int count = countByCol(matrix, mid);
                 // 第k小的数在右下角，且不包含mid
                 if (count < k) {
                     left = mid + 1;
                 }
                 // 第k小的数在左上角，且可能包含mid
                 else {
                     right = mid;
                 }
             }
             return right;
         }

        /**
         按列计算小于等于mid的元素的个数
         时间复杂度：O(N) 行i和列j加起来移动次数不超过2N次
         空间复杂度：O(1)
         */
        public int countByCol(int[][] matrix, int mid) {
            // 小于等于mid元素的个数
            int count = 0;

            // 从左下角开始计算
            int n = matrix.length, i = n - 1, j = 0;
            while (i >= 0 && j < n) {
                // 第j列有i+1个元素小于等于mid
                if (matrix[i][j] <= mid) {
                    count += i+1;
                    j++;
                }
                // 第i行第j列的元素大于mid，需要往上退一步
                else {
                    i--;
                }
            }
            return count;
        }


    }



    public static class Solution2 {

        /**
         二分：找第一个满足条件的元素(最小)
         分析：case1中，取13和14都能满足条件，但13是第一个
         */
        public int kthSmallest(int[][] matrix, int k) {
            int n = matrix.length;
            int left = matrix[0][0], right = matrix[n-1][n-1];

            // 找第一个满足条件(>=)的元素
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (countByCol(matrix, mid) >= k) {
                    if (countByCol(matrix, mid - 1) < k) {
                        return mid;
                    }
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return -1;
        }

        /**
         按列计算小于等于mid的元素的个数
         时间复杂度：O(N) 行i和列j加起来移动次数不超过2N次
         空间复杂度：O(1)
         */
        public int countByCol(int[][] matrix, int mid) {
            // 小于等于mid元素的个数
            int count = 0;

            // 从左下角开始计算
            int n = matrix.length, i = n - 1, j = 0;
            while (i >= 0 && j < n) {
                // 第j列有i+1个元素小于等于mid
                if (matrix[i][j] <= mid) {
                    count += i+1;
                    j++;
                }
                // 第i行第j列的元素大于mid，需要往上退一步
                else {
                    i--;
                }
            }
            return count;
        }

    }

}
