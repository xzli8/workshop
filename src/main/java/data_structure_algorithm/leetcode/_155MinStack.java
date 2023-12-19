package data_structure_algorithm.leetcode;

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

    public static class Solution1 {

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
