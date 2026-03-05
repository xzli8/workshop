package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class _155MinStack {

    /**
     * Your MinStack object will be instantiated and called as such:
     * MinStack obj = new MinStack();
     * obj.push(val);
     * obj.pop();
     * int param_3 = obj.top();
     * int param_4 = obj.getMin();
     */


    public static class Solution0 {

        class MinStack {

            private Deque<Integer> main = new ArrayDeque<>(), help = new ArrayDeque<>();

            public MinStack() {
            }

            public void push(int val) {
                main.push(val);
                if (help.isEmpty() || val <= help.peek()) help.push(val);
            }

            public void pop() {
                int val = main.pop();
                if (val == help.peek()) help.pop();
            }

            public int top() {
                return main.peek();
            }

            public int getMin() {
                return help.peek();
            }

        }

        // 改进版，不用辅助栈。空间复杂度：O(1)
        class MinStackII {
            // 栈里不存原值，而存「当前值与当时最小值的差值」即 diff = val - min（min 是 push 那一刻的最小值）。差值的符号承载了关键信息：
            // diff >= 0：这个元素 push 时没有比当时的最小值更小，所以它没改变 min。解码真实值 = min + diff。
            // diff < 0：这个元素 push 时比当时最小值还小，于是它成了新的最小值，min 被更新成了它。此时栈顶的真实值就等于 min 本身（因为它就是当前最小）。
            private final Deque<Long> stack = new ArrayDeque<>();
            private long min;                       // 当前最小值（标量，取代辅助栈）

            public void push(int val) {
                if (stack.isEmpty()) {
                    min = val;
                    stack.push(0L);                 // 第一个元素，差值记 0
                } else {
                    stack.push((long) val - min);   // 存「值 - 当时最小值」，用 long 防溢出
                    if (val < min) min = val;        // 比当前最小还小，刷新 min
                }
            }

            public void pop() {
                long diff = stack.pop();
                if (diff < 0) min = min - diff;     // 它当时是新最小，还原上一个最小
                // diff >= 0：它没改变过 min，min 不动
            }

            public int top() {
                long diff = stack.peek();
                return (int) (diff >= 0 ? min + diff : min);
            }

            public int getMin() {
                return (int) min;
            }
        }

    }



    public static class Solution1 {

        class MinStack {

            private Deque<Integer> main = new ArrayDeque<>(), help = new ArrayDeque<>();

            public MinStack() {

            }

            public void push(int val) {
                main.push(val);
                help.push(help.isEmpty() ? val : Math.min(val, help.peek()));
            }

            public void pop() {
                main.pop();
                help.pop();
            }

            public int top() {
                return main.peek();
            }

            public int getMin() {
                return help.peek();
            }
        }

    }



    public static class Solution2 {

        class MinStack {

            private Deque<Integer> mainStack;
            private Deque<Integer> minStack;

            public MinStack() {
                mainStack = new LinkedList<>();
                minStack = new LinkedList<>();
                minStack.push(Integer.MAX_VALUE);
            }

            public void push(int val) {
                mainStack.push(val);
                minStack.push(Math.min(minStack.peek(), val));
            }

            public void pop() {
                mainStack.pop();
                minStack.pop();
            }

            public int top() {
                return mainStack.peek();
            }

            public int getMin() {
                return minStack.peek();
            }
        }

    }


}
