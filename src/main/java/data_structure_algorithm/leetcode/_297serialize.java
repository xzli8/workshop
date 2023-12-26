package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _297serialize {

    // Your Codec object will be instantiated and called as such:
    // Codec ser = new Codec();
    // Codec deser = new Codec();
    // TreeNode ans = deser.deserialize(ser.serialize(root));

    public static class Solution1 {

        /**
         * Definition for a binary tree node.
         * public class TreeNode {
         *     int val;
         *     TreeNode left;
         *     TreeNode right;
         *     TreeNode(int x) { val = x; }
         * }
         */
        public class Codec {

            /**
             BFS：当节点为空时，仍然要加入队列，但空节点的子节点不会再加入队列。空节点会被特殊标记(为"x")
             时间复杂度：O(N)
             空间复杂度：O(N)
             */
            // Encodes a tree to a single string.
            public String serialize(TreeNode root) {
                List<String> res = new ArrayList<>();
                Queue<TreeNode> q = new LinkedList<>();
                q.offer(root);
                while (!q.isEmpty()) {
                    int size = q.size();
                    for (int i = 0; i < size; i++) {
                        TreeNode node = q.poll();
                        if (node != null) {
                            res.add(node.val + "");
                            q.offer(node.left);
                            q.offer(node.right);
                        } else {
                            res.add("x");
                        }
                    }
                }
                return String.join(",", res);
            }

            // Decodes your encoded data to tree.
            public TreeNode deserialize(String data) {
                if (data.equals("x")) return null;

                // 将字符串转换成数组
                String[] res = data.split(",");

                // 初始化根节点
                TreeNode root = new TreeNode(Integer.valueOf(res[0]));

                // BFS
                Queue<TreeNode> q = new LinkedList<>();
                q.offer(root);
                int index = 1;
                while (index < res.length) {
                    TreeNode node = q.poll();
                    String leftVal = res[index];
                    String rightVal = res[index + 1];
                    if (!leftVal.equals("x")) {
                        TreeNode leftNode = new TreeNode(Integer.valueOf(leftVal));
                        node.left = leftNode;
                        q.offer(leftNode);
                    }
                    if (!rightVal.equals("x")) {
                        TreeNode rightNode = new TreeNode(Integer.valueOf(rightVal));
                        node.right = rightNode;
                        q.offer(rightNode);
                    }
                    index += 2;
                }
                return root;
            }
        }

    }

}
