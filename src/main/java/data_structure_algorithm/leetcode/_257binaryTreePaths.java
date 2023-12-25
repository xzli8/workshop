package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class _257binaryTreePaths {

    public class Solution1 {

        /**
         DFS-v1：进入下一层前不判断当前节点是否为空，在进入下一层后再判断
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public List<String> binaryTreePaths(TreeNode root) {
             dfs(root, new ArrayList<>());
             return res;
         }

         private List<String> res = new ArrayList<>();
         private void dfs(TreeNode cur, List<Integer> path) {
             if (cur == null) return;
             path.add(cur.val);
             if (cur.left == null && cur.right == null) {
                 StringBuilder sb = new StringBuilder();
                 sb.append(path.get(0));
                 for (int i = 1; i < path.size(); i++) {
                     sb.append("->").append(path.get(i));
                 }
                 res.add(sb.toString());
                 // 这里不能return，因为即使这条路径不通，还需要尝试其他路径，所以后面要"remove"
                 // 这里如果return了，后面的"remove"就无法执行，路径上就多了一个节
             }
             dfs(cur.left, path);
             dfs(cur.right, path);
             path.remove(path.size() - 1);
         }

    }



    public class Solution2 {

        /**
         DFS-v2：进入下一层前判断当前节点是否为空，进入下一层后不判断
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public List<String> binaryTreePaths(TreeNode root) {
            dfs(root, new ArrayList<>());
            return res;
        }

        private List<String> res = new ArrayList<>();
        private void dfs(TreeNode cur, List<Integer> path) {
            path.add(cur.val);
            if (cur.left == null && cur.right == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(path.get(0));
                for (int i = 1; i < path.size(); i++) {
                    sb.append("->").append(path.get(i));
                }
                res.add(sb.toString());
                return;      // 这里有没有return都可以，因为后面也会判空，加上return避免重复判空
            }
            if (cur.left != null) {
                dfs(cur.left, path);
                path.remove(path.size() - 1);   // "回溯"为什么要写在这个地方？
            }
            if (cur.right != null) {
                dfs(cur.right, path);
                path.remove(path.size() - 1);
            }
        }

    }



    public class Solution3 {

        /**
         DFS-v3：用String代替List，java中String是不可变类型，每次都new一个新的，所以不需要回溯处理
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public List<String> binaryTreePaths(TreeNode root) {
             dfs(root, "");
             return res;
         }

         private List<String> res = new ArrayList<>();
         private void dfs(TreeNode cur, String path) {
             if (cur == null) return;
             path += cur.val;
             if (cur.left == null && cur.right == null) {
                 res.add(path);
             }
             dfs(cur.left, path + "->");
             dfs(cur.right, path + "->");
         }

    }



    public class Solution4 {

        /**
         DFS-v4：用String代替List，java中String是不可变类型，每次都new一个新的，所以不需要回溯处理
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public List<String> binaryTreePaths(TreeNode root) {
             dfs(root, "");
             return res;
         }

         private List<String> res = new ArrayList<>();
         private void dfs(TreeNode cur, String path) {
             path += cur.val;
             if (cur.left == null && cur.right == null) {
                 res.add(path);
                 return;
             }
             if (cur.left != null) dfs(cur.left, path + "->");
             if (cur.right != null) dfs(cur.right, path + "->");
         }

    }



}
