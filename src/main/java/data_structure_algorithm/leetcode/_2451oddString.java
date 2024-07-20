package data_structure_algorithm.leetcode;

public class _2451oddString {

    public static class Solution1 {

        /**
         Find Diff String(more simple than calculate difference array)
         TimeComplexity: O(N)
         SpaceComplexity: O(1)
         */
        public String oddString(String[] words) {
            if (isDiff(words[0], words[1]) && isDiff(words[0], words[2])) return words[0];
            for (int i = 1; i < words.length; i++) {
                if (isDiff(words[0], words[i])) {
                    return words[i];
                }
            }
            return null;
        }

        private boolean isDiff(String s1, String s2) {
            int diff = s1.charAt(0) - s2.charAt(0);
            for (int i = 1; i < s1.length(); i++) {
                if (s1.charAt(i) - s2.charAt(i) != diff) {
                    return true;
                }
            }
            return false;
        }

    }

}
