package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _119getRow {

    public static class Solution1 {

        /**
         动态规划
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public List<Integer> getRow(int rowIndex) {
            // 初始状态
            List<Integer> prev = new ArrayList<>();
            prev.add(1);

            // 状态转移
            for (int i = 1; i <= rowIndex; i++) {
                List<Integer> cur = new ArrayList<>();
                cur.add(1);
                for (int j = 1; j < i; j++) {
                    cur.add(prev.get(j - 1) + prev.get(j));
                }
                cur.add(1);
                prev = cur;
            }
            return prev;
        }

    }

}
