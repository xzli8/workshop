package data_structure_algorithm.leetcode;

public class _58lengthOfLastWord {

    public static class Solution1 {

        /**
         双指针
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int lengthOfLastWord(String s) {
            int end = s.length() - 1;
            while (end >= 0 && s.charAt(end) == ' ') end--;
            int start = end;
            while (start >= 0 && s.charAt(start) != ' ') start--;
            return end - start;
        }

    }

}
