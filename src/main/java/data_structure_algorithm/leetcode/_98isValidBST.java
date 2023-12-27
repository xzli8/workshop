package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class _98isValidBST {

    public static class Solution1 {

        /**
         递归：中序遍历得到序列后，判断是否为升序序列
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public boolean isValidBST(TreeNode root) {
             List<Integer> res = new ArrayList<>();
             dfs(root, res);
             for (int i = 1; i < res.size(); i++) {
                 if (res.get(i-1) >= res.get(i)) return false;
             }
             return true;
         }

         private void dfs(TreeNode node, List<Integer> res) {
             if (null == node) return;
             dfs(node.left, res);
             res.add(node.val);
             dfs(node.right, res);
         }


    }



    public static class Solution2 {

        /**
         迭代：中序遍历得到序列后，判断是否为升序序列
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public boolean isValidBST(TreeNode root) {
             List<Integer> res = new ArrayList<>();
             Deque<TreeNode> s = new ArrayDeque<>();
             while(null != root || !s.isEmpty()) {
                 if (null != root) {
                     s.push(root);
                     root = root.left;
                 } else {
                     root = s.pop();
                     res.add(root.val);
                     root = root.right;
                 }
             }

             for (int i = 1; i < res.size(); i++) {
                 if (res.get(i-1) >= res.get(i)) return false;
             }
             return true;
         }

    }



    public static class Solution3 {

        /**
         递归：遍历过程中，比较当前节点的值与其中序遍历结果中前一个节点的值，如果小于，则不符合
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public boolean isValidBST(TreeNode root) {
             dfs(root);
             return valid;
         }

         private TreeNode pre = null;
         private boolean valid = true;
         private void dfs(TreeNode cur) {
             if (!valid || cur == null) return;
             dfs(cur.left);
             if (pre != null) {
                 if (cur.val <= pre.val) {
                     valid = false;
                     return;
                 }
             }
             pre = cur;
             dfs(cur.right);
         }

    }



    public static class Solution4 {

        /**
         迭代：遍历过程中，比较当前节点的值与其中序遍历结果中前一个节点的值，如果小于，则不符合
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public boolean isValidBST(TreeNode root) {
            long pre = Long.MIN_VALUE;
            Deque<TreeNode> s = new ArrayDeque<>();
            while (null != root || !s.isEmpty()) {
                if (null != root) {
                    s.push(root);
                    root = root.left;
                } else {
                    root = s.pop();
                    if (root.val <= pre) return false;
                    pre = root.val;
                    root = root.right;
                }
            }
            return true;
        }

    }



    public static class Solution5 {

        /**
         递归：遍历过程中，判断当前节点的上下限
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public boolean isValidBST(TreeNode root) {
            return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
        }

        private boolean dfs(TreeNode node, long lower, long upper) {
            if (null == node) return true;
            if (node.val <= lower || node.val >= upper) return false;
            return dfs(node.left, lower, node.val) && dfs(node.right, node.val, upper);
        }

    }



}
