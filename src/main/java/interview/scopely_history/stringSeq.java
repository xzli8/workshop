package interview.scopely_history;

import java.util.*;

public class stringSeq {

    public static class Solution1 {


        /**
         * String compare
         */
        public int compare(String a, String b) {
            int i = 0, n = a.length(), m = b.length();
            while (i < n && i < m) {
                if (a.charAt(i) != b.charAt(i)) {
                    return a.charAt(i) < b.charAt(i) ? -1 : 1;   // 规则1: 第一个不同处决定
                }
                i++;
            }
            return Integer.compare(n, m);   // 规则2: 前缀都相同 → 短的更小
        }

        /**
         * String compare with given dict: leetcode.953
         */
        public boolean isAlienSorted(String[] words, String order) {
            int[] rank = new int[26];
            for (int i = 0; i < order.length(); i++) {
                rank[order.charAt(i) - 'a'] = i;        // 字母 → 排名
            }
            for (int k = 0; k + 1 < words.length; k++) {
                if (compare(words[k], words[k + 1], rank) > 0) return false;  // 有一对乱序就 false
            }
            return true;
        }

        // 就是题1, 把"比字符"换成"比排名"
        private int compare(String a, String b, int[] rank) {
            int i = 0, n = a.length(), m = b.length();
            while (i < n && i < m) {
                int ca = rank[a.charAt(i) - 'a'], cb = rank[b.charAt(i) - 'a'];
                if (ca != cb) return ca < cb ? -1 : 1;
                i++;
            }
            return Integer.compare(n, m);   // 前缀边界, 和题1完全一样
        }

        /**
         * Reverse comparing
         *
         * Note: 相邻两个单词，第一个不同的字符告诉我们一条线索：a 的那个字符 排在 b 的那个字符 前面。
         * 把每条线索当成一条有向边，所有边建成图，再用拓扑排序把字母排成一条合法的线。
         */
        public String alienOrder(String[] words) {
            // 1. 收集所有出现过的字母 (孤立字母也要进结果)
            Map<Character, Set<Character>> graph = new HashMap<>();
            Map<Character, Integer> indeg = new HashMap<>();
            for (String w : words)
                for (char c : w.toCharArray()) {
                    graph.putIfAbsent(c, new HashSet<>());
                    indeg.putIfAbsent(c, 0);
                }

            // 2. 相邻单词比较, 提取边
            for (int i = 0; i + 1 < words.length; i++) {
                String a = words[i], b = words[i + 1];
                int len = Math.min(a.length(), b.length()), j = 0;
                while (j < len && a.charAt(j) == b.charAt(j)) j++;
                // 找到第一个不同字符: 只有第一个不同字符有用，因为单词已排好序，它们的先后只由第一个差异决定，后面的字符给不了任何顺序信息
                if (j < len) {
                    char from = a.charAt(j), to = b.charAt(j);
                    if (graph.get(from).add(to)) {
                        // 可能从多对单词重复提取，若不去重，入度会被多加，拓扑排序就永远完不成。graph.get(from).add(to) 返回 true 才加入度，正好去重
                        indeg.put(to, indeg.get(to) + 1);
                    }
                } else if (a.length() > b.length()) {   // 前缀非法: "abc" 排在 "ab" 前
                    return "";
                }
            }

            // 3. 拓扑排序 (Kahn / 入度 BFS)
            Queue<Character> q = new ArrayDeque<>();
            for (Map.Entry<Character, Integer> e : indeg.entrySet()) {
                if (e.getValue() == 0) q.add(e.getKey());   // 入度为0的节点可以作为source
            }
            StringBuilder sb = new StringBuilder();
            while (!q.isEmpty()) {
                char c = q.poll();
                sb.append(c);
                for (char nxt : graph.get(c)) {
                    indeg.put(nxt, indeg.get(nxt) - 1);
                    if (indeg.get(nxt) == 0) q.add(nxt);
                }
            }

            // 4. 没排完 = 有环 = 矛盾, 无解
            return sb.length() == indeg.size() ? sb.toString() : "";
        }

    }

}
