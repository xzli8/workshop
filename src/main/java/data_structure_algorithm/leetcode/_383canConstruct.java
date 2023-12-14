package data_structure_algorithm.leetcode;

public class _383canConstruct {

    public static class Solution1 {

        /**
         哈希表：(类似题："242.有效的字母异位词")
         思路：因为仅包含小写字母，数据范围有限，可以用数组代替哈希表以获得更好的性能
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public boolean canConstruct(String ransomNote, String magazine) {
            int[] counts = new int[26];
            for (char c : magazine.toCharArray()) {
                counts[c - 'a']++;
            }
            for (char c : ransomNote.toCharArray()) {
                if (--counts[c - 'a'] < 0) return false;
            }
            return true;
        }

    }

}
