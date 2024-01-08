package data_structure_algorithm.leetcode;

import java.util.*;

public class _127ladderLength {

    public static class Solution1 {

        /**
         BFS
         */
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {

            // 将wordList转换成set，方便后面判断一个单词是否在wordList中
            Set<String> wordSet = new HashSet<>(wordList);
            if (wordSet.size() == 0 || !wordSet.contains(endWord)) {
                return 0;
            }
            wordSet.remove(beginWord);

            // 获取单词长度
            int n = beginWord.length();

            // BFS基本数据结构
            Queue<String> q = new LinkedList<>();
            Set<String> visited = new HashSet<>();

            // 初始化起点
            q.offer(beginWord);
            visited.add(beginWord);
            int step = 1;

            // 开始遍历搜索
            while (!q.isEmpty()) {
                // 处理当前层
                int size = q.size();
                for (int i = 0; i < size; i++) {

                    // 当前字符串
                    String cur = q.poll();

                    // 是否到达终点
                    if (cur.equals(endWord)) {
                        return step;
                    }

                    // 找当前字符串的相邻字符串，相邻字符可以通过替换任意一位字符得到
                    char[] cs = cur.toCharArray();
                    for (int j = 0; j < n; j++) {
                        // 尝试将第j位的字符替换成任意字符
                        char originChar = cs[j];
                        for (char c = 'a'; c <= 'z'; c++) {
                            if (c == originChar) {
                                continue;
                            }

                            // 将第j位字符替换成任意小写字符
                            cs[j] = c;
                            String nextWord = String.valueOf(cs);

                            // 如果替换后的字符在字符集中，并且没有被访问过
                            if (wordSet.contains(nextWord) && !visited.contains(nextWord)) {
                                q.offer(nextWord);
                                visited.add(nextWord);
                            }
                        }
                        cs[j] = originChar;
                    }
                }

                // 遍历完一层后，步数++
                step++;
            }

            // 遍历完了都没找到，返回0
            return 0;
        }


    }

}
