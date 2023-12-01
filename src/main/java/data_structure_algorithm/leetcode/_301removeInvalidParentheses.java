package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _301removeInvalidParentheses {

    public static class Solution1 {

        /**
         回溯：枚举所有可能(对每个括号，都有删除和不删除两种选择)，最后判断是否有效，取有效结果中的长度最长的解
         因为只有"("和")"，所以可以简化括号有效性的判断，将"("记为+1，将")"记为-1，保持遍历过程中sum>=0
         并且结束时sum=0即可。如果同时含有多种括号，需要参考"20-有效的括号"中判断括号是否有效的方法。

         NOTE：在枚举过程中需要保持sum>=0，否则可能出现")))((("这种非法解被当作合法解
         */
        public List<String> removeInvalidParentheses(String s) {
            backtrace(s, 0, "", 0);
            return new ArrayList<>(set);
        }

        // 记录最长有效解的长度(删除最小数量的括号，等价与最后解的长度最长)
        private int maxLen = 0;
        // 记录最长有效解的集合(set有去重的作用)
        private Set<String> set = new HashSet<>();

        private void backtrace(String s, int index, String cur, int sum) {
            // 递归终止条件：字符串遍历完了
            if (index == s.length()) {
                // 合法解条件：sum = 0
                if (sum == 0) {
                    // 找长度最长的合法解
                    if (cur.length() > maxLen) {
                        maxLen = cur.length();
                        set.clear();
                    }
                    if (cur.length() == maxLen) {
                        set.add(cur);
                    }
                }
                return;
            }

            char c = s.charAt(index);
            // 当前字符是左括号，可以选择删除或不删除
            if (c == '(') {
                // 不删除
                backtrace(s, index + 1, cur + "(", sum + 1);
                // 删除
                backtrace(s, index + 1, cur, sum);
            }
            // 当前字符是右括号，可以选择删除或不删除
            else if (c == ')') {
                // 不删除(保留右括号的前提是前面有左括号，所以要求sum > 0)
                if (sum > 0) backtrace(s, index + 1, cur + ")", sum - 1);
                // 删除
                backtrace(s, index + 1, cur, sum);
            }
            // 当前字符不是括号，不能删除，保持原样
            else {
                backtrace(s, index + 1, cur + String.valueOf(c), sum);
            }
        }

    }

}
