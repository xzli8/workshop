package data_structure_algorithm.leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class LCR_184MaxQueue {

    /**
     * Your MaxQueue object will be instantiated and called as such:
     * MaxQueue obj = new MaxQueue();
     * int param_1 = obj.max_value();
     * obj.push_back(value);
     * int param_3 = obj.pop_front();
     */

    public static class Solution1 {

        class MaxQueue {

            // 主队列
            private Queue<Integer> q;
            // 辅助队列，单调递减。每次插入value时，从辅助队列中依次取出所有比value小的元素，然后将value放入辅助队列尾
            private Deque<Integer> d;

            public MaxQueue() {
                q = new LinkedList<>();
                d = new LinkedList<>();
            }

            public int max_value() {
                if (d.isEmpty()) return -1;
                return d.peekFirst();
            }

            public void push_back(int value) {
                while (!d.isEmpty() && d.peekLast() < value) {
                    d.pollLast();
                }
                d.offerLast(value);
                q.offer(value);
            }

            public int pop_front() {
                if (q.isEmpty()) return -1;
                int res = q.poll();
                if (res == d.peekFirst()) {
                    d.pollFirst();
                }
                return res;
            }

        }

    }

}
