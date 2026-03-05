package data_structure_algorithm.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class _378kthSmallest {

    public static class Solution1 {

        /**
         二分搜索:O(Nlog(L - R)), O(1)
         Note：取一个mid，其中矩阵左上角的所有元素都小于等于mid，右下角的所有元素都大于mid，计算小于等于mid的元素个数(相似题240)
         Ref:https://leetcode.cn/problems/kth-smallest-element-in-a-sorted-matrix/solutions/311472/you-xu-ju-zhen-zhong-di-kxiao-de-yuan-su-by-leetco/
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
         按列计算小于等于mid的元素的个数(相似题:240)
         时间复杂度：O(N) 行i和列j加起来移动次数不超过2N次
         空间复杂度：O(1)
         */
        public int countByCol(int[][] matrix, int mid) {
            // 小于等于mid元素的个数
            int count = 0;

            // 从左下角开始计算
            int n = matrix.length, i = n - 1, j = 0;
            while (i >= 0 && j < n) {
                // 第j列有i + 1个元素小于等于mid
                if (matrix[i][j] <= mid) {
                    count += i + 1;
                    j++;
                }
                // 第i行第j列的元素大于mid，需要往上退一步
                else {
                    i--;
                }
            }
            return count;
        }

        // 另一种写法
        public int kthSmallestII(int[][] matrix, int k) {
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

    }


    public static class Solution2 {

        /**
         * 直接排序: O(N^2logN), O(N^2)
         * Note: 将这个二维数组转成一维数组，并对该一维数组进行排序。最后这个一维数组中的第 k 个数即为答案。
         */
        public int kthSmallest(int[][] matrix, int k) {
            int rows = matrix.length, columns = matrix[0].length;
            int[] sorted = new int[rows * columns];
            int index = 0;
            for (int[] row : matrix) {
                for (int num : row) {
                    sorted[index++] = num;
                }
            }
            Arrays.sort(sorted);
            return sorted[k - 1];
        }

    }


    public static class Solution3 {

        /**
         * 归并排序：O(KlogN), O(N)
         * Note: 这个矩阵的每一行均为一个有序数组。问题即转化为从这 n 个有序数组中找第 k 大的数，可以想到利用归并排序的做法，归并到第 k 个数即可停止。
         * 一般归并排序是两个数组归并，而本题是 n 个数组归并，所以需要用小根堆维护，以优化时间复杂度。
         */
        public int kthSmallest(int[][] matrix, int k) {
            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
            int n = matrix.length;
            for (int i = 0; i < n; i++) pq.offer(new int[]{matrix[i][0], i, 0});
            for (int i = 0; i < k - 1; i++) {
                int[] now = pq.poll();
                if (now[2] != n - 1) {
                    pq.offer(new int[]{matrix[now[1]][now[2] + 1], now[1], now[2] + 1});
                }
            }
            return pq.poll()[0];
        }

    }

}
