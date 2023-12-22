package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
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

            /**
             两个队列：利用辅助队列，在入队时将新入队元素放在队列开头位置
             */
            private Queue<Integer> main = new LinkedList<>();
            private Queue<Integer> help = new LinkedList<>();

            public MyStack() {
                // nothing to do here
            }

            public void push(int x) {
                // 先入到辅助队列中
                help.offer(x);

                // 将主队列中的元素依次加入到辅助队列中
                while (!main.isEmpty()) {
                    help.offer(main.poll());
                }

                // 交换主队列与辅助队列
                Queue<Integer> tmp = main;
                main = help;
                help = tmp;
            }

            public int pop() {
                return main.poll();
            }

            public int top() {
                return main.peek();
            }

            public boolean empty() {
                return main.isEmpty();
            }

        }

    }



    public static class Solution2 {

        class MyStack {

            /**
             一个队列：每次新元素入队后，将队尾元素倒腾到队头
             */
            private Queue<Integer> q = new ArrayDeque<>();

            public MyStack() {
                // nothing to do here
            }

            public void push(int x) {
                q.offer(x);
                int size = q.size();
                while (size-- > 1) {
                    q.offer(q.poll());
                }
            }

            public int pop() {
                return q.poll();
            }

            public int top() {
                return q.peek();
            }

            public boolean empty() {
                return q.isEmpty();
            }

        }

    }

}
