package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class _1305getAllElements {

    public static class Solution1 {

        /**
         Inorder traverse + mergeSort
         TimeComplexity:O(M + N)
         SpaceComplexity:O(M + N)
         */
        public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
            // inorder traverse to get sorted list
            List<Integer> list1 = new ArrayList<>(), list2 = new ArrayList<>();
            inorderTraverse(root1, list1);
            inorderTraverse(root2, list2);

            // merge two sorted lists
            int n1 = list1.size(), n2 = list2.size(), i = 0, j = 0;
            List<Integer> list = new ArrayList<>(n1 + n2);
            while (i < n1 && j < n2) {
                if (list1.get(i) < list2.get(j)) {
                    list.add(list1.get(i++));
                } else {
                    list.add(list2.get(j++));
                }
            }
            while (i < n1) {
                list.add(list1.get(i++));
            }
            while (j < n2) {
                list.add(list2.get(j++));
            }
            return list;
        }

        private void inorderTraverse(TreeNode node, List<Integer> list) {
            if (node == null) return;
            inorderTraverse(node.left, list);
            list.add(node.val);
            inorderTraverse(node.right, list);
        }

    }

}
