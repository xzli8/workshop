package data_structure_algorithm.leetcode;

public class _498findDiagonalOrder {

    public static class Solution1 {

        /**
         模拟遍历
         ref:https://leetcode.cn/problems/diagonal-traverse/solutions/11440/dui-jiao-xian-bian-li-fen-xi-ti-mu-zhao-zhun-gui-l/
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int[] findDiagonalOrder(int[][] mat) {
            int m = mat.length, n = mat[0].length;
            int[] res = new int[m*n];
            int idx = 0;

            // 一共有(m+n-1)条对角线
            for (int i = 0; i < m+n-1; i++) {
                // 偶数时，从下往上遍历；奇数时，从上往下遍历
                if (i % 2 == 0) {
                    // 确定下标的初始值
                    int x = i < m ? i : m-1;
                    int y = i - x;
                    // 限制下标范围
                    while (x >= 0 && y < n) {
                        res[idx++] = mat[x][y];
                        x--;
                        y++;
                    }
                } else {
                    // 确定下标的初始值
                    int y = i < n ? i : n-1;
                    int x = i - y;
                    // 限制下标范围
                    while (y >= 0 && x < m) {
                        res[idx++] = mat[x][y];
                        y--;
                        x++;
                    }
                }
            }
            return res;
        }

    }

}
