package data_structure_algorithm.leetcode;

import java.util.*;

public class _316removeDuplicateLetters {

    public static class Solution1 {

        /**
         单调栈
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public String removeDuplicateLetters(String s) {
            // 统计每个字符出现的次数
            Map<Character, Integer> map = new HashMap<>();
            for (char c : s.toCharArray()) {
                map.put(c, map.getOrDefault(c, 0) + 1);
            }

            // 遍历s，用单调(递增)栈找最小字典序，结合set去重
            Deque<Character> dq = new ArrayDeque<>();
            Set<Character> set = new HashSet<>();
            for (char c : s.toCharArray()) {
                // 用set去重
                if (set.contains(c)) {
                    map.put(c, map.get(c) - 1);
                    continue;
                }
                set.add(c);

                // 用单调栈找递增序列
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
