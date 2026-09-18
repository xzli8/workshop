package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

            class Node {
                public char data;
                public Node[] children = new Node[26];
                public boolean isEndingChar = false;

                public Node(char data) {
                    this.data = data;
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

            public boolean search(String word) {
                Node p = root;
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
                Node p = root;
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


    public static class Solution2 {

        // define node
        class TrieNode {
            Map<Character, TrieNode> children;
            boolean isEndOfWord;

            public TrieNode() {
                children = new HashMap<>();
                isEndOfWord = false;
            }
        }

        private TrieNode root;

        public void createTrie(String[] words) {
            root = new TrieNode();
            for (String word : words) {
                insert(word);
            }
        }

        public void insert(String word) {
            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                if (!node.children.containsKey(ch)) {
                    node.children.put(ch, new TrieNode());
                }
                node = node.children.get(ch);
            }
            node.isEndOfWord = true;
        }

        public boolean search(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (!node.children.containsKey(c)) {
                    return false;
                }
                node = node.children.get(c);
            }
            return node.isEndOfWord;
        }

        public boolean startsWith(String prefix) {
            TrieNode node = root;
            for (char c : prefix.toCharArray()) {
                if (!node.children.containsKey(c)) {
                    return false;
                }
                node = node.children.get(c);
            }
            return true;
        }

        // pay attention to this method (ref: https://www.hellointerview.com/learn/code/trie/implement-trie)
        public void delete(String word) {
            dfs(root, word, 0);
        }

        private boolean dfs(TrieNode node, String word, int index) {
            if (index == word.length()) {
                node.isEndOfWord = false;
                return node.children.isEmpty();
            }

            char c = word.charAt(index);
            TrieNode child = node.children.get(c);
            if (child == null) return false;

            boolean shouldDeleteChild = dfs(child, word, index + 1);
            if (shouldDeleteChild) node.children.remove(c);
            return !node.isEndOfWord && node.children.isEmpty();
        }

        // test code
        public Boolean[] trie(String[] initialWords, String[][] commands) {
            createTrie(initialWords);
            List<Boolean> output = new ArrayList<>();
            for (String[] command : commands) {
                if (command[0].equals("search")) {
                    output.add(search(command[1]));
                } else if (command[0].equals("startsWith")) {
                    output.add(startsWith(command[1]));
                } else if (command[0].equals("delete")) {
                    delete(command[1]);
                }
            }
            return output.toArray(new Boolean[0]);
        }

    }



    public static class SolutionPrefixMatching {

        class TrieNode {
            Map<Character, TrieNode> children;
            boolean isEndOfWord;

            public TrieNode() {
                children = new HashMap<>();
                isEndOfWord = false;
            }
        }

        public class Solution {
            private TrieNode root;

            public void createTrie(String[] words) {
                // === DO NOT MODIFY ===
                root = new TrieNode();
                for (String word : words) {
                    insert(word);
                }
            }

            public void insert(String word) {
                // === DO NOT MODIFY ===
                TrieNode node = root;
                for (char ch : word.toCharArray()) {
                    if (!node.children.containsKey(ch)) {
                        node.children.put(ch, new TrieNode());
                    }
                    node = node.children.get(ch);
                }
                node.isEndOfWord = true;
            }

            public List<String> prefix(String word) {
                /**
                 * Return a list of all words in the trie that start with the given prefix.
                 */
                // === YOUR CODE HERE ===
                TrieNode node = root;
                for (char c : word.toCharArray()) {
                    if (!node.children.containsKey(c)) return new ArrayList<>();
                    node = node.children.get(c);
                }

                // DFS
                List<String> res = new ArrayList<>();
                dfs(node, word, res);
                return res;
            }

            private void dfs(TrieNode node, String word, List<String> res) {
                if (node.isEndOfWord) {
                    res.add(word);
                }

                for (Map.Entry<Character, TrieNode> child : node.children.entrySet()) {
                    dfs(child.getValue(), word + child.getKey(), res);
                }
            }

            public List<String> trie(String[] words, String prefix) {
                // === DO NOT MODIFY ===
                createTrie(words);
                return this.prefix(prefix);
            }
        }

    }

}
