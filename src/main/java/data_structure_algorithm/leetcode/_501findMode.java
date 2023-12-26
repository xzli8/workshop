package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class _501findMode {

    public static class Solution1 {

        /**
         遍历树，统计每个数字出现的次数，然后按照出现次数排序，返回结果
         优点：适用于任意二叉树，而不仅仅是二叉搜索树
         缺点：需要额外的空间

         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public int[] findMode(TreeNode root) {
             List<Integer> res = new ArrayList<>();
             if (root == null) res.stream().mapToInt(Integer::intValue).toArray();

             dfs(root);
             List<Map.Entry<Integer, Integer>> list = val2Count.entrySet().stream().sorted((c1, c2) -> c2.getValue().compareTo(c1.getValue())).collect(Collectors.toList());
             res.add(list.get(0).getKey());
             for (int i = 1; i < list.size(); i++) {
                 if (list.get(i).getValue().equals(list.get(0).getValue())) {
                     res.add(list.get(i).getKey());
                 } else {
                     break;
                 }
             }
             return res.stream().mapToInt(Integer::intValue).toArray();
         }

         private Map<Integer, Integer> val2Count = new HashMap<>();
         private void dfs(TreeNode cur) {
             if (cur == null) return;
             // 统计次数放在前/中/后序的位置都可以
             val2Count.put(cur.val, val2Count.getOrDefault(cur.val, 0) + 1);
             dfs(cur.left);
             dfs(cur.right);
         }

    }



    public static class Solution2 {

        /**
         DFS：BST的中序遍历是升序序列，所以可以一边遍历一边计数一边更新，不使用额外空间
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int[] findMode(TreeNode root) {
            dfs(root);
            return modes.stream().mapToInt(Integer::intValue).toArray();
        }

        private List<Integer> modes = new ArrayList<>();
        private int count, maxCount;
        private TreeNode prev;

        private void dfs(TreeNode cur) {
            if (cur == null) return;
            dfs(cur.left);
            update(cur);
            dfs(cur.right);
        }

        private void update(TreeNode cur) {
            // 先更新count
            if (prev == null || cur.val != prev.val) {
                prev = cur;
                count = 1;
            } else {
                count++;
            }

            // 再更新mode
            if (count == maxCount) {
                modes.add(cur.val);
            } else if (count > maxCount) {
                maxCount = count;
                modes.clear();
                modes.add(cur.val);
            }
        }

    }


}
