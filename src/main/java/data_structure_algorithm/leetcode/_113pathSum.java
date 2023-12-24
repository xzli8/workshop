package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class _113pathSum {

    public class Solution1 {

        /**
         DFS-v1
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
             dfs(root, targetSum);
             return res;
         }

         private List<List<Integer>> res = new ArrayList<>();
         private Deque<Integer> path = new LinkedList<>();
         private void dfs(TreeNode root, int targetSum) {
             if (root == null) return;
             targetSum -= root.val;
             path.offerLast(root.val);
             if (root.left == null && root.right == null && targetSum == 0) {
                 res.add(new LinkedList<>(path));    // 这里不能直接return，需要执行后面的“回到过去”
             }
             dfs(root.left, targetSum);
             dfs(root.right, targetSum);
             path.pollLast();    // 回到过去，恢复现场
         }

    }



    public class Solution2 {

        /**
         DFS-v2
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
            if (cur.left == null && cur.right == null) {
                if (targetSum == 0) {
                    res.add(new ArrayList<>(path)); // 这里要new一个，因为path是共享的
                }
                return;
            }

            if (cur.left != null) {
                dfs(cur.left, targetSum, path, res);
                path.remove(path.size() - 1);
            }
            if (cur.right != null) {
                dfs(cur.right, targetSum, path, res);
                path.remove(path.size() - 1);
            }
        }

    }


}
