package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class _1382balanceBST {

    /**
     中序遍历+递归构建：O(N), O(N)
     */
    public TreeNode balanceBST(TreeNode root) {
        dfs(root);
        return build(0, vals.size() - 1);
    }

    private List<Integer> vals = new ArrayList<>();

    private void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.left);
        vals.add(root.val);
        dfs(root.right);
    }

    private TreeNode build(int left, int right) {
        if (left > right) return null;
        int mid = left + ((right - left) >> 1);
        return new TreeNode(vals.get(mid), build(left, mid - 1), build(mid + 1, right));
    }

}
