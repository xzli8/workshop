package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class _772calculate {

    /**
     * 题目链接：https://www.lintcode.com/problem/849/
     */

    public static class Solution1 {

        public int calculate(String s) {

            // 操作数栈 + 操作符栈
            Deque<Integer> numsStack = new ArrayDeque<>();
            Deque<Character> opsStack = new ArrayDeque<>();

            // 遍历表达式
            StringBuilder sb = new StringBuilder();
            char preOp = ' ';
            for (char c : s.toCharArray()) {
                // 跳过空格
                if (c == ' ') {
                    continue;
                }

                // 当前字符是数字，先记录下来，后面转成int
                if (c >= '0' && c <= '9') {
                    sb.append(c);
                }
                // 当前字符是操作符(符号或括号)
                else {
                    // 如果数字不为空，转成int并压入操作数栈
                    if (sb.length() != 0) {
                        numsStack.push(str2int(sb.reverse().toString()));
                        sb.setLength(0);
                    }

                    // 当前字符是左括号，压入操作符栈，作为后面对应右括号的终止标识
                    if (c == '(') {
                        opsStack.push((c));
                    }
                    // 当前字符是右括号，从操作数栈和操作符栈中取出元素运算，直到遇到对应左括号
                    else if (c == ')') {
                        // 有右括号必然有左括号，不需要判空
                        while (opsStack.peek() != '(') {
                            cal(numsStack, opsStack);
                        }

                        // 弹出左括号
                        opsStack.pop();
                    }
                    // 当前字符是符号
                    else {
                        // 如果是一元运算符，前面补0
                        if (preOp == ' ' || preOp == '(') {
                            numsStack.push(0);
                        }

                        // 计算，直到运算符栈为空或者遇到左括号或者栈顶运算符优先级比当前运算符低
                        // 注意运算符的优先级：乘除优先，其次加减
                        while (!opsStack.isEmpty() && opsStack.peek() != '(' && !opPriority(c, opsStack.peek())) {
                            cal(numsStack, opsStack);
                        }
                        opsStack.push(c);
                    }
                }

                // 更新preOp
                preOp = c;
            }

            // 如果数字不为空，转成int并压入操作数栈
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

        // 字符串转数字
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
            } else if (op == '-') {
                numsStack.push(num1 - num2);
            } else if (op == '*') {
                numsStack.push(num1 * num2);
            } else {
                numsStack.push(num1 / num2);
            }
        }

        // 运算符优先级判断
        public boolean opPriority(char op1, char op2) {
            return (op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-');
        }

    }

}
