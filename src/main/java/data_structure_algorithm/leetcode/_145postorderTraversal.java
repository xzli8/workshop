package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.*;

public class _145postorderTraversal {

    public static class Solution1 {

        /**
         递归
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public List<Integer> postorderTraversal(TreeNode root) {
             List<Integer> res = new ArrayList<>();
             traversal(root, res);
             return res;
         }

         private void traversal(TreeNode root, List<Integer> res) {
             if (root == null) return;

             traversal(root.left, res);
             traversal(root.right, res);
             res.add(root.val);
         }

    }



    public static class Solution2 {

        /**
         迭代
         思路：前序"根左右"，后续"左右根"，在前序遍历过程中交换左右子节点的处理顺序，最后将结果反转
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) return res;

            Deque<TreeNode> s = new ArrayDeque<>();
            s.push(root);
            while (!s.isEmpty()) {
                TreeNode cur = s.pop();
                res.add(cur.val);
                if (cur.left != null) s.push(cur.left);
                if (cur.right != null) s.push(cur.right);
            }
            Collections.reverse(res);
            return res;
        }

    }



    public static class Solution3 {

        /**
         Morris遍历: O(N), O(1)
         Note: 先找中序遍历的前驱节点(左子树的最右节点)，然后将其右指针指向当前节点(避免遍历当前节点左子树完成后无法找到当前节点)，然后遍历当前节点左子树(最后遍历到的是前驱节点)
         */
        public List<Integer> postorderTraversal(TreeNode root) {
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
                        cur = cur.left;
                    }
                    // 第二次到达该最右节点，将mostRight.right指向null
                    else {
                        mostRight.right = null;
                        // 这里不同与“前序遍历，中序遍历”的简单处理，比较难搞！
                        addRes(cur.left, res);
                        cur = cur.right;
                    }
                }
                // 没有左子树时，考虑右子树
                else {
                    cur = cur.right;
                }
            }
            addRes(root, res);
            return res;
        }

        // ref: https://cloud.tencent.com/developer/article/1900915
        private void addRes(TreeNode node, List<Integer> res) {
            TreeNode head = reverse(node);
            TreeNode cur = head;
            while (cur != null) {
                res.add(cur.val);
                cur = cur.right;
            }
            reverse(head);
        }

        // 单链表反转(将right指针看作是next指针)
        private TreeNode reverse(TreeNode cur) {
            TreeNode prev = null;
            while (cur != null) {
                TreeNode next = cur.right;
                cur.right = prev;
                prev = cur;
                cur = next;
            }
            return prev;
        }

    }


}
