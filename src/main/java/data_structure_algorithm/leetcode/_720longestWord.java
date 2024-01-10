package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _720longestWord {

    public static class Solution1 {

        /**
         字典树
         */
        public String longestWord(String[] words) {
            // 单词排序：先按照长度，长度相同按照字典序
            Arrays.sort(words, (w1, w2) -> {
                if (w1.length() == w2.length()) return w1.compareTo(w2);
                return w1.length() - w2.length();
            });

            // 用长度最小的单词初始化字典树
            Trie trie = new Trie();
            trie.init(words[0]);
            int n = words.length, i = 1;
            while (i < n) {
                if (words[i].length() > words[i - 1].length()) break;
                trie.init(words[i]);
                i++;
            }

            // 遍历后续单词，继续构建字典树
            String res = words[0].length() == 1 ? words[0] : "";
            int curLen = words[0].length();
            while (i < n) {
                // 断层了，直接返回
                if (words[i].length() - curLen > 1) break;

                // 如果能匹配成功，并且是第一次匹配成功的，就更新结果
                if (trie.insert(words[i]) && words[i].length() > curLen) {
                    curLen = words[i].length();
                    res = words[i];
                }
                i++;
            }
            return res;
        }

        class Trie {

            class Node {
                char val;
                Node[] children;
                boolean isEndingChar;

                public Node(char val) {
                    this.val = val;
                    this.children = new Node[26];
                    this.isEndingChar = false;
                }
            }

            private Node root = new Node('/');

            public void init(String word) {
                Node p = root;
                for (char c : word.toCharArray()) {
                    int index = c - 'a';
                    if (p.children[index] == null) {
                        p.children[index] = new Node(c);
                    }
                    p = p.children[index];
                }
                p.isEndingChar = true;
            }

            public boolean insert(String word) {
                Node p = root;
                int n = word.length();
                for (int i = 0; i < n - 1; i++) {
                    int index = word.charAt(i) - 'a';
                    Node child = p.children[index];
                    if (child == null || !child.isEndingChar) return false;
                    p = child;
                }

                char c = word.charAt(n - 1);
                int index = c - 'a';
                if (p.children[index] == null) p.children[index] = new Node(c);
                p = p.children[index];
                p.isEndingChar = true;
                return true;
            }
        }

    }

}
