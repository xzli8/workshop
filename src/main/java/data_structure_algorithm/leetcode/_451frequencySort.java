package data_structure_algorithm.leetcode;

import java.util.*;

public class _451frequencySort {

    public static class Solution1 {

        /**
         哈希表 + 排序
         时间复杂度：O(NlogN)
         空间复杂度：O(N)
         */
        public String frequencySort(String s) {
            // 遍历字符串，用哈希表统计每个字符出现的次数
            Map<Character, Integer> occurences = new HashMap<>();
            for (char c : s.toCharArray()) {
                occurences.put(c, occurences.getOrDefault(c, 0) + 1);
            }

            // 对Map.Entry排序
            List<Map.Entry<Character, Integer>> list = new ArrayList<>(occurences.entrySet());
            list.sort(Comparator.comparingInt(Map.Entry::getValue));
            Collections.reverse(list);

            // 组装结果返回
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<Character, Integer> entry : list) {
                for (int i = 0; i < entry.getValue(); i++) {
                    sb.append(entry.getKey());
                }
            }
            return sb.toString();
        }

    }


}
