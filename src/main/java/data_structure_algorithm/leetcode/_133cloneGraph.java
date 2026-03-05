package data_structure_algorithm.leetcode;

import java.util.*;

public class _133cloneGraph {

    public static class Solution1 {

        // Definition for a Node.
        class Node {
            public int val;
            public List<Node> neighbors;
            public Node() {
                val = 0;
                neighbors = new ArrayList<Node>();
            }
            public Node(int _val) {
                val = _val;
                neighbors = new ArrayList<Node>();
            }
            public Node(int _val, ArrayList<Node> _neighbors) {
                val = _val;
                neighbors = _neighbors;
            }
        }

        /**
         BFS + Hash: O(N), O(N)
         Note: 用BFS遍历图，同时用HashSet记录每个节点，然后再重新构建
         */
        public Node cloneGraph(Node node) {
            // 边界情况
            if (node == null) return null;

            // BFS遍历原图，构建从原节点到新节点的映射
            Map<Node, Node> old2New = new HashMap<>();
            Queue<Node> q = new ArrayDeque<>();
            q.offer(node);
            Set<Node> visited = new HashSet<>();
            visited.add(node);
            while (!q.isEmpty()) {
                Node cur = q.poll();
                old2New.put(cur, new Node(cur.val));
                for (Node neighbor : cur.neighbors) {
                    if (visited.contains(neighbor)) continue;
                    visited.add(neighbor);
                    q.offer(neighbor);
                }
            }

            // BFS遍历原图，从映射构建新图
            visited.clear();
            visited.add(node);
            q.offer(node);
            while (!q.isEmpty()) {
                Node cur = q.poll(), newNode = old2New.get(cur);
                for (Node neighbor : cur.neighbors) {
                    newNode.neighbors.add(old2New.get(neighbor));
                    if (visited.contains(neighbor)) continue;
                    visited.add(neighbor);
                    q.offer(neighbor);
                }
            }
            return old2New.get(node);
        }

    }



    public static class Solution2 {

        // Definition for a Node.
        class Node {
            public int val;
            public List<Node> neighbors;
            public Node() {
                val = 0;
                neighbors = new ArrayList<>();
            }
            public Node(int _val) {
                val = _val;
                neighbors = new ArrayList<>();
            }
            public Node(int _val, ArrayList<Node> _neighbors) {
                val = _val;
                neighbors = _neighbors;
            }
        }

        /**
         DFS + Hash: O(N), O(N)
         Note: 用DFS遍历图，同时用HashMap记录每个旧节点->新节点的映射，然后再重新构建
         */
        public Node cloneGraph(Node node) {
            dfs(node);
            visited.clear();
            return build(node);
        }

        private Set<Node> visited = new HashSet<>();
        private Map<Node, Node> old2New = new HashMap<>();

        private void dfs(Node node) {
            if (node == null || visited.contains(node)) return;
            visited.add(node);
            old2New.put(node, new Node(node.val));
            for (Node neighbor : node.neighbors) dfs(neighbor);
        }

        private Node build(Node node) {
            if (node == null || visited.contains(node)) return null;
            visited.add(node);
            Node newNode = old2New.get(node);
            for (Node neighbor : node.neighbors) {
                newNode.neighbors.add(old2New.get(neighbor));
                build(neighbor);
            }
            return newNode;
        }

    }

}
