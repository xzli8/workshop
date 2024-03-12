package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.HashSet;
import java.util.Set;

public class _1261FindElements {

    public static class Solution1 {

        public class FindElements {

            /**
             DFS
             时间复杂度：构建O(N)，查找O(N)
             空间复杂度：O(1)，如果不计算递归栈开销的话
             */
            private TreeNode root;

            public FindElements(TreeNode root) {
                this.root = root;
                if (root == null) return;
                root.val = 0;
                recover(root);
            }

            private void recover(TreeNode cur) {
                if (cur.left != null) {
                    cur.left.val = cur.val * 2 + 1;
                    recover(cur.left);
                }
                if (cur.right != null) {
                    cur.right.val = cur.val * 2 + 2;
                    recover(cur.right);
                }
            }

            public boolean find(int target) {
                return doFind(root, target);
            }

            private boolean doFind(TreeNode root, int target) {
                if (root == null) return false;
                if (root.val == target) return true;
                return doFind(root.left, target) || doFind(root.right, target);
            }

        }

    }



    public static class Solution2 {

        public class FindElements {

            /**
             DFS + 哈希表
             时间复杂度：构建O(N)，查找O(1)
             空间复杂度：O(N)
             */
            private Set<Integer> vals = new HashSet<>();

            public FindElements(TreeNode root) {
                dfs(root, 0);
            }

            private void dfs(TreeNode node, int val) {
                if (node == null) return;
                node.val = val;
                vals.add(val);
                dfs(node.left, val * 2 + 1);
                dfs(node.right, val * 2 + 2);
            }

            public boolean find(int target) {
                return vals.contains(target);
            }

        }

    }

}
