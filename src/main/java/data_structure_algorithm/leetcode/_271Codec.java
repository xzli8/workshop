package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _271Codec {

    /**
     * ref: https://leetcode.doocs.org/lc/271/
     */

    public static class Solution1 {

        public class Codec {

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

        // 编码: 每段前面写"长度#"，绝对安全的写法，不受限于char范围65535
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
                while (s.charAt(j) != '#') j++;                 // 找到本段的"长度#"分隔符
                int len = Integer.parseInt(s.substring(i, j));  // 解析出长度 L
                String str = s.substring(j + 1, j + 1 + len);   // 从#后精确取 L 个字符
                res.add(str);
                i = j + 1 + len;                                // 跳到下一段开头
            }
            return res;
        }

    }

}
