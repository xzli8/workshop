package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _1002commonChars {

    public static class Solution1 {

        /**
         哈希表
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public List<String> commonChars(String[] words) {

            // 遍历所有字符串，统计每个字符串中所有字符及其出现次数
            int n = words.length;
            int[][] counts = new int[n][26];
            for (int i = 0; i < n; i++) {
                String word = words[i];
                for (char c : word.toCharArray()) {
                    counts[i][c - 'a']++;
                }
            }

            // 遍历所有统计结果，针对每个字符，按其出现的最少次数，添加到结果中
            List<String> res = new ArrayList<>();
            for (int i = 0; i < 26; i++) {
                int minCount = Integer.MAX_VALUE;
                for (int j = 0; j < n; j++) {
                    minCount = Math.min(minCount, counts[j][i]);
                }
                while (minCount-- > 0) {
                    res.add(String.valueOf((char) (i + 'a')));
                }
            }
            return res;
        }

    }

}
