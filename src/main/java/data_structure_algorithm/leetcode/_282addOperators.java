package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _282addOperators {

    public static class Solution1 {

        /**
         回溯：O(4^N), O(N)
         */
        public List<String> addOperators(String num, int target) {
            this.n = num.length();
            this.target = target;
            this.num = num;
            this.ans = new ArrayList<>();
            StringBuilder exp = new StringBuilder();
            backtrace(exp, 0, 0, 0);
            return ans;
        }

        private int n, target;
        private String num;
        private List<String> ans;

        /**
         exp: 当前构建出来的表达式
         i: 当前枚举到了num的第i个数字
         res: 当前表达式的计算结果
         mul: 表达式最后一个连乘串的结果
         */
        private void backtrace(StringBuilder exp, int i, long res, long mul) {
            if (i == n) {
                if (res == target) {
                    ans.add(exp.toString());
                }
                return;
            }

            int signIndex = exp.length();   // (即将可能要填充的)符号位下标
            if (i > 0) exp.append(0);   // 占位，下面填充符号

            // 枚举截取数字的长度(取多少位)
            long val = 0;
            // for (int j = i; j < n && (j == i || num.charAt(i) != '0'); j++) {
            for (int j = i; j < n; j++) {
                // 数字可以是单个0但不能有前导0(跳过这种case)
                if (j != i && num.charAt(i) == '0') break;

                val = val * 10 + num.charAt(j) - '0';
                exp.append(num.charAt(j));
                if (i == 0) {
                    backtrace(exp, j + 1, val, val);
                } else {
                    exp.setCharAt(signIndex, '+');
                    backtrace(exp, j + 1, res + val, val);
                    exp.setCharAt(signIndex, '-');
                    backtrace(exp, j + 1, res - val, -val);
                    exp.setCharAt(signIndex, '*');
                    backtrace(exp, j + 1, res - mul + mul * val, mul * val);
                }
            }
            exp.setLength(signIndex);   // 回溯
        }


    }



    public static class Solution2 {

        private List<String> ans;
        private String num;
        private int target;

        public List<String> addOperators(String num, int target) {
            ans = new ArrayList<>();
            this.num = num;
            this.target = target;
            dfs(0, 0, 0, "");
            return ans;
        }

        private void dfs(int u, long prev, long curr, String path) {
            if (u == num.length()) {
                if (curr == target) ans.add(path);
                return;
            }
            for (int i = u; i < num.length(); i++) {
                if (i != u && num.charAt(u) == '0') {
                    break;
                }
                long next = Long.parseLong(num.substring(u, i + 1));
                if (u == 0) {
                    dfs(i + 1, next, next, path + next);
                } else {
                    dfs(i + 1, next, curr + next, path + "+" + next);
                    dfs(i + 1, -next, curr - next, path + "-" + next);
                    dfs(i + 1, prev * next, curr - prev + prev * next, path + "*" + next);
                }
            }
        }

    }

}
