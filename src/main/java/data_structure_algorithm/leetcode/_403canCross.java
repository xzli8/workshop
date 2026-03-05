package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _403canCross {

    public static class Solution1 {

        /**
         DFS: TLE
         */
        public boolean canCross(int[] stones) {
            return dfs(stones, 0, 0);
        }

        private boolean dfs(int[] stones, int i, int k) {
            if (i == stones.length - 1) return true;
            for (int j = i + 1; j < stones.length; j++) {
                int gap = stones[j] - stones[i];
                if (gap < k - 1) continue; // 跳过太近的石头
                if (gap > k + 1) break; // 剪枝：后面的石头更远

                // 递归尝试
                if (dfs(stones, j, gap)) {
                    return true;
                }
            }
            return false;
        }

    }


    public static class Solution1_2 {

        /**
         DFS
         Note: 加上备忘录，避免递归重复状态，提高计算效率
         */
        public boolean canCross(int[] stones) {
            return dfs(stones, 0, 0);
        }

        private Set<String> visited = new HashSet<>();
        private boolean dfs(int[] stones, int i, int k) {
            if (i == stones.length - 1) return true;
            String state = i + "," + k; // 记录状态
            if (visited.contains(state)) return false; // 如果已经访问过，返回 false
            visited.add(state); // 标记为已访问

            for (int j = i + 1; j < stones.length; j++) {
                int gap = stones[j] - stones[i];
                if (gap < k - 1) continue; // 跳过太近的石头
                if (gap > k + 1) break; // 剪枝：后面的石头更远

                // 递归尝试
                if (dfs(stones, j, gap)) {
                    return true;
                }
            }
            return false;
        }

    }


    public static class Solution1_3 {

        /**
         DFS
         Note: 计算所有路径
         */
        public boolean canCross(int[] stones) {
            List<Integer> path = new ArrayList<>();
            path.add(0);
            dfs(stones, 0, 0, path);
            return paths.size() > 0;
        }

        private Set<String> visited = new HashSet<>();
        private List<List<Integer>> paths = new ArrayList<>();
        private void dfs(int[] stones, int i, int k, List<Integer> path) {
            if (i == stones.length - 1) {
                paths.add(new ArrayList<>(path));
            }
            String state = i + "," + k; // 记录状态
            if (visited.contains(state)) return; // 如果已经访问过，返回
            visited.add(state); // 标记为已访问

            for (int j = i + 1; j < stones.length; j++) {
                int gap = stones[j] - stones[i];
                if (gap < k - 1) continue; // 跳过太近的石头
                if (gap > k + 1) break; // 剪枝：后面的石头更远

                // 递归尝试
                path.add(j);
                dfs(stones, j, gap, path);
                path.remove(path.size() - 1);
            }
        }

    }

}
