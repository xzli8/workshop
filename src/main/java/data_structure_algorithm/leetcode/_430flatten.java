package data_structure_algorithm.leetcode;

public class _430flatten {

    public static class Solution1 {

        // Definition for a Node.
        class Node {
            public int val;
            public Node prev;
            public Node next;
            public Node child;
        }

        /**
         DFS
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public Node flatten(Node head) {
            if (head == null) return null;
            Node p = head;
            while (p != null) {
                if (p.child == null) p = p.next;
                else {
                    Node next = p.next, nextLevelHead = flatten(p.child);
                    p.next = nextLevelHead;
                    nextLevelHead.prev = p;
                    Node q = nextLevelHead;
                    while (q.next != null) q = q.next;
                    q.next = next;
                    if (next != null) next.prev = q;
                    p.child = null;
                    p = q;
                }
            }
            return head;
        }

    }

}
