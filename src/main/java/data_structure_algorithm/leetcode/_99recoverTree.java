package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.*;

public class _99recoverTree {

    /**
     不用实际交换节点，只需要交换节点中的val即可
     */

    public static class Solution1 {

        /**
         中序遍历 + 找逆序对(可能有一个逆序对，也可能有两个逆序对)
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public void recoverTree(TreeNode root) {
             List<TreeNode> inorder = inorder(root);
             TreeNode x = null, y = null;
             for (int i = 1; i < inorder.size(); i++) {
                 if (inorder.get(i).val < inorder.get(i - 1).val) {
                     // 这里不太好理解。可以分为两种情况来考虑：
                     // 第一种是存在一对逆序对：直接交换
                     // 第二种是存在两对逆序对：交换第一个逆序对的前一个元素和第二个逆序对的后一个元素
                     y = inorder.get(i);
                     if (x == null) x = inorder.get(i - 1);
                 }
             }
             if (x != null && y != null) {
                 int tmp = x.val;
                 x.val = y.val;
                 y.val = tmp;
             }
         }

         private List<TreeNode> inorder(TreeNode root) {
             List<TreeNode> res = new ArrayList<>();
             Deque<TreeNode> s = new ArrayDeque<>();
             TreeNode cur = root;
             while (!s.isEmpty() || cur != null) {
                 if (cur != null) {
                     s.push(cur);
                     cur = cur.left;
                 } else {
                     cur = s.pop();
                     res.add(cur);
                     cur = cur.right;
                 }
             }
             return res;
         }

    }



    public static class Solution2 {

        /**
         不用将遍历结果存储在数组中，而是在遍历过程中找逆序对(递归)
         时间复杂度：O(N)
         空间复杂度：O(H)
         */
         public void recoverTree(TreeNode root) {
             dfs(root);
             if (x != null && y != null) {
                 int tmp = x.val;
                 x.val = y.val;
                 y.val = tmp;
             }
         }

         private TreeNode x = null, y = null, prev = null;
         private void dfs(TreeNode cur) {
             if (cur == null) return;
             dfs(cur.left);
             if (prev != null && prev.val > cur.val) {
                 y = cur;
                 if (x == null) x = prev;
             }
             prev = cur;
             dfs(cur.right);
         }

    }



    public static class Solution3 {

        /**
         不用将遍历结果存储在数组中，而是在遍历过程中找逆序对(迭代)
         时间复杂度：O(N)
         空间复杂度：O(H)
         */
         public void recoverTree(TreeNode root) {
             Deque<TreeNode> s = new ArrayDeque<>();
             TreeNode x = null, y = null, prev = null, cur = root;
             while (!s.isEmpty() || cur != null) {
                 if (cur != null) {
                     s.push(cur);
                     cur = cur.left;
                 } else {
                     cur = s.pop();
                     if (prev != null && prev.val > cur.val) {
                         y = cur;
                         if (x == null) x = prev;
                     }
                     prev = cur;
                     cur = cur.right;
                 }
             }

             if (x != null && y != null) {
                 int tmp = x.val;
                 x.val = y.val;
                 y.val = tmp;
             }
         }

    }



    public static class Solution4 {

        /**
         Morris遍历
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public void recoverTree(TreeNode root) {
            TreeNode cur = root, prev = null, x = null, y = null;
            while (cur != null) {
                if (cur.left != null) {
                    TreeNode mostRight = cur.left;
                    while (mostRight.right != null && mostRight.right != cur) {
                        mostRight = mostRight.right;
                    }
                    if (mostRight.right == null) {
                        mostRight.right = cur;
                        cur = cur.left;
                    } else {
                        mostRight.right = null;
                        if (prev != null && prev.val > cur.val) {
                            y = cur;
                            if (x == null) x = prev;
                        }
                        prev = cur;
                        cur = cur.right;
                    }
                } else {
                    if (prev != null && prev.val > cur.val) {
                        y = cur;
                        if (x == null) x = prev;
                    }
                    prev = cur;
                    cur = cur.right;
                }
            }

            if (x != null && y != null) {
                int tmp = x.val;
                x.val = y.val;
                y.val = tmp;
            }
        }

    }



}
