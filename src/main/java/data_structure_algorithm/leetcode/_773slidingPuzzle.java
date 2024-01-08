package data_structure_algorithm.leetcode;

import java.util.*;

public class _773slidingPuzzle {

    public static class Solution1 {

        /**
         BFS
         */
        public int slidingPuzzle(int[][] board) {

            // 将board转换成字符串作为搜索的起点
            StringBuilder sb = new StringBuilder();
            int m = 2, n = 3;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    sb.append(board[i][j]);
                }
            }
            String start = sb.toString();

            // 确定终点
            String end = "123450";

            // 计算二维数组转换为一维数组后的相邻节点
            int[][] neighbors = neighbors(m, n);

            // BFS基本数据结构：队列和集合
            Queue<String> q = new ArrayDeque<>();
            Set<String> visited = new HashSet<>();

            // 初始化起点
            q.offer(start);
            visited.add(start);
            int step = 0;

            // 开始搜索
            while (!q.isEmpty()) {
                // 遍历每一层
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    String cur = q.poll();

                    // 到达终点，返回步数
                    if (cur.equals(end)) {
                        return step;
                    }

                    // 找到数字0的索引位置
                    int idx = 0;
                    while (cur.charAt(idx) != '0') {
                        idx++;
                    }

                    // 将数字0和相邻数字交换位置
                    for (int adj : neighbors[idx]) {
                        String newBoard = swap(cur.toCharArray(), idx, adj);
                        if (!visited.contains(newBoard)) {
                            q.offer(newBoard);
                            visited.add(newBoard);
                        }
                    }
                }

                // 步数++
                step++;
            }

            // 没找到目标解
            return -1;
        }

        // 交换下标位置
        private String swap(char[] cs, int i, int j) {
            char tmp = cs[i];
            cs[i] = cs[j];
            cs[j] = tmp;
            return new String(cs);
        }

        // 计算m * n的二维数组每个节点相邻节点在一维情况下的下标
        private int[][] neighbors(int m, int n) {
            // neighbors[i]表示第i个节点的相邻节点下标有哪些
            int[][] neighbors = new int[m * n][];

            // 遍历
            for (int i = 0; i < m * n; i++) {
                List<Integer> neighbor = new ArrayList<>();

                // 不是第一列，有左侧邻居
                if (i % n != 0) neighbor.add(i - 1);

                // 不是最后一列，有右侧邻居
                if (i % n != n - 1) neighbor.add(i + 1);

                // 不是第一行，有上方邻居
                if (i / n != 0) neighbor.add(i - n);

                // 不是最后一行，有下方邻居
                if (i / n != m - 1) neighbor.add(i + n);

                neighbors[i] = neighbor.stream().mapToInt(Integer::intValue).toArray();
            }
            return neighbors;
        }


    }

}
