package data_structure_algorithm.leetcode;

import java.util.*;

public class _127ladderLength {

    public static class Solution1 {

        /**
         BFS
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            // 转换成set，方便后续判断
            Set<String> dict = new HashSet<>(wordList);
            if (!dict.contains(endWord)) return 0;

            // 初始化
            int n = beginWord.length();
            Set<String> visited = new HashSet<>();
            visited.add(beginWord);
            Queue<String> q = new LinkedList<>();
            q.offer(beginWord);
            int step = 0;

            // 遍历
            while (!q.isEmpty()) {
                step++;
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    String cur = q.poll();
                    if (cur.equals(endWord)) return step;

                    // 遍历每一位，尝试进行替换
                    char[] cs = cur.toCharArray();
                    for (int j = 0; j < n; j++) {
                        char originChar = cs[j];
                        for (char c = 'a'; c <= 'z'; c++) {
                            if (c == originChar) continue;
                            cs[j] = c;
                            String next = String.valueOf(cs);
                            if (dict.contains(next) && !visited.contains(next)) {
                                visited.add(next);
                                q.offer(next);
                            }
                        }
                        cs[j] = originChar;
                    }
                }
            }
            return 0;
        }

    }

}
