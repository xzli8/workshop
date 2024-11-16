package data_structure_algorithm.leetcode;

import java.util.LinkedList;

public class _331isValidSerialization {

    public static class Solution1 {

        /**
         Stack:https://leetcode.cn/problems/verify-preorder-serialization-of-a-binary-tree/solutions/651132/pai-an-jiao-jue-de-liang-chong-jie-fa-zh-66nt/
         TC: O(N)
         SC: O(N)
         */
         public boolean isValidSerialization(String preorder) {
             LinkedList<String> s = new LinkedList<>();
             for (String node : preorder.split(",")) {
                 s.push(node);
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
         Indegree and outdegree
         TC: O(N)
         SC: O(1)
         */
        public boolean isValidSerialization(String preorder) {
            int diff = 1;
            for (String node : preorder.split(",")) {
                diff -= 1;
                if (diff < 0) return false;
                if (!node.equals("#")) diff += 2;
            }
            return diff == 0;
        }

    }

}
