package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;
import data_structure_algorithm.domain.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class _109sortedListToBST {

    public static class Solution1 {

        /**
         1.将链表转换为数组，然后再用数组转换为二叉搜索树（类似108）
         时间复杂度：O(n)
         空间复杂度：O(n)
         */
         public TreeNode sortedListToBST(ListNode head) {
             List<Integer> vals = new ArrayList<>();
             ListNode p = head;
             while (p != null) {
                 vals.add(p.val);
                 p = p.next;
             }
             return dfs(vals, 0, vals.size() - 1);
         }

         private TreeNode dfs(List<Integer> vals, int low, int high) {
             if (low > high) return null;
             int mid = low + ((high - low) >> 1);
             TreeNode root = new TreeNode(vals.get(mid));
             root.left = dfs(vals, low, mid - 1);
             root.right = dfs(vals, mid + 1, high);
             return root;
         }

    }



    public static class Solution2 {

        /**
         2.寻找链表中点，然后分治
         时间复杂度：O(nlogn)
         空间复杂度：O(logn)
         */
         public TreeNode sortedListToBST(ListNode head) {
             if (head == null) return null;

             // 快慢指针找中点
             ListNode fast = head, slow = head, prev = null;
             while (fast != null && fast.next != null) {
                 prev = slow;
                 slow = slow.next;
                 fast = fast.next.next;
             }

             TreeNode root = new TreeNode(slow.val);
             if (prev != null) {
                 prev.next = null;
                 root.left = sortedListToBST(head);
             }
             root.right = sortedListToBST(slow.next);
             return root;
         }

    }



    public static class Solution3 {

        /**
         顺序遍历链表，先构建左子树，然后根节点，然后右子树
         时间复杂度：O(n)
         空间复杂度：O(logn)
         */
        public TreeNode sortedListToBST(ListNode head) {
            ListNode p = head;
            int count = 0;
            while (p != null) {
                count++;
                p = p.next;
            }
            this.p = head;
            return dfs(0, count - 1);
        }

        private ListNode p;
        private TreeNode dfs(int low, int high) {
            if (low > high) return null;
            int mid = low + ((high - low) >> 1);

            // 递归构建左子树
            TreeNode left = dfs(low, mid - 1);
            // 构建根节点
            TreeNode root = new TreeNode(p.val);
            // 指针后移一位
            p = p.next;
            root.left = left;
            // 递归构建右子树
            root.right = dfs(mid + 1, high);
            return root;
        }

    }

}
