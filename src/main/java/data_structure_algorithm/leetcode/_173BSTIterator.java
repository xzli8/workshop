package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class _173BSTIterator {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */

    /**
     * Your BSTIterator object will be instantiated and called as such:
     * BSTIterator obj = new BSTIterator(root);
     * int param_1 = obj.next();
     * boolean param_2 = obj.hasNext();
     */

    public static class Solution1 {

        /**
         * 递归：构建时中序遍历得到有序数组，然后在有序数组中查询
         * 时间复杂度：构建O(N)，查询O(1)
         * 空间复杂度：O(N)
         */
        class BSTIterator {

            private int idx = 0;

            private final List<Integer> res = new ArrayList<>();

            private void dfs(TreeNode cur) {
                if (cur == null) return;
                dfs(cur.left);
                res.add(cur.val);
                dfs(cur.right);
            }

            public BSTIterator(TreeNode root) {
                dfs(root);
            }

            public int next() {
                return res.get(idx++);
            }

            public boolean hasNext() {
                return idx < res.size();
            }

        }


    }


    public static class Solution2 {


        /**
         迭代：利用栈模拟递归过程，实际上是二叉树中序遍历的迭代实现
         参考题解：https://leetcode.cn/problems/binary-search-tree-iterator/solutions/684560/fu-xue-ming-zhu-dan-diao-zhan-die-dai-la-dkrm/
         时间复杂度：构建O(h)，查询O(1)
         空间复杂度：O(h)
         */
        class BSTIterator {

            private final Deque<TreeNode> s = new ArrayDeque<>();

            public BSTIterator(TreeNode root) {
                while (root != null) {
                    s.push(root);
                    root = root.left;
                }
            }

            public int next() {
                TreeNode cur = s.pop();
                int val = cur.val;
                cur = cur.right;
                while (cur != null) {
                    s.push(cur);
                    cur = cur.left;
                }
                return val;
            }

            public boolean hasNext() {
                return !s.isEmpty();
            }

        }

    }


}
