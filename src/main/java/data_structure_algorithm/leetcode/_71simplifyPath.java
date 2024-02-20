package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class _71simplifyPath {

    public static class Solution1 {

        /**
         栈：根据"/"将path分隔成数组，遍历数组元素，当字符串为"."或者""时，无需处理；当字符串是".."时，pop出栈顶元素(回到上一级目录)；当字符串为其他字符时，入栈
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public String simplifyPath(String path) {
            // 按照"/"分割字符串
            String[] strs = path.split("/");

            // 遍历字符串，用栈简化路径
            Deque<String> s = new ArrayDeque<>();
            for (String str : strs) {
                if (str.equals("") || str.equals(".")) {
                    continue;
                } else if (str.equals("..")) {
                    if (!s.isEmpty()) s.pop();
                } else {
                    s.push(str);
                }
            }

            // 构建返回结果
            if (s.isEmpty()) return "/";
            StringBuilder sb = new StringBuilder();
            while (!s.isEmpty()) sb.append("/").append(s.pollLast());
            return sb.toString();
        }

    }

}
