package data_structure_algorithm.leetcode;

public class _1650lowestCommonAncestor {

    class Node {

        int val;

        Node left;

        Node right;

        Node parent;
    }


    public static class Solution1 {

        public Node lowestCommonAncestor(Node p, Node q) {
            Node p1 = p, q1 = q;
            while (p1 != q1) {
                p1 = p1.parent != null ? p1.parent : q1;
                q1 = q1.parent != null ? q1.parent : p1;
            }
            return p1;
        }

    }

}
