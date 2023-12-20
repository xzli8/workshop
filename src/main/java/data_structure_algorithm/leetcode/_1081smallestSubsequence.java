package data_structure_algorithm.leetcode;

import java.util.*;

public class _1081smallestSubsequence {

    public static class Solution1 {

        /**
         单调栈
         */
        public String smallestSubsequence(String s) {
            // 统计每个字符出现的次数
            Map<Character, Integer> map = new HashMap<>();
            for (char c : s.toCharArray()) {
                map.put(c, map.getOrDefault(c, 0) + 1);
            }

            // 遍历s, 用单调栈找递增序列，同时用set去重
            Set<Character> set = new HashSet<>();
            Deque<Character> dq = new LinkedList<>();
            for (char c : s.toCharArray()) {
                // set中的字符直接跳过
                if (set.contains(c)) {
                    map.put(c, map.get(c) - 1);
                    continue;
                }
                set.add(c);

                // 单调栈找递增序列
                while (!dq.isEmpty() && map.get(dq.peekLast()) > 1 && c < dq.peekLast()) {
                    char last = dq.pollLast();
                    map.put(last, map.get(last) - 1);
                    set.remove(last);
                }
                dq.offerLast(c);
            }

            // 组装结果并返回
            StringBuilder sb = new StringBuilder();
            while (!dq.isEmpty()) {
                sb.append(dq.pollFirst());
            }
            return sb.toString();
        }

    }

}
