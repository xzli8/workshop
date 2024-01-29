package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _112hasPathSum {

    public static class Solution0 {

        /**
         DFS-v0：如果是向下的任意路径，可以这样写
         */
         public boolean hasPathSum(TreeNode root, int targetSum) {
             return dfs(root, targetSum);
         }

         private boolean dfs(TreeNode cur, int targetSum) {
             if (cur == null) return false;
             targetSum -= cur.val;
             return targetSum == 0 || dfs(cur.left, targetSum) || dfs(cur.right, targetSum);
         }

    }

    public class Solution1 {

        /**
         DFS-v1：进入下一层前不判断当前节点是否为空，在进入下一层后再判断
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public boolean hasPathSum(TreeNode root, int targetSum) {
            dfs(root, targetSum);
            return has;
        }

        private boolean has = false;
        private void dfs(TreeNode cur, int targetSum) {
            if (cur == null) return;
            targetSum -= cur.val;
            if (cur.left == null && cur.right == null) {
                if (targetSum == 0) {
                    has = true;
                }
                return;     // 这里有没有return都可以，后面再次进入递归函数时会判断，加上后避免多一层递归
            }
            dfs(cur.left, targetSum);
            dfs(cur.right, targetSum);
        }

    }



    public class Solution2 {

        /**
         DFS-v2：进入下一层前判断当前节点是否为空，进入下一层后不判断
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
             if (cur.left == null && cur.right == null) {
                 if (targetSum == 0) {
                     has = true;
                 }
                 return;     // 这里有没有return都可以，后面也会判空，加上return避免重复判空
             }
             if (cur.left != null) dfs(cur.left, targetSum);
             if (cur.right != null) dfs(cur.right, targetSum);
         }

    }



    public class Solution3 {

        /**
         DFS-v3：将v1中递归函数的返回值类型从void改成boolean，以代替全局变量has
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public boolean hasPathSum(TreeNode root, int targetSum) {
             return dfs(root, targetSum);
         }

         private boolean dfs(TreeNode cur, int targetSum) {
             if (cur == null) return false;
             targetSum -= cur.val;
             if (cur.left == null && cur.right == null) {
                 if (targetSum == 0) {
                     return true;
                 }
                 return false;   // 这里不加"return false"也可以，后面再次进入递归函数时会判断，加上后避免多一层递归
             }
             return dfs(cur.left, targetSum) || dfs(cur.right, targetSum);
         }

    }



    public class Solution4 {

        /**
         DFS-v4：将v2中递归函数的返回值类型从void改成boolean，以代替全局变量has
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public boolean hasPathSum(TreeNode root, int targetSum) {
             if (root == null) return false;
             return dfs(root, targetSum);
         }

         private boolean dfs(TreeNode cur, int targetSum) {
             targetSum -= cur.val;
             if (cur.left == null && cur.right == null) {
                 if (targetSum == 0) {
                     return true;
                 }
                 return false;   // 这里不加"return false"也可以，后面再次进入递归函数时会判断，加上后避免多一层递归
             }
             if (cur.left != null && dfs(cur.left, targetSum)) return true;
             if (cur.right != null && dfs(cur.right, targetSum)) return true;
             return false;
         }

    }



    public class Solution5 {

        /**
         DFS-v5：在v3的基础上，利用函数签名本身，不再定义递归函数，进一步简化编码
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public boolean hasPathSum(TreeNode root, int targetSum) {
             if (root == null) return false;
             targetSum -= root.val;
             if (root.left == null && root.right == null) {
                 if (targetSum == 0) {
                     return true;
                 }
                 return false;
             }
             return hasPathSum(root.left, targetSum) || hasPathSum(root.right, targetSum);
         }

    }



    public static class Solution6 {

        /**
         DFS-v6：在v5的基础上，继续简化编码
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public boolean hasPathSum(TreeNode root, int targetSum) {
            if (root == null) return false;
            targetSum -= root.val;
            if (root.left == null && root.right == null) return targetSum == 0;
            return hasPathSum(root.left, targetSum) || hasPathSum(root.right, targetSum);
        }

    }


}
