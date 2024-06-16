package data_structure_algorithm.leetcode;

public class _521findLUSlength {

    public static class Solution1 {

        /**
         脑筋急转弯
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int findLUSlength(String a, String b) {
            return a.equals(b) ? -1 : Math.max(a.length(), b.length());
        }

    }

}
