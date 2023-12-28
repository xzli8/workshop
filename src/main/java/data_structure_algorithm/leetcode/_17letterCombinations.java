package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _17letterCombinations {

    public static class Solution1 {

        /**
         回溯
         时间复杂度：O(3^M * 4^N)
         空间复杂度：O(M + N)
         */
        public List<String> letterCombinations(String digits) {
            if (digits.length() == 0) return res;
            backtrace(digits, 0, new StringBuilder());
            return res;
        }

        // 数字到字符串的映射
        private static final Map<Character, String> dig2Str = new HashMap<>();
        static {
            dig2Str.put('2', "abc");
            dig2Str.put('3', "def");
            dig2Str.put('4', "ghi");
            dig2Str.put('5', "jkl");
            dig2Str.put('6', "mno");
            dig2Str.put('7', "pqrs");
            dig2Str.put('8', "tuv");
            dig2Str.put('9', "wxyz");
        }

        // 回溯
        private final List<String> res = new ArrayList<>();
        private void backtrace(String digits, int idx, StringBuilder path) {
            if (idx == digits.length()) {
                res.add(path.toString());
                return;
            }

            for (char c : dig2Str.get(digits.charAt(idx)).toCharArray()) {
                path.append(c);
                backtrace(digits, idx + 1, path);
                path.deleteCharAt(path.length() - 1);
            }
        }

    }

}
