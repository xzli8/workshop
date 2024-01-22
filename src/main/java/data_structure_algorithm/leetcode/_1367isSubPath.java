package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;
import data_structure_algorithm.domain.TreeNode;

public class _1367isSubPath {

    public static class Solution1 {

        /**
         DFS(类似题:"572.另一棵树的子树")
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public boolean isSubPath(ListNode head, TreeNode root) {
            if (head == null) return true;
            if (root == null) return false;
            return isPath(head, root) || isSubPath(head, root.right) || isSubPath(head, root.left);
        }

        private boolean isPath(ListNode head, TreeNode root) {
            if (head == null) return true;
            if (root == null) return false;
            if (head.val != root.val) return false;
            return isPath(head.next, root.left) || isPath(head.next, root.right);
        }

        // 错误写法(当head.val != root.val时，跳过了树的这一层，因为此时的head并不是原链表的head，而是上层递归传下来的head)
        // public boolean isSubPath(ListNode head, TreeNode root) {
        //     if (head == null) return true;
        //     if (root == null) return false;
        //     if (head.val == root.val) {
        //         return isSubPath(head.next, root.right) || isSubPath(head.next, root.left);
        //     }
        //     return isSubPath(head, root.right) || isSubPath(head, root.left);
        // }

    }

}
