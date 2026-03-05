package data_structure_algorithm.leetcode;

import org.junit.Test;

public class _676MagicDictionary {

    /**
     * Your MagicDictionary object will be instantiated and called as such:
     * MagicDictionary obj = new MagicDictionary();
     * obj.buildDict(dictionary);
     * boolean param_2 = obj.search(searchWord);
     */

    public static class Solution1 {

        /**
         TrieTree + DFS
         */
        class MagicDictionary {

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

                // 根节点
                private Node root;

                // 构造函数
                public Trie() {
                    root = new Node('/');
                }

                // 插入方法
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

                // 搜索方法
                public boolean search(String searchWord) {
                    return dfs(searchWord, 0, 1, root);
                }

                // idx为匹配到的当前字符下标，remain为还剩几次替换机会(这里是1，也可以扩展到更一般的k)，p为当前节点父节点
                private boolean dfs(String searchWord, int idx, int remain, Node p) {
                    if (idx == searchWord.length()) return p.isEndingChar && remain == 0;
                    int index = searchWord.charAt(idx) - 'a';
                    // 当前节点匹配的话，继续匹配下一个字符，remain不变
                    if (p.children[index] != null && dfs(searchWord, idx + 1, remain, p.children[index])) return true;
                    if (remain == 0) return false;
                    // 当前节点不匹配，匹配下一个字符，remain - 1
                    for (int i = 0; i < 26; i++) {
                        if (i != index && p.children[i] != null && dfs(searchWord, idx + 1, remain - 1, p.children[i])) return true;
                    }
                    return false;
                }

            }


            private Trie trie;

            public MagicDictionary() {
                trie = new Trie();
            }

            public void buildDict(String[] dictionary) {
                for (String word : dictionary) {
                    trie.insert(word);
                }
            }

            public boolean search(String searchWord) {
                return trie.search(searchWord);
            }

        }

    }

}
