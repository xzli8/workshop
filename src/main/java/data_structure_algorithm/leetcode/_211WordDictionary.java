package data_structure_algorithm.leetcode;

public class _211WordDictionary {


    /**
     * Your WordDictionary object will be instantiated and called as such:
     * WordDictionary obj = new WordDictionary();
     * obj.addWord(word);
     * boolean param_2 = obj.search(word);
     */

    public static class Solution1 {


        /**
         TriTree + DFS/BFS
         */
        class WordDictionary {

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

            public WordDictionary() {
                root = new Node('/');
            }

            public void addWord(String word) {
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

            public boolean search(String word) {
                return dfs(word, 0, root);
            }

            private boolean dfs(String word, int idx, Node p) {
                if (idx == word.length()) return p.isEndingChar;
                char c = word.charAt(idx);
                if (c != '.') {
                    int index = c - 'a';
                    if (p.children[index] == null) return false;
                    return dfs(word, idx + 1, p.children[index]);
                } else {
                    for (Node child : p.children) {
                        if (child != null && dfs(word, idx + 1, child)) return true;
                    }
                }
                return false;
            }
        }

    }

}
