package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _297serialize {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */

    // Your Codec object will be instantiated and called as such:
    // Codec ser = new Codec();
    // Codec deser = new Codec();
    // TreeNode ans = deser.deserialize(ser.serialize(root));

    public static class Solution1 {

        /**
         BFS层序遍历：当节点为空时，仍然要加入队列，但空节点的子节点不会再加入队列。空节点会被特殊标记(为"x")
         时间复杂度：O(N)
         空间复杂度：O(N
         */
        public class Codec {

            // Encodes a tree to a single string.
            public String serialize(TreeNode root) {
                List<String> res = new ArrayList<>();
                Queue<TreeNode> q = new LinkedList<>(); // 这里不能用ArrayDeque，因为ArrayDeque中不能有null
                q.offer(root);
                while (!q.isEmpty()) {
                    // 内层for循环不要也可以，因为和深度无关
                    // int size = q.size();
                    // for (int i = 0; i < size; i++) {
                    TreeNode cur = q.poll();
                    if (cur == null) {
                        res.add("x");
                    } else {
                        res.add(String.valueOf(cur.val));
                        q.offer(cur.left);
                        q.offer(cur.right);
                    }
                    // }
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
                    TreeNode cur = q.poll();
                    String leftVal = res[index++], rightVal = res[index++];
                    if (!leftVal.equals("x")) {
                        cur.left = new TreeNode(Integer.valueOf(leftVal));
                        q.offer(cur.left);
                    }
                    if (!rightVal.equals("x")) {
                        cur.right = new TreeNode(Integer.valueOf(rightVal));
                        q.offer(cur.right);
                    }
                }
                return root;
            }

        }

    }



    public static class Solution2 {

        /**
         序列化：遍历得到前/后序遍历序列 + 遍历得到中序遍历序列 => 组合形成String
         反序列化：由前/后序遍历序列 + 中序遍历序列 => recontruct
         */


    }

}
