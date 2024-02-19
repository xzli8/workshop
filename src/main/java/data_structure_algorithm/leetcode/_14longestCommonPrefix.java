package data_structure_algorithm.leetcode;

public class _14longestCommonPrefix {

    public static class Solution0 {

        /**
         遍历(纵向扫描)
         时间复杂度：O(M * N)
         空间复杂度：O(1)
         */
        public String longestCommonPrefix(String[] strs) {
            // 计算最短字符串的长度
            int minLen = Integer.MAX_VALUE;
            for (String str : strs) minLen = Math.min(minLen, str.length());

            // 遍历所有字符串，求最长公共前缀
            int i = 0;
            while (i < minLen) {
                char c = strs[0].charAt(i);
                for (int j = 1; j < strs.length; j++) {
                    if (strs[j].charAt(i) != c) return strs[0].substring(0, i);
                }
                i++;
            }
            return strs[0].substring(0, i);
        }

    }



    public static class Solution1 {

        /**
         纵向扫描
         时间复杂度：O(mn)
         空间复杂度：O(1)
         */
        public String longestCommonPrefix(String[] strs) {
            if (strs.length == 0) {
                return "";
            }

            int minLen = Integer.MAX_VALUE;
            for (String str : strs) {
                minLen = Math.min(str.length(), minLen);
            }

            StringBuilder res = new StringBuilder();
            for (int i = 0; i < minLen; i++) {
                char c = strs[0].charAt(i);
                int count = strs.length - 1;
                for (int j = 1; j < strs.length; j++) {
                    if (strs[j].charAt(i) == c) {
                        count--;
                    } else {
                        break;
                    }
                }
                if (count == 0) {
                    res.append(c);
                } else {
                    break;  // 注意这里必须要"break"，反例:["cir", "car"]，不break，返回"cr"，正确结果是"c"
                }
            }
            return res.toString();
        }

    }

}
