package data_structure_algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class _290wordPattern {

    public static class Solution1 {

        /**
         哈希表：建立双向映射
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public boolean wordPattern(String pattern, String s) {
            String[] strs = s.split(" ");
            if (pattern.length() != strs.length) return false;

            Map<Character, String> char2Str = new HashMap<>();
            Map<String, Character> str2Char = new HashMap<>();
            for (int i = 0; i < pattern.length(); i++) {
                char c = pattern.charAt(i);
                String str = strs[i];
                if (char2Str.containsKey(c) && !char2Str.get(c).equals(str)) return false;
                char2Str.putIfAbsent(c, str);
                if (str2Char.containsKey(str) && !str2Char.get(str).equals(c)) return false;
                str2Char.putIfAbsent(str, c);
            }
            return true;
        }

    }

}
