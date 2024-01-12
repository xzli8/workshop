package data_structure_algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class _2085countWords {

    public static class Solution1 {

        /**
         哈希计数
         时间复杂度：O(M + N)
         空间复杂度：O(M + N)
         */
        public int countWords(String[] words1, String[] words2) {
            Map<String, Integer> counts1 = countWords(words1), counts2 = countWords(words2);
            int count = 0;
            for (String word : counts1.keySet()) {
                if (counts1.get(word) == 1 && counts2.getOrDefault(word, 0) == 1) count++;
            }
            return count;
        }

        private Map<String, Integer> countWords(String[] words) {
            Map<String, Integer> counts = new HashMap<>();
            for (String word : words) {
                counts.put(word, counts.getOrDefault(word, 0) + 1);
            }
            return counts;
        }

    }

}
