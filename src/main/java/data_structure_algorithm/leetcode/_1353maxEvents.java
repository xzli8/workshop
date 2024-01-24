package data_structure_algorithm.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class _1353maxEvents {

    public static class Solution1 {

        /**
         贪心 + 优先队列
         贪心：优先选择结束时间早的会议参加，因为其他会议还有更多的机会参加
         优先队列：如何动态获取当前结束时间最早的会议？用小根堆记录所有当前可参加会议的结束时间
         ref:https://leetcode.cn/problems/maximum-number-of-events-that-can-be-attended/solutions/98119/sao-miao-suan-fa-tan-xin-by-lucifer1004/
         时间复杂度：O(TlogN)，T为数据范围，N为数据量
         空间复杂度：O(N)
         */
        public int maxEvents(int[][] events) {
            // 按照会议开始时间排序
            Arrays.sort(events, (e1, e2) -> e1[0] - e2[0]);

            // 优先队列：结束时间小的会议排在前面
            PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);

            // 按照时间遍历
            int n = events.length, i = 0, res = 0;
            for (int timestamp = 0; timestamp <= 100000; timestamp++) {
                // 将当前时间点已经开始的会议加入优先队列
                while (i < n && events[i][0] <= timestamp) pq.offer(events[i++]);
                // 将当前时间点已经结束的会议移出优先队列
                while (!pq.isEmpty() && pq.peek()[1] < timestamp) pq.poll();
                // 从剩下的会议中选择一个结束时间最早的会议参加
                if (!pq.isEmpty()) {
                    pq.poll();
                    res++;
                }
            }
            return res;
        }

    }

}
