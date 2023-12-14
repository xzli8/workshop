package data_structure_algorithm.leetcode;

public class _242isAnagram {

    public static class Solution1 {

        /**
         哈希表：(类似题："383.赎金信")
         思路：因为s和t仅包含小写字母，数据范围有限，可以用数组代替哈希表，以获得更好的性能
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public boolean isAnagram(String s, String t) {
            if (s.length() != t.length()) return false;

            int[] counts = new int[26];
            for (char c : s.toCharArray()) {
                counts[c - 'a']++;
            }
            for (char c : t.toCharArray()) {
                if (--counts[c - 'a'] < 0) return false;
            }
            return true;
        }

    }

}
