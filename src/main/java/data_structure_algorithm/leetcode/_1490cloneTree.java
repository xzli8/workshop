package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _1490cloneTree {

    /**
     * Ref: https://leetcode.doocs.org/lc/1490/
     */

    public static class Solution1 {

        /**
         * DFS: O(N), O(N)
         * Note: 对于当前节点，如果为空，则返回空；否则，创建一个新节点，其值为当前节点的值，然后对当前节点的每个子节点递归调用该函数，将返回值作为新节点的子节点。最后返回新节点即可。
         */
        public Node cloneTree(Node root) {
            if (root == null) {
                return null;
            }
            ArrayList<Node> children = new ArrayList<>();
            for (Node child : root.children) {
                children.add(cloneTree(child));
            }
            return new Node(root.val, children);
        }

        // Definition for a Node.
        class Node {
            public int val;
            public List<Node> children;

            public Node() {
                children = new ArrayList<Node>();
            }

            public Node(int _val) {
                val = _val;
                children = new ArrayList<Node>();
            }

            public Node(int _val,ArrayList<Node> _children) {
                val = _val;
                children = _children;
            }
        };

    }
}
