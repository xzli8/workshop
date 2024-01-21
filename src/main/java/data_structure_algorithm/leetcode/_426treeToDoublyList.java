package data_structure_algorithm.leetcode;

public class _426treeToDoublyList {

    public static class Solution1 {

        // Definition for a Node.
        class Node {
            public int val;
            public Node left;
            public Node right;

            public Node() {}

            public Node(int _val) {
                val = _val;
            }

            public Node(int _val,Node _left,Node _right) {
                val = _val;
                left = _left;
                right = _right;
            }
        }

        /**
         中序遍历(左根右)
         时间复杂度：O(N)
         空间复杂度：和递归的深度有关，即和BST的平衡性有关，最好：O(logN)，平均：O(logN)，最坏O(N)
         */
        public Node treeToDoublyList(Node root) {
            if (root == null) return null;
            dfs(root);
            head.left = pre;
            pre.right = head;
            return head;
        }

        private Node pre = null, head = null;
        private void dfs(Node cur) {
            if (cur == null) return;
            dfs(cur.left);
            if (pre == null) head = cur;
            else pre.right = cur;
            cur.left = pre;
            pre = cur;
            dfs(cur.right);
        }

    }

}
