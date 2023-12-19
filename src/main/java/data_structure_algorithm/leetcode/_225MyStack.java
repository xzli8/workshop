package data_structure_algorithm.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class _225MyStack {

    /**
     * Your MyStack object will be instantiated and called as such:
     * MyStack obj = new MyStack();
     * obj.push(x);
     * int param_2 = obj.pop();
     * int param_3 = obj.top();
     * boolean param_4 = obj.empty();
     */

    public static class Solution1 {

        class MyStack {

            // 利用辅助队列，在入队时将新入队元素放在队列开头位置

            // 主队列
            private Queue<Integer> q1 = new LinkedList<>();
            // 辅助队列(仅在入队时使用，其余时刻保持为空)
            private Queue<Integer> q2 = new LinkedList<>();

            public MyStack() {
                // nothing to do here
            }

            public void push(int x) {
                // 先入到辅助队列中
                q2.offer(x);

                // 将主队列中的元素依次加入到辅助队列中
                while (!q1.isEmpty()) {
                    q2.offer(q1.poll());
                }

                // 交换主队列与辅助队列
                Queue<Integer> tmp = q1;
                q1 = q2;
                q2 = tmp;
            }

            public int pop() {
                return q1.poll();
            }

            public int top() {
                return q1.peek();
            }

            public boolean empty() {
                return q1.isEmpty();
            }

        }

    }

}
