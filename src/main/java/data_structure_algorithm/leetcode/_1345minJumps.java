package data_structure_algorithm.leetcode;

import java.util.*;

public class _1345minJumps {

    public static class Solution1 {

        /**
         BFS: TLE(todo)
         */
        public int minJumps(int[] arr) {
            // Build graph (Build a graph of n nodes where nodes are the indices of the array and edges for node i are nodes i+1, i-1, j where arr[i] == arr[j].)
            int n = arr.length;
            Map<Integer, List<Integer>> num2Idxes = new HashMap<>();
            for (int i = 0; i < n; i++) {
                List<Integer> idxes = num2Idxes.getOrDefault(arr[i], new ArrayList<>());
                idxes.add(i);
                num2Idxes.put(arr[i], idxes);
            }
            List<Integer>[] g = new List[n];
            Arrays.setAll(g, e -> new ArrayList<>());
            for (int i = 0; i < n; i++) {
                for (Integer idx : num2Idxes.get(arr[i])) {
                    if (idx != i) g[i].add(idx);
                }
                if (i - 1 >= 0) g[i].add(i - 1);
                if (i + 1 < n) g[i].add(i + 1);
            }

            // BFS (Start bfs from node 0 and keep distance. The answer is the distance when you reach node n-1.)
            int steps = 0;
            Set<Integer> visited = new HashSet<>();
            visited.add(0);
            Queue<Integer> q = new ArrayDeque<>();
            q.offer(0);
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int cur = q.poll();
                    if (cur == n - 1) return steps;
                    for (int adj : g[cur]) {
                        if (visited.contains(adj)) continue;
                        q.offer(adj);
                        visited.add(adj);
                    }
                }
                steps++;
            }
            return steps;
        }

    }

}
