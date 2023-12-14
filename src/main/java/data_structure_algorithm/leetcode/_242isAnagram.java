package data_structure_algorithm.leetcode;

public class _242isAnagram {

    public static class Solution1 {

        /**
         哈希表
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public boolean isAnagram(String s, String t) {
            if (s.length() != t.length()) return false;

            int[] set = new int[26];
            for (int i = 0; i < s.length(); i++) {
                set[s.charAt(i) - 'a']++;
            }
            for (int i = 0; i < t.length(); i++) {
                set[t.charAt(i) - 'a']--;
                if (set[t.charAt(i) - 'a'] < 0) return false;
            }
            return true;
        }

    }

}
