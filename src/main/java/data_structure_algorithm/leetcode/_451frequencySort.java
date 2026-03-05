package data_structure_algorithm.leetcode;

import java.util.*;

public class _451frequencySort {

    public static class Solution1 {

        /**
         * 哈希表+排序: O(NlogN), O(N)
         */
        public String frequencySort(String s) {
            // 哈希表统计次数
            Map<Character, Integer> cnt = new HashMap<>(52);
            for (int i = 0; i < s.length(); ++i) {
                cnt.merge(s.charAt(i), 1, Integer::sum);
            }

            // 按照出现次数排序
            List<Character> cs = new ArrayList<>(cnt.keySet());
            cs.sort((a, b) -> cnt.get(b) - cnt.get(a));

            // 组装结果返回
            StringBuilder ans = new StringBuilder();
            for (char c : cs) {
                for (int v = cnt.get(c); v > 0; --v) {
                    ans.append(c);
                }
            }
            return ans.toString();
        }

    }

    public static class Solution2 {

        /**
         哈希表 + 排序
         时间复杂度：O(NlogN)
         空间复杂度：O(N)
         */
        public String frequencySort(String s) {
            // 遍历字符串，用哈希表统计每个字符出现的次数
            Map<Character, Integer> occurrences = new HashMap<>();
            for (char c : s.toCharArray()) {
                occurrences.put(c, occurrences.getOrDefault(c, 0) + 1);
            }

            // 对Map.Entry排序
            List<Map.Entry<Character, Integer>> list = new ArrayList<>(occurrences.entrySet());
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
