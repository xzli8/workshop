package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.*;

public class _449serialize {

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
    // String tree = ser.serialize(root);
    // TreeNode ans = deser.deserialize(tree);
    // return ans;

    public static class Solution1 {

        /**
         * 层序遍历序列化(一般二叉树的序列化/反序列化方式)
         * 分析：适用于所有二叉树，但没有充分利用BST的性质
         */
        public class Codec {

            // Encodes a tree to a single string.
            public String serialize(TreeNode root) {
                List<String> res = new ArrayList<>();
                Queue<TreeNode> q = new LinkedList<>();
                q.offer(root);
                while (!q.isEmpty()) {
                    TreeNode cur = q.poll();
                    if (cur == null) {
                        res.add("x");
                    } else {
                        res.add(String.valueOf(cur.val));
                        q.offer(cur.left);
                        q.offer(cur.right);
                    }
                }
                return String.join(",", res);
            }

            // Decodes your encoded data to tree.
            public TreeNode deserialize(String data) {
                if ("x".equals(data)) return null;

                // split
                String[] res = data.split(",");

                // init root
                TreeNode root = new TreeNode(Integer.valueOf(res[0]));

                // bfs
                Queue<TreeNode> q = new ArrayDeque<>();
                q.offer(root);
                int idx = 1;
                while (idx < res.length) {
                    TreeNode cur = q.poll();
                    String leftVal = res[idx++], rightVal = res[idx++];
                    if (!"x".equals(leftVal)) {
                        cur.left = new TreeNode(Integer.valueOf(leftVal));
                        q.offer(cur.left);
                    }
                    if (!"x".equals(rightVal)) {
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
         序列化：遍历得到前/后序遍历序列
         反序列化：根据有序性直接由前/后序遍历序列来恢复BST
         */
        public class Codec {

            // Encodes a tree to a single string.
            public String serialize(TreeNode root) {
                List<Integer> postorder = new ArrayList<>();
                Deque<TreeNode> s = new ArrayDeque<>();
                if (root != null) s.push(root);
                while (!s.isEmpty()) {
                    TreeNode cur = s.poll();
                    postorder.add(cur.val);
                    if (cur.left != null) s.push(cur.left);
                    if (cur.right != null) s.push(cur.right);
                }
                Collections.reverse(postorder);
                String str = postorder.toString();
                return str.substring(1, str.length() - 1);
            }

            // Decodes your encoded data to tree.
            public TreeNode deserialize(String data) {
                if (data.isEmpty()) return null;
                String[] postorder = data.split(", ");
                Deque<Integer> s = new ArrayDeque<>();
                for (String str : postorder) {
                    s.push(Integer.parseInt(str));
                }
                return construct(Integer.MIN_VALUE, Integer.MAX_VALUE, s);
            }

            private TreeNode construct(int lower, int upper, Deque<Integer> s) {
                if (s.isEmpty() || s.peek() < lower || s.peek() > upper) {
                    return null;
                }

                int val = s.pop();
                TreeNode root = new TreeNode(val);
                root.right = construct(val, upper, s);
                root.left = construct(lower, val, s);
                return root;
            }

        }

    }


    public static class Solution3 {

    }


}
