package data_structure_algorithm.leetcode;

import java.util.PriorityQueue;

public class _1046lastStoneWeight {

    /**
     优先队列
     时间复杂度：O(NlogN)
     空间复杂度：O(N)
     */
    public int lastStoneWeight(int[] stones) {
        // 所有石头加入优先队列
        PriorityQueue<Integer> pq = new PriorityQueue<>((i1, i2) -> i2 - i1);
        for (int stone : stones) {
            pq.offer(stone);
        }

        // 从优先队列中取出前两块石头，碰撞后放入优先队列
        while (!pq.isEmpty() && pq.size() > 1) {
            int remain = pq.poll() - pq.poll();
            if (remain > 0) {
                pq.offer(remain);
            }
        }
        return pq.isEmpty() ? 0 : pq.poll();
    }

}
