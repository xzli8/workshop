package data_structure_algorithm.leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class _227calculate {

    public static class Solution1 {

        /**
         两个栈：操作数栈 + 运算符栈
         时间复杂度：O(n)
         空间复杂度：O(n)
         */
        public int calculate(String s) {
            Deque<Integer> numStack = new LinkedList<>();
            Deque<Character> opsStack = new LinkedList<>();

            StringBuilder sb = new StringBuilder();
            for (char c : s.toCharArray()) {
                // 跳过空格（"3 2+4 - 9"这种不合法，可以不用考虑）
                if (c == ' ') {
                    continue;
                }
                // 如果是数字，累加进位（考虑到"123"这种多位数），参考leetcode-8
                if (c >= '0' && c <= '9') {
                    sb.append(c);
                } else {
                    // 如果是运算符，操作数入栈，操作数相关临时变量恢复初始状态
                    numStack.push(str2int(sb.reverse().toString()));
                    sb.setLength(0);

                    // 如果操作符栈为空，操作符直接入栈；否则，当操作符的优先级比栈顶操作符优先级高时入栈，
                    // 当操作符比栈顶操作符优先级低或相同时，取出栈顶操作符和两个操作数
                    // 进行运算后，将运算结果入操作数栈
                    if (opsStack.isEmpty()) {
                        opsStack.push(c);
                    } else {
                        // 注意这里是while，需要一直找到优先级比当前运算符低的或者运算符栈为空时才停止
                        while (!opsStack.isEmpty() && !opsPriority(c, opsStack.peek())) {
                            cal(numStack, opsStack);
                        }
                        opsStack.push(c);
                    }
                }
            }
            // 最后一个操作数入栈
            numStack.push(str2int(sb.reverse().toString()));

            // 弹出一个操作符和两个操作数，运算结果入操作数栈，直至操作符栈为空
            while (!opsStack.isEmpty()) {
                cal(numStack, opsStack);
            }
            return numStack.pop();
        }

        private int str2int(String s) {
            int sum = 0, factor = 1;
            for (char c : s.toCharArray()) {
                int tmp = (c - '0') * factor;
                sum += tmp;
                factor *= 10;
            }
            return sum;
        }

        /**
         判断两个操作符的优先级
         if (c1 > c2) return true;
         else return false;
         */
        private boolean opsPriority(char c1, char c2) {
            return (c1 == '*' || c1 == '/') && (c2 == '+' || c2 == '-');
        }

        /**
         从操作符栈中取出一个操作符，从操作数栈中取出两个操作数，进行计算后将结果放在操作数栈中
         注意：操作数的顺序
         */
        private void cal(Deque<Integer> numStack, Deque<Character> opsStack) {
            char c = opsStack.pop();
            int num1 = numStack.pop(), num2 = numStack.pop();
            if (c == '+') {
                numStack.push(num2 + num1);
            } else if (c == '-') {
                numStack.push(num2 - num1);
            } else if (c == '*') {
                numStack.push(num2 * num1);
            } else {
                numStack.push(num2 / num1);
            }
        }

    }

}
