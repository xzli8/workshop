package data_structure_algorithm.leetcode;

import java.util.Collections;
import java.util.List;

public class _524findLongestWord {

    public static class Solution1 {

        /**
         排序 + 双指针
         思路：先将字典中的单词按照长度逆序排列，长度相同的按照字典序升序排列。
         然后从前往后遍历单词，对每个单词用双指针判断是否是s的子序列
         时间复杂度：O(NlogN + M * N)
         空间复杂度：O(logN)
         */
        public String findLongestWord(String s, List<String> dictionary) {
            // 排序
            Collections.sort(dictionary, (word1, word2) -> {
                if (word1.length() == word2.length()) return word1.compareTo(word2);
                return word2.length() - word1.length();
            });

            // 遍历，用双指针判断是否是子序列
            for (String word : dictionary) {
                if (isSubsequence(s, word)) return word;
            }
            return "";
        }

        // 双指针判断t是否是s的子序列(贪心)
        private boolean isSubsequence(String s, String t) {
            int i = 0, j = 0;
            while (i < s.length() && j < t.length()) {
                if (s.charAt(i) == t.charAt(j)) j++;
                i++;
            }
            return j == t.length();
        }

    }

}
