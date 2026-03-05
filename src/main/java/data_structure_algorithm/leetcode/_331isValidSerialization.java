package data_structure_algorithm.leetcode;

import java.util.LinkedList;

public class _331isValidSerialization {

    public static class Solution1 {

        /**
         Stack: 从叶子节点开始，将叶子节点用#替换(消消乐)
         ref: https://leetcode.cn/problems/verify-preorder-serialization-of-a-binary-tree/solutions/651132/pai-an-jiao-jue-de-liang-chong-jie-fa-zh-66nt/
         TC: O(N)
         SC: O(N)
         */
         public boolean isValidSerialization(String preorder) {
             LinkedList<String> s = new LinkedList<>();
             for (String node : preorder.split(",")) {
                 s.push(node);  // addFirst
                 while (s.size() >= 3 && s.get(0).equals("#") && s.get(1).equals("#") && !s.get(2).equals("#")) {
                     s.pop();
                     s.pop();
                     s.pop();
                     s.push("#");
                 }
             }
             return s.size() == 1 && s.pop().equals("#");
         }

    }



    public static class Solution2 {

        /**
         Indegree and outdegree: 二叉树中任何一个非空节点的入度为1出度为2，空节点的入度为1出度为0，遍历整棵树计算出入度差diff=出度-入度。
         TC: O(N)
         SC: O(1)
         */
        public boolean isValidSerialization(String preorder) {
            int diff = 1;   // 根节点没有父节点，入度为0，这里初始化为1修复(后面总是先计算diff -= 1)
            for (String node : preorder.split(",")) {
                diff -= 1;
                if (diff < 0) return false;     // 任何时候diff>=0
                if (!node.equals("#")) diff += 2;
            }
            return diff == 0;
        }

    }

}
