package data_structure_algorithm.leetcode;

public class _389findTheDifference {

    public static class Solution1 {

        /**
         异或
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public char findTheDifference(String s, String t) {
            char res = t.charAt(t.length() - 1);
            for (int i = 0; i < s.length(); i++) {
                res ^= s.charAt(i);
                res ^= t.charAt(i);
            }
            return res;
        }

    }

}
