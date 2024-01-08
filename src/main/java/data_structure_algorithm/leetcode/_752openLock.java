package data_structure_algorithm.leetcode;

import java.util.*;

public class _752openLock {

    public static class Solution1 {

        /**
         BFS：每个位置的数字有向上和向下两种选择，从起点开始枚举每一步的结果，避开死锁和访问过的节点
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int openLock(String[] deadends, String target) {

            // 记录死亡密码
            Set<String> deads = new HashSet<>(Arrays.asList(deadends));

            // BFS基本数据结构-队列
            Queue<String> q = new ArrayDeque<>();

            // 记录访问过的节点
            Set<String> visited = new HashSet<>();

            // 初始化起点
            q.offer("0000");
            visited.add("0000");
            int step = 0;

            // 开始BFS
            while (!q.isEmpty()) {
                int size = q.size();

                // 遍历某一层的所有节点
                for (int i = 0; i < size; i++) {

                    // 判断当前节点是否到达终点
                    String cur = q.poll();
                    if (deads.contains(cur)) {
                        continue;
                    }
                    if (cur.equals(target)) {
                        return step;
                    }

                    // 将当前节点的相邻节点加入队列
                    for (int j = 0; j < 4; j++) {
                        String up = up(cur, j);
                        if (!visited.contains(up)) {
                            q.offer(up);
                            visited.add(up);
                        }

                        String down = down(cur, j);
                        if (!visited.contains(down)) {
                            q.offer(down);
                            visited.add(down);
                        }
                    }
                }

                // 遍历完一层后，步数+1
                step++;
            }
            return -1;
        }

        // 第j位向上转
        private String up(String s, int j) {
            char[] cs = s.toCharArray();
            if (cs[j] == '9') {
                cs[j] = '0';
            } else {
                cs[j] += 1;
            }
            return new String(cs);
        }

        // 第j位向下转
        private String down(String s, int j) {
            char[] cs = s.toCharArray();
            if (cs[j] == '0') {
                cs[j] = '9';
            } else {
                cs[j] -= 1;
            }
            return new String(cs);
        }


    }

}
