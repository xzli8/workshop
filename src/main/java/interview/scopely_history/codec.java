package interview.scopely_history;

public class codec {

    /**
     * 解码语法（Decode 是固定的）
     *
     * 一个 token 严格由 3 个字符组成：
     *
     * <单个数字 count>  +  'x'  +  <任意一个字符>
     *
     * 它的含义是：把那个字符重复 count 次。
     *
     * 3xa  ->  aaa
     * 2x3  ->  33
     * 1xx  ->  x
     *
     * 解码器扫描字符串：遇到"数字 + x + 字符"就当成 token 展开；否则当成普通字面量原样输出。
     *
     * 要设计：Encode
     *
     * 满足两个目标：
     *
     * 1. 普通字符串原样保留：像 "abcd" 编码后还是 "abcd"，不能膨胀
     * 2. 往返安全：Decode(Encode(input)) == input 永远成立
     */

    public static class Solution1 {

        // 第一步：精确写出 Decode（我们要让 Encode 当它的逆）
        public String decode(String s) {
            StringBuilder sb = new StringBuilder();
            int i = 0, n = s.length();
            while (i < n) {
                // token 占 3 个字符: 数字 + 'x' + 任意字符
                if (i + 2 < n && Character.isDigit(s.charAt(i)) && s.charAt(i + 1) == 'x') {
                    int count = s.charAt(i) - '0';
                    char ch = s.charAt(i + 2);
                    for (int k = 0; k < count; k++) sb.append(ch);
                    i += 3;
                } else {
                    sb.append(s.charAt(i));     // 普通字面量
                    i++;
                }
            }
            return sb.toString();
        }

        // Encode
        public String encode(String s) {
            StringBuilder sb = new StringBuilder();
            int n = s.length(), i = 0;
            while (i < n) {
                char c = s.charAt(i);
                int run = 1;                                    // 数右边连续相同字符
                while (i + run < n && s.charAt(i + run) == c && run < 9) run++;  // count是单数字, 最多9

                if (run >= 4) {                                 // 4个以上才压缩划算(token占3字符)
                    sb.append((char) ('0' + run)).append('x').append(c);
                    i += run;
                } else if (Character.isDigit(c) && i + 1 < n && s.charAt(i + 1) == 'x') {
                    sb.append("1x").append(c);                  // 危险数字, 转义
                    i++;
                } else {
                    sb.append(c);                               // 原样
                    i++;
                }
            }
            return sb.toString();
        }

    }

}
