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
            private Queue<Integer> main;
            // 辅助队列，单调递减。每次插入value时，从辅助队列中依次取出所有比value小的元素，然后将value放入辅助队列尾
            private Deque<Integer> help;

            public MaxQueue() {
                main = new LinkedList<>();
                help = new LinkedList<>();
            }

            public int max_value() {
                if (help.isEmpty()) return -1;
                return help.peekFirst();
            }

            public void push_back(int value) {
                while (!help.isEmpty() && help.peekLast() < value) {
                    help.pollLast();
                }
                help.offerLast(value);
                main.offer(value);
            }

            public int pop_front() {
                if (main.isEmpty()) return -1;
                int res = main.poll();
                if (res == help.peekFirst()) {
                    help.pollFirst();
                }
                return res;
            }

        }

    }

}
