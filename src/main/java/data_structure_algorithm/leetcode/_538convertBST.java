package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class _538convertBST {

    public static class Solution1 {

        /**
         递归: O(N), O(N)
         Note: 遍历顺序改为"右根左"，这样就能从小到大遍历节点，然后累加赋值即可
         */
         public TreeNode convertBST(TreeNode root) {
             dfs(root);
             return root;
         }

         private int sum = 0;
         private void dfs(TreeNode cur) {
             if (cur == null) return;
             dfs(cur.right);
             sum += cur.val;
             cur.val = sum;
             dfs(cur.left);
         }

    }


    public static class Solution2 {

        /**
         迭代：O(N), O(N)
         Note: 遍历顺序改为"右根左"，这样就能从小到大遍历节点，然后累加赋值即可
         */
        public TreeNode convertBST(TreeNode root) {
            Deque<TreeNode> s = new ArrayDeque<>();
            TreeNode cur = root;
            int sum = 0;
            while (cur != null || !s.isEmpty()) {
                if (cur != null) {
                    s.push(cur);
                    cur = cur.right;
                } else {
                    cur = s.pop();
                    sum += cur.val;
                    cur.val = sum;
                    cur = cur.left;
                }
            }
            return root;
        }

    }


    public static class Solution3 {

        /**
         * Morris遍历: O(N), O(1)
         * Note: 中序遍历(左根右)的前驱节点是left.mostRight，这里需要的遍历顺序是(右根左)，所以前驱节点是right.mostLeft
         */
        public TreeNode convertBST(TreeNode root) {
            int s = 0;
            TreeNode node = root;
            while (root != null) {
                if (root.right == null) {
                    s += root.val;
                    root.val = s;
                    root = root.left;
                } else {
                    // 中序遍历(左根右)的前驱节点是left.mostRight，这里需要的遍历顺序是(右根左)，所以前驱节点是right.mostLeft
                    TreeNode next = root.right;
                    while (next.left != null && next.left != root) {
                        next = next.left;
                    }
                    if (next.left == null) {
                        next.left = root;
                        root = root.right;
                    } else {
                        s += root.val;
                        root.val = s;
                        next.left = null;
                        root = root.left;
                    }
                }
            }
            return node;
        }

    }

}
