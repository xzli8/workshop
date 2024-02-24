package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.*;

public class _2476closestNodes {

    public static class Solution1 {

        /**
         中序遍历得到有序数组 + 二分查找
         时间复杂度：O(N + MlogN)
         空间复杂度：O(N)
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
