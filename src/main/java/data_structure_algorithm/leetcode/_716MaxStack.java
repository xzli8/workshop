package data_structure_algorithm.leetcode;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

public class _716MaxStack {

    /**
     *  lintcode：https://www.lintcode.com/problem/859
     */

    public static class Solution1 {

        class MaxStack {

            private Deque<Integer> main = new ArrayDeque<>(), help = new ArrayDeque<>();

            public MaxStack() {
            }

            public void push(int x) {
                main.push(x);
                help.push(help.isEmpty() ? x : Math.max(x, help.peek()));
            }

            public int pop() {
                help.pop();
                return main.pop();
            }

            public int top() {
                return main.peek();
            }

            public int peekMax() {
                return help.peek();
            }

            public int popMax() {
                int max = peekMax();
                Deque<Integer> tmp = new ArrayDeque<>();
                while (top() != max) tmp.push(pop());
                pop();
                while (!tmp.isEmpty()) push(tmp.pop());
                return max;
            }
        }


        @Test
        public void test() {
            MaxStack maxStack = new MaxStack();

            maxStack.push(-137);
            maxStack.top();
            maxStack.popMax();
            maxStack.push(99);
            maxStack.popMax();
            maxStack.push(-927);
            maxStack.push(436);
            maxStack.pop();
            maxStack.peekMax();
            maxStack.popMax();
            maxStack.push(-593);
            maxStack.popMax();
            maxStack.push(-245);
            maxStack.popMax();
            maxStack.push(730);
            maxStack.top();
            maxStack.peekMax();
            maxStack.push(-391);
            maxStack.top();
            maxStack.pop();
            maxStack.popMax();
            maxStack.push(924);
            maxStack.peekMax();
            maxStack.pop();
            maxStack.push(722);
            maxStack.top();
            maxStack.peekMax();
            maxStack.top();
            maxStack.top();
            maxStack.popMax();
            maxStack.push(232);
            maxStack.push(-531);
            maxStack.popMax();
            maxStack.popMax();
            maxStack.push(-812);
            maxStack.popMax();
            maxStack.push(569);
            maxStack.popMax();
            maxStack.push(968);
            maxStack.push(-454);
            maxStack.push(966);
            maxStack.push(-429);
            maxStack.top();
            maxStack.top();
            maxStack.peekMax();
            maxStack.popMax();
            maxStack.push(-699);
            maxStack.push(32);
            maxStack.popMax();
            maxStack.push(-821);
            maxStack.pop();
            maxStack.push(260);
            maxStack.popMax();
            maxStack.popMax();
            maxStack.push(572);
            maxStack.top();
            maxStack.peekMax();
            maxStack.push(-702);
            maxStack.push(-748);
            maxStack.top();
            maxStack.popMax();
            maxStack.top();
            maxStack.push(603);
            maxStack.top();
            maxStack.popMax();
            maxStack.pop();
            maxStack.pop();
            maxStack.push(138);
            maxStack.popMax();
            maxStack.push(-224);
            maxStack.popMax();
            maxStack.pop();
            maxStack.push(-140);
            maxStack.pop();
            maxStack.push(787);
            maxStack.peekMax();
            maxStack.pop();
            maxStack.top();
            maxStack.pop();
            maxStack.push(944);
            maxStack.peekMax();
            maxStack.push(-918);
            maxStack.popMax();
            maxStack.push(24);
            maxStack.popMax();
        }

    }


}
