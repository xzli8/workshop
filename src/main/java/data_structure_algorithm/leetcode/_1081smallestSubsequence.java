package data_structure_algorithm.leetcode;

import java.util.*;

public class _1081smallestSubsequence {

    /**
     ref:https://leetcode.cn/problems/remove-k-digits/solutions/290203/yi-zhao-chi-bian-li-kou-si-dao-ti-ma-ma-zai-ye-b-5/
     */

    public static class Solution1 {

        /**
         单调栈: O(N), O(N)
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



    public static class Solution2 {

        /**
         单调栈:O(N), O(N)
         */
        public String smallestSubsequence(String s) {
            // 记录s中每个字符最后出现的下标
            int[] idx = new int[26];
            for (int i = 0; i < s.length(); i++) {
                idx[s.charAt(i) - 'a'] = i;
            }

            // 从前往后遍历找逆序对，用哈希表去重
            Set<Character> set = new HashSet<>();
            Deque<Character> stack = new ArrayDeque<>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (set.contains(c)) {
                    continue;
                }

                // 当前字符小于栈顶字符，并且栈顶字符在后面还有出现，就将栈顶字符pop出来
                while (!stack.isEmpty() && c < stack.peek() && idx[stack.peek() - 'a'] > i) {
                    char last = stack.pop();
                    set.remove(last);
                }
                stack.push(c);
                set.add(c);
            }

            // 组装结果返回
            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) {
                sb.append(stack.pop());
            }
            return sb.reverse().toString();
        }

    }



}
