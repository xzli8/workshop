package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class _394decodeString {

    public static class Solution1 {

        /**
         栈
         */
        public String decodeString(String s) {
            // 记录次数
            Deque<Integer> numsStack = new ArrayDeque<>();
            // 记录字符
            Deque<Character> strStack = new ArrayDeque<>();

            // 遍历字符串
            StringBuilder sb = new StringBuilder();
            for (char c : s.toCharArray()) {
                // 当前字符是数字，先记录下来，后面再转换成int
                if (c >= '0' && c <= '9') {
                    sb.append(c);
                }
                // 当前字符是括号或者字母
                else {
                    // 当数字不为空时，转换成int并压入数字栈
                    if (sb.length() != 0) {
                        numsStack.push(str2int(sb.reverse().toString()));
                        sb.setLength(0);
                    }

                    // 当前字符是左括号，压入字符栈，作为后面对应右括号的结束标志
                    if (c == '[') {
                        strStack.push(c);
                    }
                    // 当前字符是右括号，从字符栈中取出元素，再从数字栈中取出元素，再将结果压入字符栈
                    else if (c == ']') {
                        // 有右括号必然有左括号，所以不用判空
                        StringBuilder tmp = new StringBuilder();
                        while (strStack.peek() != '[') {
                            tmp.append(strStack.pop());
                        }
                        tmp = tmp.reverse();

                        // 弹出左括号
                        strStack.pop();

                        // 有括号必然前面有数字，所以不用判空
                        int num = numsStack.pop();
                        StringBuilder res = new StringBuilder();
                        for (int i = 0; i < num; i++) {
                            res.append(tmp);
                        }
                        for (char ch : res.toString().toCharArray()) {
                            strStack.push(ch);
                        }
                    }
                    // 当前字符是字母，压入字符栈
                    else {
                        strStack.push(c);
                    }
                }
            }

            // 从字符栈中取出结果
            StringBuilder res = new StringBuilder();
            while (!strStack.isEmpty()) {
                res.append(strStack.pop());
            }
            return res.reverse().toString();
        }

        // 字符串转数字
        public int str2int(String s) {
            int sum = 0, factor = 1;
            for (char c : s.toCharArray()) {
                sum += (c - '0') * factor;
                factor *= 10;
            }
            return sum;
        }

    }



    public static class Solution2 {

        /**
         栈
         时间复杂度：O(n)
         空间复杂度：O(n)
         */
         public String decodeString(String s) {
             Integer multi = 0;
             StringBuilder res = new StringBuilder();
             Deque<Integer> stack_multi = new LinkedList<>();
             Deque<String> stack_res = new LinkedList<>();
             for (Character c : s.toCharArray()) {
                 if (c >= '0' && c <= '9') {
                     multi = multi * 10 + c - '0';
                 } else if (c >= 'a' && c <= 'z') {
                     res.append(c);
                 } else if (c == '[') {
                     stack_multi.push(multi);
                     stack_res.push(res.toString());
                     multi = 0;
                     res = new StringBuilder();
                 } else if (c == ']') {
                     StringBuilder tmp = new StringBuilder();
                     int cur_multi = stack_multi.pop();
                     for (int i = 0; i < cur_multi; i++) {
                         tmp.append(res);
                     }
                     res = new StringBuilder(stack_res.pop() + tmp);
                 }
             }
             return res.toString();
         }

    }

}
