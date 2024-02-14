package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class _232MyQueue {

    /**
     * Your MyQueue object will be instantiated and called as such:
     * MyQueue obj = new MyQueue();
     * obj.push(x);
     * int param_2 = obj.pop();
     * int param_3 = obj.peek();
     * boolean param_4 = obj.empty();
     */

    public static class Solution1 {

        class MyQueue {

            private Deque<Integer> in = new ArrayDeque<>();

            private Deque<Integer> out = new ArrayDeque<>();

            public MyQueue() {

            }

            public void push(int x) {
                in.push(x);
            }

            public int pop() {
                if (!out.isEmpty()) {
                    return out.pop();
                }
                while (!in.isEmpty()) {
                    out.push(in.pop());
                }
                return out.pop();
            }

            public int peek() {
                if (!out.isEmpty()) {
                    return out.peek();
                }
                while (!in.isEmpty()) {
                    out.push(in.pop());
                }
                return out.peek();
            }

            public boolean empty() {
                return in.isEmpty() && out.isEmpty();
            }

        }

    }



    public static class Solution2 {

        class MyQueue {

            private Deque<Integer> in = new ArrayDeque<>(), out = new ArrayDeque<>();

            public MyQueue() {

            }

            public void push(int x) {
                in.push(x);
            }

            public int pop() {
                if (out.isEmpty()) {
                    while (!in.isEmpty()) out.push(in.pop());
                }
                return out.pop();
            }

            public int peek() {
                if (out.isEmpty()) {
                    while (!in.isEmpty()) out.push(in.pop());
                }
                return out.peek();
            }

            public boolean empty() {
                return in.isEmpty() && out.isEmpty();
            }

        }

    }

}
