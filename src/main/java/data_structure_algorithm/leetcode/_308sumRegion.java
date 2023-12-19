package data_structure_algorithm.leetcode;

public class _308sumRegion {

    /**
     * 参考题解：https://blog.csdn.net/u011386173/article/details/128597513
     */

    public static class Solution1 {

        // 测试链接：https://leetcode.com/problems/range-sum-query-2d-mutable
        // 但这个题是付费题目
        public class NumMatrix {
            private int[][] tree;
            private int[][] nums;
            private int N;
            private int M;

            public NumMatrix(int[][] matrix) {
                if (matrix.length == 0 || matrix[0].length == 0) {
                    return;
                }
                N = matrix.length;
                M = matrix[0].length;
                tree = new int[N + 1][M + 1];
                nums = new int[N][M];
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        update(i, j, matrix[i][j]);
                    }
                }
            }

            //累加和
            //计算左上角为(1,1)，右下角为(i,j)围成区域的累加和
            private int sum(int row, int col) {
                int sum = 0;
                for (int i = row + 1; i > 0; i -= i & (-i)) { //每次减去二进制形式最右侧的1
                    for (int j = col + 1; j > 0; j -= j & (-j)) {
                        sum += tree[i][j];
                    }
                }
                return sum;
            }

            //更新操作
            //将(row,col)位置的值修改为val，可以修改为add来实现
            public void update(int row, int col, int val) {
                if (N == 0 || M == 0) {
                    return;
                }
                int add = val - nums[row][col]; //增量 = 要修改成的值 - 原来的值
                nums[row][col] = val;
                //受影响的范围
                for (int i = row + 1; i <= N; i += i & (-i)) {
                    for (int j = col + 1; j <= M; j += j & (-j)) {
                        tree[i][j] += add;
                    }
                }
            }
            //任意区域的累加和
            //计算 (row1,col1) 到 (row2, col2) 范围的值累加和
            public int sumRegion(int row1, int col1, int row2, int col2) {
                if (N == 0 || M == 0) {
                    return 0;
                }
                return sum(row2, col2) + sum(row1 - 1, col1 - 1) - sum(row1 - 1, col2) - sum(row2, col1 - 1);
            }
        }

    }

}
