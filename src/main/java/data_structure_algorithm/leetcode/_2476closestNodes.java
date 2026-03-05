package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.*;

public class _2476closestNodes {

    public static class Solution1 {

        /**
         inorder(递归/迭代) + BinarySearch: O(N + MlogN), O(N)[N为二叉树节点数量，M为queries长度]
         Note: 先中序遍历得到有序数组，然后在有序数组中二分查找(因为题目没说BST是BBST，如果最坏情况是退化成链表，直接查询可能是O(N)，所以这样效率最高)
         */
        public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
            // 中序遍历得到有序数组
            List<Integer> inorder = new ArrayList<>();
            Deque<TreeNode> s = new ArrayDeque<>();
            TreeNode cur = root;
            while (!s.isEmpty() || cur != null) {
                if (cur != null) {
                    s.push(cur);
                    cur = cur.left;
                } else {
                    cur = s.pop();
                    inorder.add(cur.val);
                    cur = cur.right;
                }
            }

            // 二分查找
            List<List<Integer>> res = new ArrayList<>();
            for (Integer query : queries) res.add(bsearch(inorder, query));
            return res;
        }

        private List<Integer> bsearch(List<Integer> nums, Integer target) {
            // 先找小于等于target的最大值
            int max = -1, min = -1;
            int n = nums.size(), left = 0, right = n - 1;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (nums.get(mid) <= target) {
                    if (mid == n - 1 || nums.get(mid + 1) > target) {
                        max = nums.get(mid);
                        break;
                    }
                    else left = mid + 1;
                } else right = mid - 1;
            }

            // 然后找大于等于target的最小值
            left = 0; right = n - 1;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (nums.get(mid) >= target) {
                    if (mid == 0 || nums.get(mid - 1) < target) {
                        min = nums.get(mid);
                        break;
                    }
                    else right = mid - 1;
                } else left = mid + 1;
            }
            return Arrays.asList(max, min);
        }

    }

}
