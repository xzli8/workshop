package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _1222queensAttacktheKing {

    public static class Solution1 {

        /**
         数学：矩阵模拟
         T: O(N)
         S: O(N)
         */
        public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
            int[][] dirs = {{1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}};
            boolean[][] isQueen = new boolean[8][8]; // 数组效率比哈希表高
            for (int[] q : queens) {
                isQueen[q[0]][q[1]] = true;
            }
            List<List<Integer>> ans = new ArrayList<>();
            for (int[] d : dirs) {
                int x = king[0] + d[0];
                int y = king[1] + d[1];
                while (0 <= x && x < 8 && 0 <= y && y < 8) {
                    if (isQueen[x][y]) {
//                        ans.add(List.of(x, y)); // List.of()为java9开始引入的静态工厂方法
                        break;
                    }
                    x += d[0];
                    y += d[1];
                }
            }
            return ans;
        }

    }

}
