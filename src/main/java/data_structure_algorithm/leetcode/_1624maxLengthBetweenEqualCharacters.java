package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _1624maxLengthBetweenEqualCharacters {

    public static class Solution1 {

        /**
         哈希表：遍历字符串中的所有字符，统计每个字符第一次出现的下标，初次出现不更新maxLen，后面出现时用当前下标减去第一次出现的下标之差更新maxLen
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int maxLengthBetweenEqualCharacters(String s) {
            int[] char2Index = new int[26];
            Arrays.fill(char2Index, -1);
            int maxLen = -1;
            for (int i = 0; i < s.length(); i++) {
                int idx = s.charAt(i) - 'a', index = char2Index[idx];
                if (index != -1) maxLen = Math.max(maxLen, i - index - 1);
                else char2Index[idx] = i;
            }
            return maxLen;
        }

    }

}
