package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class _113pathSum {

    public static class Solution0 {

        /**
         DFS
         时间复杂度：O(N^2)
         空间复杂度：O(N)
         */
        public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
            dfs(root, new ArrayList<>(), targetSum);
            return res;
        }

        private List<List<Integer>> res = new ArrayList<>();
        private void dfs(TreeNode cur, List<Integer> path, int targetSum) {
            if (cur == null) return;
            if (cur.left == null && cur.right == null && targetSum == cur.val) {
                path.add(cur.val);
                res.add(new ArrayList<>(path));
                path.remove(path.size() - 1);
                return;
            }

            path.add(cur.val);
            dfs(cur.left, path, targetSum - cur.val);
            dfs(cur.right, path, targetSum - cur.val);
            path.remove(path.size() - 1);
        }

    }



    public class Solution1 {

        /**
         DFS-v1：进入下一层前不判断当前节点是否为空，在进入下一层后再判断
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
             dfs(root, targetSum, new ArrayList<>());
             return res;
         }

         private List<List<Integer>> res = new ArrayList<>();
         private void dfs(TreeNode cur, int targetSum, List<Integer> path) {
             if (cur == null) return;
             path.add(cur.val);
             targetSum -= cur.val;
             if (cur.left == null && cur.right == null && targetSum == 0) {
                 // 这里要new一个，因为path是共享的，只有一份副本
                 res.add(new ArrayList<>(path));

                 // 这里要么不return，要么将path中最后一个元素remove后return(对于叶子结点不用后面对其子节点的多余判断)
                 path.remove(path.size() - 1);
                 return;
             }
             dfs(cur.left, targetSum, path);
             dfs(cur.right, targetSum, path);
             path.remove(path.size() - 1);
             // 为什么targetSum不用"回溯"，因为targetSum是int类型(值传递而不是引用传递)[每次都是new一个传下去，不是共享的，有多份副本]
         }

    }



    public class Solution2 {

        /**
         DFS-v2：进入下一层前判断当前节点是否为空，进入下一层后不判断
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
            List<List<Integer>> res = new ArrayList<>();
            if (root == null) return res;
            dfs(root, targetSum, new ArrayList<>(), res);
            return res;
        }

        private void dfs(TreeNode cur, int targetSum, List<Integer> path, List<List<Integer>> res) {
            targetSum -= cur.val;
            path.add(cur.val);
            if (cur.left == null && cur.right == null && targetSum == 0) {
                // 这里要new一个，因为path是共享的(引用传递而非值传递)
                res.add(new ArrayList<>(path));

                // 这里要么不return，要么将path中最后一个元素remove后return(对于叶子结点不用后面对其子节点的多余判断)
                path.remove(path.size() - 1);
                return;
            }

            if (cur.left != null) dfs(cur.left, targetSum, path, res);
            if (cur.right != null) dfs(cur.right, targetSum, path, res);
            path.remove(path.size() - 1);
            // 为什么targetSum不用"回溯"，因为targetSum是int类型(值传递而不是引用传递)[每次都是new一个传下去，不是共享的，有多份副本]
        }

    }


}
