package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class _257binaryTreePaths {

    public class Solution1 {

        /**
         DFS
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public List<String> binaryTreePaths(TreeNode root) {
            List<String> res = new ArrayList<>();
            dfs(root, "", res);
            return res;
        }

        private void dfs(TreeNode cur, String path, List<String> res) {
            path += cur.val;
            if (cur.left == null && cur.right == null) {
                res.add(path);
                return;
            }
            if (cur.left != null) dfs(cur.left, path + "->", res);
            if (cur.right != null) dfs(cur.right, path + "->", res);
        }

    }



    public class Solution2 {

        /**
         DFS：用List代替String，更能体现回溯的过程
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public List<String> binaryTreePaths(TreeNode root) {
            List<String> res = new ArrayList<>();
            dfs(root, new ArrayList<>(), res);
            return res;
        }

        private void dfs(TreeNode cur, List<Integer> path, List<String> res) {
            path.add(cur.val);
            if (cur.left == null && cur.right == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(path.get(0));
                for (int i = 1; i < path.size(); i++) {
                    sb.append("->").append(path.get(i));
                }
                res.add(sb.toString());
                return;
            }
            if (cur.left != null) {
                dfs(cur.left, path, res);
                path.remove(path.size() - 1);
            }
            if (cur.right != null) {
                dfs(cur.right, path, res);
                path.remove(path.size() - 1);
            }
        }

    }

}
