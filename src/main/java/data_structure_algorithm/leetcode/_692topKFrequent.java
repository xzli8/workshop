package data_structure_algorithm.leetcode;

import javafx.util.Pair;

import java.util.*;

public class _692topKFrequent {

    public static class Solution1 {

        /**
         哈希表 + (小顶)堆
         时间复杂度：O(NlogK)
         空间复杂度：O(N)
         */
        public List<String> topKFrequent(String[] words, int k) {
            // 遍历数组，用哈希表记录单词及其出现次数
            Map<String, Integer> occurrences = new HashMap<>();
            for (String word : words) {
                occurrences.put(word, occurrences.getOrDefault(word, 0) + 1);
            }

            // 用小顶堆来筛选前k个出现次数最多的单词
            PriorityQueue<Pair<String, Integer>> pq = new PriorityQueue<>((p1, p2) -> {
                // 当出现次数相同时，比较字典顺序
                if (p1.getValue().equals(p2.getValue())) {
                    // 这里字典序要逆序比较，因为最后会反转
                    return p2.getKey().compareTo(p1.getKey());
                }
                return p1.getValue() - p2.getValue();
            });
            for (String str : occurrences.keySet()) {
                Integer count = occurrences.get(str);
                pq.offer(new Pair<>(str, count));
                if (pq.size() > k) {
                    pq.poll();
                }
            }

            // 取出堆中元素，返回最终结果
            List<String> res = new ArrayList<>(k);
            while (!pq.isEmpty()) {
                res.add(pq.poll().getKey());
            }

            // 因为构建的是小根堆，所以从顶部弹出的元素顺序是从小到大的，所以最后我们还需要反转集合
            Collections.reverse(res);
            return res;
        }

    }

}
