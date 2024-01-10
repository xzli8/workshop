package data_structure_algorithm.leetcode;

public class _208Trie {


    /**
     * Your Trie object will be instantiated and called as such:
     * Trie obj = new Trie();
     * obj.insert(word);
     * boolean param_2 = obj.search(word);
     * boolean param_3 = obj.startsWith(prefix);
     */

    public static class Solution1 {

        /**
         前缀树
         */
        class Trie {

            class TrieNode {
                public char data;
                public TrieNode[] children = new TrieNode[26];
                public boolean isEndingChar = false;

                public TrieNode(char data) {
                    this.data = data;
                }
            }

            private TrieNode root;

            public Trie() {
                root = new TrieNode('/');
            }

            public void insert(String word) {
                TrieNode p = root;
                for (char c : word.toCharArray()) {
                    int index = c - 'a';
                    if (p.children[index] == null) {
                        p.children[index] = new TrieNode(c);
                    }
                    p = p.children[index];
                }
                p.isEndingChar = true;
            }

            public boolean search(String word) {
                TrieNode p = root;
                for (char c : word.toCharArray()) {
                    int index = c - 'a';
                    if (p.children[index] == null) {
                        return false;
                    }
                    p = p.children[index];
                }
                return p.isEndingChar;
            }

            public boolean startsWith(String prefix) {
                TrieNode p = root;
                for (char c : prefix.toCharArray()) {
                    int index = c - 'a';
                    if (p.children[index] == null) {
                        return false;
                    }
                    p = p.children[index];
                }
                return true;
            }

        }

    }

}
