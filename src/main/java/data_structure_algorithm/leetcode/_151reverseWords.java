package data_structure_algorithm.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class _151reverseWords {

    public static class Solution1 {

        /**
         split + reverse + join
         时间复杂度：O(n)
         空间复杂度：O(n)
         */
         public String reverseWords(String s) {
             // 正则"\\s+"匹配连续的空白字符
             List<String> list = Arrays.asList(s.trim().split("\\s+"));
             Collections.reverse(list);
             return String.join(" ", list);
         }

    }



    public static class Solution2 {

        /**
         左右双指针：从后往前遍历字符串，同时去除空格
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public String reverseWords(String s) {
             StringBuilder sb = new StringBuilder();
             int left = s.length() - 1, right = 0;
             while (left >= 0) {
                 // 如果left指向的字符不是空格，说明left指向了某个单词的结尾
                 if (s.charAt(left) != ' ') {
                     right = left;
                     // 再让left指向该单词开头的前一个字符
                     while (left >= 0 && s.charAt(left) != ' ') {
                         left--;
                     }
                     sb.append(s.substring(left+1, right+1)).append(" ");
                 }
                 left--;
             }
             return sb.toString().trim();
         }

    }



    public static class Solution3 {

        /**
         去除空格 + 反转整个字符串 + 反转每个字符串
         时间复杂度：O(N)
         空间复杂度：O(N)，如果String是不可变类型的语言，比如C++，只需要O(1)的空间
         */
        public String reverseWords(String s) {
            StringBuilder sb = trimSpaces(s);

            // 翻转字符串
            reverse(sb, 0, sb.length() - 1);

            // 翻转每个单词
            reverseEachWord(sb);

            return sb.toString();
        }

        public StringBuilder trimSpaces(String s) {
            int left = 0, right = s.length() - 1;
            // 去掉字符串开头的空白字符
            while (left <= right && s.charAt(left) == ' ') {
                ++left;
            }

            // 去掉字符串末尾的空白字符
            while (left <= right && s.charAt(right) == ' ') {
                --right;
            }

            // 将字符串间多余的空白字符去除
            StringBuilder sb = new StringBuilder();
            while (left <= right) {
                char c = s.charAt(left);

                if (c != ' ') {
                    sb.append(c);
                } else if (sb.charAt(sb.length() - 1) != ' ') {
                    sb.append(c);
                }

                ++left;
            }
            return sb;
        }

        public void reverse(StringBuilder sb, int left, int right) {
            while (left < right) {
                char tmp = sb.charAt(left);
                sb.setCharAt(left++, sb.charAt(right));
                sb.setCharAt(right--, tmp);
            }
        }

        public void reverseEachWord(StringBuilder sb) {
            int n = sb.length();
            int start = 0, end = 0;

            while (start < n) {
                // 循环至单词的末尾
                while (end < n && sb.charAt(end) != ' ') {
                    ++end;
                }
                // 翻转单词
                reverse(sb, start, end - 1);
                // 更新start，去找下一个单词
                start = end + 1;
                ++end;
            }
        }

    }



}
