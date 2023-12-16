package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.*;

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



    public static class Solution3 {

        /**
         dfs遍历+搜索：(类似题：“1214.查找两棵二叉搜索树之和”)
         思路：枚举其中一个节点，在BST中搜索另一个节点，需要注意搜索到枚举节点本身的情况
         时间复杂度：O(NlogN)
         空间复杂度：O(logN)，取决于二叉树的深度
         */
         public boolean findTarget(TreeNode root, int k) {
             return doFindTarget(root, root, k);
         }

         private boolean doFindTarget(TreeNode root1, TreeNode root2, int target) {
             if (root1 == null) return false;
             // 当枚举节点的值等于target时，命中了该节点本身，不符合要求，需要排除
             if (root1.val != target - root1.val && find(root2, target - root1.val)) return true;
             return doFindTarget(root1.left, root2, target) || doFindTarget(root1.right, root2, target);
         }

         private boolean find(TreeNode root, int target) {
             if (root == null) return false;
             if (root.val == target) {
                 return true;
             } else if (root.val < target) {
                 return find(root.right, target);
             } else {
                 return find(root.left, target);
             }
         }

    }



    public static class Solution4 {

        /**
         bfs遍历+搜索：(类似题：“1214.查找两棵二叉搜索树之和”)
         思路：枚举其中一个节点，在BST中搜索另一个节点，需要注意搜索到枚举节点本身的情况
         时间复杂度：O(NlogN)
         空间复杂度：O(N)，取决于二叉树的最大宽度
         */
        public boolean findTarget(TreeNode root, int k) {
            if (root == null) return false;

            // bfs遍历
            Queue<TreeNode> q = new ArrayDeque<>();
            q.offer(root);
            while (!q.isEmpty()) {
                TreeNode cur = q.poll();
                if (cur.val != k - cur.val && find(root, k - cur.val)) return true;
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
            return false;
        }

        private boolean find(TreeNode root, int target) {
            if (root == null) return false;
            if (root.val == target) {
                return true;
            } else if (root.val < target) {
                return find(root.right, target);
            } else {
                return find(root.left, target);
            }
        }

    }




}
