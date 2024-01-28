package data_structure_algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;

public class _365canMeasureWater {

    /**
     分析：需要不断尝试每一种可能的方法，DFS/BFS都可以
     */

    public static class Solution1 {

        /**
         DFS
         时间复杂度：O(M + N)
         空间复杂度：O(M + N)
         */
        public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
            this.cap1 = jug1Capacity; this.cap2 = jug2Capacity; this.target = targetCapacity;
            return dfs(0, 0);
        }

        private int cap1, cap2, target;
        private Set<Long> visited = new HashSet<>();
        private boolean dfs(int cur1, int cur2) {
            if (!visited.add(hash(cur1, cur2))) return false;
            if (cur1 == target || cur2 == target || cur1 + cur2 == target) return true;
            // 把1灌满/把2罐满/把1清空/把2清空
            if (dfs(cap1, cur2) || dfs(cur1, cap2) || dfs(0, cur2) || dfs(cur1, 0)) return true;
            // 把1倒入2中，直到罐满或倒空/把2倒入1中，直到罐满或倒空
            int remain2 = Math.min(cur1, cap2 - cur2), remain1 = Math.min(cur2, cap1 - cur1);
            return dfs(cur1 - remain2, cur2 + remain2) || dfs(cur1 + remain1, cur2 - remain1);
        }

        private long hash(int cur1, int cur2) {
            return cur1 * (long) 1e6 + cur2;
        }

    }

}
