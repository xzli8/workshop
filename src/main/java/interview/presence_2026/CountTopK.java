package interview.presence_2026;

import java.util.*;

public class CountTopK {


    public static List<Map.Entry<String, Integer>> topKFrequent(String text, int k) {
        // 1. 分割单词
        String[] words = text.split(" ");

        // 2. 统计词频
        Map<String, Integer> countMap = new HashMap<>();
        for (String word : words) {
            countMap.put(word, countMap.getOrDefault(word, 0) + 1);
        }

        // 3. 小顶堆：保留频率最高的 K 个
        PriorityQueue<Map.Entry<String, Integer>> minHeap =
                new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));

        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // 4. 结果逆序输出（从大到小）
        List<Map.Entry<String, Integer>> res = new ArrayList<>(minHeap);
        Collections.reverse(res);

        return res;
    }

    public static void main(String[] args) {
        String text = "the cat sat on the mat the cat";
        int k = 2;

        List<Map.Entry<String, Integer>> result = topKFrequent(text, k);

        // 格式化输出
        System.out.println("输出：");
        for (Map.Entry<String, Integer> entry : result) {
            System.out.println("(" + entry.getKey() + ", " + entry.getValue() + ")");
        }
    }

}
