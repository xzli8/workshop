package data_structure_algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;

public class _403canCross {

    public static class Solution1 {

        /**
         DFS
         */
        public boolean canCross(int[] stones) {
            this.n = stones.length;
            this.stones = stones;
            return dfs(0, 0);
        }

        private int n;
        private int[] stones;
        private Set<String> memo = new HashSet<>();
        private boolean dfs(int index, int k) {
            String key = index + "-" + k;
            if (memo.contains(key)) {
                return false;
            } else {
                memo.add(key);
            }
            if (index == n - 1) return true;
            for (int i = index + 1; i < n; i++) {
                int gap = stones[i] - stones[index];
                if (k - 1 <= gap && gap <= k + 1) {
                    if (dfs(i, gap)) {
                        return true;
                    }
                }
                else if (gap > k + 1) {
                    break; // 后面的更远，不可能跳到
                }
                else if (gap < k - 1) {
                    continue;  // 太近，尝试后面更远点的
                }
            }
            return false;
        }

    }

}
