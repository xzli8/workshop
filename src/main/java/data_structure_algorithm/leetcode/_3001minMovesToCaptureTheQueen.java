package data_structure_algorithm.leetcode;

public class _3001minMovesToCaptureTheQueen {

    public static class Solution1 {

        /**
         数学：分析并枚举所有可能
         */
        public int minMovesToCaptureTheQueen(int a, int b, int c, int d, int e, int f) {
            if (a == e && (c != e || !inBetween(b, d, f)) || // 车直接攻击到皇后（同一行）
                    b == f && (d != f || !inBetween(a, c, e)) || // 车直接攻击到皇后（同一列）
                    c + d == e + f && (a + b != e + f || !inBetween(c, a, e)) || // 象直接攻击到皇后
                    c - d == e - f && (a - b != e - f || !inBetween(c, a, e))) {
                return 1;
            }
            return 2;
        }

        // m 在 l 和 r 之间（写不写等号都可以）
        private boolean inBetween(int l, int m, int r) {
            return Math.min(l, r) < m && m < Math.max(l, r);
        }

    }

}
