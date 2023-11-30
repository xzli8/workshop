package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _22generateParenthesis {

    public static class Solution1 {

        /**
         回溯
         */
        public List<String> generateParenthesis(int n) {
            List<String> res = new ArrayList<>();
            backtrace(res, new StringBuilder(), 0, 0, n);
            return res;
        }

        /**
         res:最终结果
         sb:每一步的结果
         l:左括号数量
         r:右括号数量
         n:总数
         */
        private void backtrace(List<String> res, StringBuilder sb, int l, int r, int n) {
            // 当左括号和右括号的数量都等于总数n时，停止递归，返回结果
            if (l == n && r == n) res.add(sb.toString());
            if (l < n) {    // 当左括号数量小于总数n时，可以将左括号+1，然后继续下一层递归
                sb.append("(");
                backtrace(res, sb, l + 1, r, n);
                sb.deleteCharAt(sb.length() - 1);   // 回到过去
            }
            if (r < l) {    // 当右括号数量小于左括号数量时，可以将右括号+1，然后继续下一层递归
                sb.append(")");
                backtrace(res, sb, l, r + 1, n);
                sb.deleteCharAt(sb.length() - 1);   // 回到过去
            }
        }


    }

}
