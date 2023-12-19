package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Mianshiti_03_05SortedStack {

    /**
     * Your SortedStack object will be instantiated and called as such:
     * SortedStack obj = new SortedStack();
     * obj.push(val);
     * obj.pop();
     * int param_3 = obj.peek();
     * boolean param_4 = obj.isEmpty();
     */

    public static class Solution1 {

        class SortedStack {

            /**
             类似“双栈排序”，每次调用push时，进行一次双栈排序
             */

            // 主栈
            private Deque<Integer> main;

            // 辅助栈
            private Deque<Integer> help;

            public SortedStack() {
                main = new ArrayDeque<>();
                help = new ArrayDeque<>();
            }

            // 将栈顶元素不断弹出压入辅助栈备用，找到val的插入位置，然后再将辅助栈中的元素弹出压入主栈
            // 保持主栈(从栈顶到栈底)单调递增
            public void push(int val) {

                // 当主栈不为空，且栈顶元素小于val时，将栈顶元素弹出，压入辅助栈备用
                while (!main.isEmpty() && main.peek() < val) {
                    help.push(main.pop());
                }

                // 将val插入到主栈中
                main.push(val);

                // 将辅助栈中的元素弹出压入主栈
                while (!help.isEmpty()) {
                    main.push(help.pop());
                }
            }

            public void pop() {
                if (!main.isEmpty()) {
                    main.pop();
                }
            }

            public int peek() {
                return main.isEmpty() ? -1 : main.peek();
            }

            public boolean isEmpty() {
                return main.isEmpty();
            }

        }

    }

}
