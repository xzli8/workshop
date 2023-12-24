package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _112hasPathSum {

    public class Solution1 {

        /**
         DFS-v1
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public boolean hasPathSum(TreeNode root, int targetSum) {
             dfs(root, 0, targetSum);
             return flag;
         }

         private boolean flag = false;
         private void dfs(TreeNode cur, int sum, int targetSum) {
             // 当前节点为空，返回
             if (cur == null) return;

             // 计算从根节点到当前节点的和
             sum += cur.val;

             // 如果当前节点是叶子结点，并且sum等于targetSum，flag为true返回
             if (cur.left == null && cur.right == null && sum == targetSum) {
                 flag = true;
                 return;
             }

             // 继续遍历当前节点的左右子节点
             dfs(cur.left, sum, targetSum);
             dfs(cur.right, sum, targetSum);
         }

    }



    public class Solution2 {

        /**
         DFS-v2
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public boolean hasPathSum(TreeNode root, int targetSum) {
             if (root == null) return false;
             dfs(root, targetSum);
             return has;
         }

         private boolean has = false;
         private void dfs(TreeNode cur, int targetSum) {
             targetSum -= cur.val;
             if (cur.left == null && cur.right == null && targetSum == 0) {
                 has = true;
                 return;
             }

             if (!has && cur.left != null) dfs(cur.left, targetSum);
             if (!has && cur.right != null) dfs(cur.right, targetSum);
         }

    }



    public class Solution3 {

        /**
         DFS-v3
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public boolean hasPathSum(TreeNode root, int targetSum) {
            if (root == null) return false;
            return dfs(root, targetSum);
        }

        private boolean dfs(TreeNode cur, int targetSum) {
            targetSum -= cur.val;
            if (cur.left == null && cur.right == null && targetSum == 0) {
                return true;
            }

            if (cur.left != null && dfs(cur.left, targetSum)) return true;
            if (cur.right != null && dfs(cur.right, targetSum)) return true;
            return false;
        }

    }



}
