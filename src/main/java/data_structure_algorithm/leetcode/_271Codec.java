package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _271Codec {

    /**
     * ref: https://leetcode.doocs.org/lc/271/
     */

    public static class Solution1 {

        public class Codec {

            /**
             * 用 1 个 char 存长度
             * - Java 的 char 是 16 位无符号整数（一个 UTF-16 码元），范围 0 ~ 2^16 - 1 = 65535
             * - 长度 <= 65535：(char) 强转能原样存下，解码时 charAt 取回来就是原长度
             * - 长度 > 65535：强转只保留低 16 位，长度被截断
             *     - 例：长度 70000 存成 70000 - 65536 = 4464，解码只截 4464 个字符，后面全部错位
             * - 原题限制每个字符串长度 <= 200，所以能过；面试时要主动说出这个限制
             * - 要支持更长：固定用 2 个 char（32 位）存长度，或者用 Solution2
             */
            // Encodes a list of strings to a single string.
            public String encode(List<String> strs) {
                StringBuilder ans = new StringBuilder();
                for (String s : strs) {
                    ans.append((char) s.length()).append(s);
                }
                return ans.toString();
            }

            // Decodes a single string to a list of strings.
            public List<String> decode(String s) {
                List<String> ans = new ArrayList<>();
                int i = 0, n = s.length();
                while (i < n) {
                    // 长度被压进 1 个 char，超了就溢出。长度上限是65535（char 范围）
                    int size = s.charAt(i++);
                    ans.add(s.substring(i, i + size));
                    i += size;
                }
                return ans;
            }
        }

        // Your Codec object will be instantiated and called as such:
        // Codec codec = new Codec();
        // codec.decode(codec.encode(strs));

    }


    public static class Solution2 {

        /**
         * 每段编码成：长度的十进制数字 + '#' + 内容，长度不受 char 范围 65535 限制
         * - 字符集包含 '#'：原题字符串可以含任意 256 个 ASCII 字符，内容里可能有 '#'
         * - 依然可行：解码只在每段开头找 '#'，从不在内容里找
         *     - 解码指针 i 永远停在某段开头，后面先是若干位数字，数字里不可能有 '#'
         *     - 所以从 i 往后第一个 '#' 一定是分隔符
         *     - 读出长度 len 后直接截 len 个字符，不管里面有没有 '#'；i 跳到 j + 1 + len，正好是下一段开头
         * - 例：["a#b", "#"] → "3#a#b1##"
         *     - i = 0：读到 3，截 "a#b"（中间的 '#' 是内容），i 跳到 5
         *     - i = 5：读到 1，截 "#"，i 跳到 8，结束
         * - 对比错误写法：只用 '#' 连接再 split("#")，内容里的 '#' 会被当成分隔符
         * - 关键是长度前缀：内容靠长度精确截取，'#' 只标出长度数字在哪里结束，换成任何非数字字符都行
         */
        public String encode(List<String> strs) {
            StringBuilder sb = new StringBuilder();
            for (String s : strs) {
                sb.append(s.length()).append('#').append(s);
            }
            return sb.toString();
        }

        // 解码: 先读长度, 再按长度精确截取
        public List<String> decode(String s) {
            List<String> res = new ArrayList<>();
            int i = 0, n = s.length();
            while (i < n) {
                int j = i;
                while (s.charAt(j) != '#') j++;                 // 找到本段的"长度#"分隔符（i 在段开头，[i, j) 只有数字，第一个 '#' 一定不是内容）
                int len = Integer.parseInt(s.substring(i, j));  // 解析出长度 L
                String str = s.substring(j + 1, j + 1 + len);   // 从#后精确取 L 个字符
                res.add(str);
                i = j + 1 + len;                                // 跳到下一段开头
            }
            return res;
        }

    }

}
