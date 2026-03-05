package data_structure_algorithm.leetcode;

public class _1650lowestCommonAncestor {

    /**
     * ref: https://leetcode.doocs.org/lc/1650/
     */

    class Node {

        int val;

        Node left;

        Node right;

        Node parent;
    }


    public static class Solution1 {

        /**
         * 双指针: O(N), O(N)
         * Note: 因为有parent节点，所以直接从p, q开始往上遍历找LCA就行。(与"160.相交链表"类似)
         */
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
