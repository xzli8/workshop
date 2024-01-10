package data_structure_algorithm.leetcode;

import java.util.List;

public class _648replaceWords {

    public static class Solution1 {

        /**
         TrieTree(相似题："676.实现一个魔法字典")
         时间复杂度：O(N + M)
         空间复杂度：O(N + M)
         */
        public String replaceWords(List<String> dictionary, String sentence) {
            // 用字典中的单词构建TrieTree
            Trie trie = new Trie();
            for (String word : dictionary) {
                trie.insert(word);
            }

            // 遍历句子中的单词，看能否用词根替换
            String[] strs = sentence.split(" ");
            for (int i = 0; i < strs.length; i++) {
                String res = trie.search(strs[i]);
                if (res == null) continue;
                strs[i] = res;
            }
            return String.join(" ", strs);
        }

        class Trie {

            // 节点定义
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

            private Node root;

            public Trie() {
                root = new Node('/');
            }

            public void insert(String word) {
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

            public String search(String word) {
                Node p = root;
                StringBuilder sb = new StringBuilder();
                for (char c : word.toCharArray()) {
                    int index = c - 'a';
                    if (p.children[index] == null) return null;
                    p = p.children[index];
                    sb.append(p.val);
                    if (p.isEndingChar) return sb.toString();
                }
                return null;
            }

        }


    }

}
