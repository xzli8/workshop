package data_structure_algorithm.leetcode;

import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class _1654minimumJumps {

    public static class Solution1 {

        /**
         BFS：注意这里的状态是个二维状态，不仅有坐标，还有向前/向后
         时间复杂度：O(1)
         空间复杂度：O(1)
         */
        public int minimumJumps(int[] forbidden, int a, int b, int x) {
            // 初始化visited数组
            int max = 6000, n = max + 1;
            boolean[][] visited = new boolean[n][2];    // visited[0]表示坐标，visited[1]表示向前跳(0)还是向后跳(1)
            for (int forbid : forbidden) Arrays.fill(visited[forbid], true);

            // BFS
            int step = 0;
            Queue<Pair<Integer, Boolean>> q = new ArrayDeque<>();   // key表示坐标，value表示该点是向前跳(true)还是向后跳(false)得到的
            q.offer(new Pair<>(0, true));
            visited[0][0] = true;
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    Pair<Integer, Boolean> pair = q.poll();
                    int cur = pair.getKey(), forward = cur + a, backward = cur - b;
                    if (cur == x) return step;

                    // 往前跳
                    if (forward <= max && !visited[forward][0]) {
                        visited[forward][0] = true;
                        q.offer(new Pair<>(forward, true));
                    }

                    // 往后跳
                    if (backward >= 0 && !visited[backward][1] && pair.getValue()) {
                        visited[backward][1] = true;
                        q.offer(new Pair<>(backward, false));
                    }
                }
                step++;
            }
            return -1;
        }

    }

}
