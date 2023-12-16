package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _653findTarget {

    public static class Solution1 {

        /**
         哈希表 + 遍历(dfs/bfs)：(类似题："1.两数之和")
         分析：没有利用BST的性质(中序遍历为有序数组)，如果输入二叉树也可以这样解。
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public boolean findTarget(TreeNode root, int k) {
            if (root == null) return false;
            if (set.contains(k - root.val)) return true;
            set.add(root.val);
            return findTarget(root.left, k) || findTarget(root.right, k);
        }

        private Set<Integer> set = new HashSet<Integer>();

    }



    public static class Solution2 {

        /**
         中序遍历 + 双指针:中序遍历得到一个有序数组，然后用双指针在有序数组中寻找
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public boolean findTarget(TreeNode root, int k) {
             // 中序遍历
             List<Integer> nums = new ArrayList<>();
             inorder(root, nums);

             // 双指针查找
             int left = 0, right = nums.size() - 1;
             while (left < right) {  // 注意这里一定是"<"，而不是"<="
                 int sum = nums.get(left) + nums.get(right);
                 if (sum == k) {
                     return true;
                 } else if (sum < k) {
                     left++;
                 } else {
                     right--;
                 }
             }
             return false;
         }

         private void inorder(TreeNode root, List<Integer> vals) {
             if (root == null) return;
             inorder(root.left, vals);
             vals.add(root.val);
             inorder(root.right, vals);
         }

    }


}
