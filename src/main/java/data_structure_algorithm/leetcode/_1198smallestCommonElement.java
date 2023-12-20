package data_structure_algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

public class _1198smallestCommonElement {

    /**
     * 题目描述：
     * 给你一个矩阵 mat，其中每一行的元素都已经按 递增 顺序排好了。请你帮忙找出在所有这些行中 最小的公共元素。
     * 如果矩阵中没有这样的公共元素，就请返回 -1。
     *
     * 示例：
     * 输入：mat = [[1,2,3,4,5],[2,4,5,8,10],[3,5,7,9,11],[1,3,5,7,9]]
     * 输出：5
     *
     * 提示：
     * 1 <= mat.length, mat[i].length <= 500
     * 1 <= mat[i][j] <= 10^4
     * mat[i] 已按递增顺序排列。
     *
     */

    /**
     *  参考题解：https://www.cnblogs.com/seyjs/p/11596008.html
     */

    public static class Solution1 {

        @Test
        public void test() {
            Assert.assertEquals(5, smallestCommonElement(new int[][] {{1,2,3,4,5}, {2,4,5,8,10}, {3,5,7,9,11}, {1,3,5,7,9}}));
        }

        /**
         *  多指针：(类似题："1213.三个有序数组的交集")
         *      时间复杂度：O(M * N)
         *      空间复杂度：O(1)
         */
        public int smallestCommonElement(int[][] mat) {
            int m = mat.length, n = mat[0].length;
            int[] pos = new int[m];
            while (hasRemain(pos, n)) {
                if (allEqual(mat, pos)) {
                    return mat[0][pos[0]];
                } else {
                    int min = findMin(mat, pos);
                    for (int i = 0; i < m; i++) {
                        if (mat[i][pos[i]] == min) {
                            pos[i]++;
                        }
                    }
                }
            }
            return -1;
        }

        private boolean hasRemain(int[] pos, int n) {
            for (int i = 0; i < pos.length; i++) {
                if (pos[i] >= n) return false;
            }
            return true;
        }

        private boolean allEqual(int[][] mat, int[] pos) {
            int i = 1;
            while (i < pos.length) {
                if (mat[i][pos[i]] != mat[i - 1][pos[i - 1]]) {
                    break;
                }
                i++;
            }
            return i == pos.length;
        }

        private int findMin(int[][] mat, int[] pos) {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < mat.length; i++) {
                min = Math.min(min, mat[i][pos[i]]);
            }
            return min;
        }

    }

}
