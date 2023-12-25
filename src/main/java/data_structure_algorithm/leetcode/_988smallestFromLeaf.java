package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class _988smallestFromLeaf {

    /**
     * 参考题解：https://zhuanlan.zhihu.com/p/150854767
     */

    public class Solution1 {

        /**
         DFS
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public String smallestFromLeaf(TreeNode root) {
            dfs(root, new ArrayList<>());
            return res;
        }

        // res初始化为比z字典序大的值即可，或者初始化为""，后面在判断大小前加上"res.equals("") || 大小判断"即可
        private String res = String.valueOf((char) ('z' + 1));
        private void dfs(TreeNode cur, List<Integer> path) {
            if (cur == null) return;
            path.add(cur.val);
            if (cur.left == null && cur.right == null) {
                StringBuilder sb = new StringBuilder();
                for (int num : path) {
                    sb.append((char) ('a' + num));
                }
                String tmp = sb.reverse().toString();
                if (tmp.compareTo(res) < 0) {
                    res = tmp;
                }
            }
            dfs(cur.left, path);
            dfs(cur.right, path);
            path.remove(path.size() - 1);
        }

    }

}
