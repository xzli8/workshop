package data_structure_algorithm.leetcode;

import java.util.*;

public class _433minMutation {

    public static class Solution1 {

        /**
         BFS
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int minMutation(String startGene, String endGene, String[] bank) {
            // 转换为set便于后续判断
            Set<String> genes = new HashSet<>(Arrays.asList(bank));
            if (!genes.contains(endGene)) return -1;

            // 初始化
            int n = startGene.length();
            char[] dirs = new char[] {'A', 'C', 'G', 'T'};
            Set<String> visited = new HashSet<>();
            visited.add(startGene);
            Queue<String> q = new ArrayDeque<>();
            q.offer(startGene);
            int step = 0;

            // 遍历
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    String cur = q.poll();
                    if (cur.equals(endGene)) return step;

                    // 替换任意位置
                    char[] cs = cur.toCharArray();
                    for (int j = 0; j < n; j++) {
                        char c = cs[j];
                        for (char dir : dirs) {
                            if (dir == c) continue;
                            cs[j] = dir;
                            String next = String.valueOf(cs);
                            if (genes.contains(next) && !visited.contains(next)) {
                                visited.add(next);
                                q.offer(next);
                            }
                        }
                        cs[j] = c;
                    }
                }
                step++;
            }
            return -1;
        }


    }

}
