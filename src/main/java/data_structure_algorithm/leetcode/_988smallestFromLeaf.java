package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class _988smallestFromLeaf {

    /**
     * 参考题解：https://zhuanlan.zhihu.com/p/150854767
     */

    public class Solution1 {

        /**
         DFS: O(NlogN)[遍历O(N), 比较O(logN)], O(N)
         Note: 创建出所有可能的字符串，然后逐一比较，输出字典序最小的那个。
         */
        public String smallestFromLeaf(TreeNode root) {
            dfs(root, new StringBuilder());
            return min;
        }

        private String min = "";
        private void dfs(TreeNode root, StringBuilder path) {
            if (root == null) return;
            path.append((char) ('a' + root.val));
            if (root.left == null && root.right == null) {
                path.reverse();
                String s = path.toString();
                path.reverse(); // 切记一定要reverse回来
                if (Objects.equals(min, "") || s.compareTo(min) < 0) {
                    min = s;
                }
            }
            dfs(root.left, path);
            dfs(root.right, path);
            path.deleteCharAt(path.length() - 1);
        }

    }

}
