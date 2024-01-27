package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _118generate {

    public static class Solution1 {

        /**
         动态规划(二维平面路径问题)
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> res = new ArrayList<>(numRows);

            // 初始状态
            List<Integer> prev = new ArrayList<>();
            prev.add(1);
            res.add(prev);

            // 状态转移
            for (int i = 1; i < numRows; i++) {
                List<Integer> cur = new ArrayList<>(i + 1);
                cur.add(1);
                for (int j = 1; j < i; j++) {
                    cur.add(prev.get(j - 1) + prev.get(j));
                }
                cur.add(1);
                res.add(cur);
                prev = cur;
            }
            return res;
        }

    }

}
