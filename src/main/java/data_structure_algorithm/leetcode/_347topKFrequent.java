package data_structure_algorithm.leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class _347topKFrequent {

    public static class Solution1 {

        /**
         哈希表 + (小顶)堆
         思路：遍历数组，用哈希表记录每个元素及其出现次数，用堆来取出哈希表中出现次数最多的K的元素
         时间复杂度：O(NlogK)
         空间复杂度：O(N)
         */
        public int[] topKFrequent(int[] nums, int k) {
            // 遍历数组，用哈希表记录每个元素的出现次数
            Map<Integer, Integer> occurrences = new HashMap<>();
            for (int num : nums) {
                occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
            }

            // int[]的第一个元素代表元素值，第二个元素代表元素的出现次数
            PriorityQueue<int[]> pq = new PriorityQueue<>((m, n) -> m[1] - n[1]);
            for (Integer num : occurrences.keySet()) {
                pq.offer(new int[] {num, occurrences.get(num)});
                if (pq.size() > k) {
                    pq.poll();
                }
            }

            // 取出堆中元素，返回最终结果
            int[] res = new int[k];
            for (int i = 0; i < k; i++) {
                res[i] = pq.poll()[0];
            }
            return res;
        }

    }

}
