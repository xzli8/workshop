package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class _224calculate {

    public static class Solution1 {

        /**
         两个栈：操作数栈 + 操作符栈
         */
        public int calculate(String s) {

            // 操作数栈 + 操作符栈
            Deque<Integer> numsStack = new ArrayDeque<>();
            Deque<Character> opsStack = new ArrayDeque<>();

            // 遍历表达式
            StringBuilder sb = new StringBuilder();
            char preOp = ' ';
            for (char c : s.toCharArray()) {
                // 如果当前字符是空格，跳过
                if (c == ' ') {
                    continue;
                }

                // 如果当前字符是数字，先记录下来，后面转换成int
                if (c >= '0' && c <= '9') {
                    sb.append(c);
                }
                // 当前字符是操作符(符号或括号)
                else {
                    // 当操作数不为空时，将其转换成数字并压入操作数栈
                    // 和运算符没有括号的情况相比，这里需要判空
                    if (sb.length() != 0) {
                        numsStack.push(str2int(sb.reverse().toString()));
                        sb.setLength(0);
                    }

                    // 当前字符是左括号，压入操作符栈，作为后面对应右括号的终止标识
                    if (c == '(') {
                        opsStack.push(c);
                    }
                    // 当前字符是右括号，从操作数栈和操作符栈中弹出元素进行计算，直到遇到左括号
                    else if (c == ')') {
                        // 有右括号必然有左括号，所以不需要判空
                        while (opsStack.peek() != '(') {
                            cal(numsStack, opsStack);
                        }

                        // 弹出左括号
                        opsStack.pop();
                    }
                    // 当前字符是符号
                    else {
                        // 如果当前运算符是一元运算符(-1, (-2+3))，在前面补上一个0
                        if (preOp == ' ' || preOp == '(') {
                            numsStack.push(0);
                        }

                        // 计算，直到操作符栈为空或者遇到左括号为止
                        // 只有加减，没有乘除，不需要考虑运算符优先级
                        while (!opsStack.isEmpty() && opsStack.peek() != '(') {
                            cal(numsStack, opsStack);
                        }
                        opsStack.push(c);
                    }
                }

                // 更新preOp
                preOp = c;
            }

            // 当操作数不为空时，将其转换成数字并压入操作数栈
            // 和运算符没有括号的情况相比，这里需要判空
            if (sb.length() != 0) {
                numsStack.push(str2int(sb.reverse().toString()));
                sb.setLength(0);
            }

            // 计算剩余表达式
            while (!opsStack.isEmpty()) {
                cal(numsStack, opsStack);
            }
            return numsStack.pop();
        }

        // 字符串转换成数字
        public int str2int(String s) {
            int sum = 0, factor = 1;
            for (char c : s.toCharArray()) {
                sum += (c - '0') * factor;
                factor *= 10;
            }
            return sum;
        }

        // 计算
        public void cal(Deque<Integer> numsStack, Deque<Character> opsStack) {
            int num2 = numsStack.pop(), num1 = numsStack.pop();
            char op = opsStack.pop();
            if (op == '+') {
                numsStack.push(num1 + num2);
            } else {
                numsStack.push(num1 - num2);
            }
        }

    }

}
