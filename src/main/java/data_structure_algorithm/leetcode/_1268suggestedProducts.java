package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class _1268suggestedProducts {

    public static class Solution1 {

        /**
         TrieTree:树的每个节点存以当前路径为前缀，字典序最小的三个单词
         */
        public List<List<String>> suggestedProducts(String[] products, String searchWord) {
            for (String product : products) {
                insert(product);
            }
            startWith(searchWord);
            return res;
        }

        class Node {
            Node[] children;
            PriorityQueue<String> pq;

            public Node() {
                children = new Node[26];
                pq = new PriorityQueue<>((s1, s2) -> s2.compareTo(s1));
            }
        }

        private Node root = new Node();
        private List<List<String>> res = new ArrayList<>();

        private void insert(String word) {
            Node node = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new Node();
                }
                node = node.children[index];
                node.pq.offer(word);
                if (node.pq.size() > 3) {
                    node.pq.poll();
                }
            }
        }

        private void startWith(String word) {
            Node node = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int index = c - 'a';
                if (node.children[index] == null) {
                    for (int j = i; j < word.length(); j++) {
                        res.add(new ArrayList<>());
                    }
                    break;
                }
                node = node.children[index];

                // poll堆顶然后reverse，会破坏树结构
                // List<String> tmp = new ArrayList<>();
                // while (!node.pq.isEmpty()) {
                //     tmp.add(node.pq.poll());
                // }
                // Collections.reverse(tmp);

                // 不poll，拿出元素然后进行排序，不破坏树结构
                List<String> tmp = new ArrayList<>(node.pq);
                Collections.sort(tmp);

                res.add(tmp);
            }
        }


    }

}
