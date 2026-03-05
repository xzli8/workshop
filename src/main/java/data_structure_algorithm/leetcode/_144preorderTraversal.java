package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class _144preorderTraversal {

    public static class Solution1 {

        /**
         递归
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public List<Integer> preorderTraversal(TreeNode root) {
             List<Integer> res = new ArrayList<>();
             traversal(root, res);
             return res;
         }

         private void traversal(TreeNode root, List<Integer> res) {
             if (root == null) return;

             res.add(root.val);
             traversal(root.left, res);
             traversal(root.right, res);
         }

    }



    public static class Solution2 {

        /**
         迭代
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) return res;

            Deque<TreeNode> s = new ArrayDeque<>();
            s.push(root);
            while (!s.isEmpty()) {
                TreeNode cur = s.pop();
                res.add(cur.val);
                if (cur.right != null) s.push(cur.right);
                if (cur.left != null) s.push(cur.left);
            }
            return res;
        }

    }



    public static class Solution3 {

        /**
         Morris遍历: O(N), O(1)
         Note: 先找中序遍历的前驱节点(左子树的最右节点)，然后将其右指针指向当前节点(避免遍历当前节点左子树完成后无法找到当前节点)，然后遍历当前节点左子树(最后遍历到的是前驱节点)
         */
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            TreeNode cur = root;
            while (cur != null) {
                // 有左子树时，找到左子树的最右节点(也就是中序遍历的前驱节点predecessor)
                if (cur.left != null) {
                    // 查找左子树的最右节点
                    TreeNode mostRight = cur.left;
                    while (mostRight.right != null && mostRight.right != cur) {
                        mostRight = mostRight.right;
                    }

                    // 上面的循环停下来，要么mostRight.right == null, 要么mostRight.right == cur
                    // 当mostRight.right == right时，说明是第一次到达到该最右节点，需要将mostRight.right指向cur
                    if (mostRight.right == null) {
                        mostRight.right = cur;
                        res.add(cur.val);
                        cur = cur.left;
                    }
                    // 第二次到达该最右节点，将mostRight.right指向null
                    else {
                        mostRight.right = null;
                        cur = cur.right;
                    }
                }
                // 没有左子树时，考虑右子树
                else {
                    res.add(cur.val);
                    cur = cur.right;
                }
            }
            return res;
        }

    }

}
