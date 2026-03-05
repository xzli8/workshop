package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class _114flatten {

    public static class Solution1 {

        /**
         前序遍历 + 构建：O(N), O(N)
         Not work: 仅用val信息，然后重新构建节点，破坏了原有树结构，而且new TreeNode()也是开销。
         */
        public void flatten(TreeNode root) {
            List<Integer> vals = new ArrayList<>();
            preorder(root, vals);
            TreeNode dummy = new TreeNode(), p = dummy;
            for (int val : vals) {
                p.left = new TreeNode(val);
                p = p.left;
            }
            root = dummy.left;
        }

        private void preorder(TreeNode root, List<Integer> vals) {
            if (root == null) return;
            vals.add(root.val);
            preorder(root.left, vals);
            preorder(root.right, vals);
        }

    }


    public static class Solution2 {

        /**
         前序遍历 + 构建：O(N), O(N)
         */
        public void flatten(TreeNode root) {
            List<TreeNode> nodes = new ArrayList<>();
            preorder(root, nodes);
            for (int i = 1; i < nodes.size(); i++) {
                TreeNode prev = nodes.get(i - 1), cur = nodes.get(i);
                prev.left = null;
                prev.right = cur;
            }
        }

        private void preorder(TreeNode root, List<TreeNode> nodes) {
            if (root == null) return;
            nodes.add(root);
            preorder(root.left, nodes);
            preorder(root.right, nodes);
        }

    }


    public static class Solution3 {

        /**
         寻找前驱节点(类似Morris遍历)
         参考题解：https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--26/
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public void flatten(TreeNode root) {
            while (root != null) {
                // 当左子树不为空时
                if (root.left != null) {
                    // 找左子树的最右节点(即中序遍历的前驱节点predecessor)
                    TreeNode node = root.left;
                    while (node.right != null) {
                        node = node.right;
                    }

                    // 将右子树拼到前驱节点(即左子树的最右节点)下
                    node.right = root.right;
                    root.right = root.left;
                    root.left = null;
                }
                root = root.right;
            }
        }

    }

}
