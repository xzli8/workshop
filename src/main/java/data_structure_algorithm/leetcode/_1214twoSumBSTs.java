package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class _1214twoSumBSTs {

    public static class Solution1 {

        /**
         *  哈希表：遍历两棵树将节点分别存放在两个哈希表中，然后遍历其中一个哈希表的values，判断另一个哈希表中是否有对应值
         *      分析：没有用到二叉搜索数的性质
         *      时间复杂度：O(M + N)
         *      空间复杂度：O(M + N)
         */
        public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
            // 遍历树，构建哈希表
            HashSet<Integer> s1 = new HashSet<>();
            HashSet<Integer> s2 = new HashSet<>();
            traverse(root1, s1);
            traverse(root2, s2);

            // 遍历其中一个哈希表的values，判断另一个哈希表中是否有对应值
            for (int val : s1) {
                if (s2.contains(target - val)) {
                    return true;
                }
            }
            return false;
        }

        // dfs/bfs均可，dfs的前中后序也均可，这里采用dfs前序遍历
        private void traverse(TreeNode root, Set<Integer> s) {
            if (root == null) return;
            s.add(root.val);
            traverse(root.left, s);
            traverse(root.right, s);
        }

    }



    public static class Solution2 {

        /**
         *  dfs遍历 + 查找：遍历其中一棵树，当遍历到某个节点是，在另一棵BST上寻找对应值(这里可以利用BST的性质搜索)
         *      时间复杂度：O(MlogN)
         *      空间复杂度：O(logM)，取决于递归遍历root1时的递归深度，也就是root1的高度
         */
        public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
            if (root1 == null) return false;
            if (find(root2, target - root1.val)) return true;
            return twoSumBSTs(root1.left, root2, target) || twoSumBSTs(root1.right, root2, target);
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



    public static class Solution3 {

        /**
         *  bfs遍历 + 查找：遍历其中一棵树，当遍历到某个节点是，在另一棵BST上寻找对应值(这里可以利用BST的性质搜索)
         *      时间复杂度：O(MlogN)
         *      空间复杂度：O(M)，取决于root1的最大宽度
         */
        public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
            if (root1 == null) return false;

            // bfs遍历root1
            Queue<TreeNode> q = new ArrayDeque<>();
            q.offer(root1);
            while (!q.isEmpty()) {
                TreeNode cur = q.poll();
                if (find(root2, target - cur.val)) {
                    return true;
                }
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
