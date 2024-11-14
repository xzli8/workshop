package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class _1472BrowserHistory {

    public static class Solution1 {

        /**
         Double Stack
         TC: O(1)
         SC: O(N)
         */
        class BrowserHistory {

            private final Deque<String> back = new ArrayDeque<>(), forward = new ArrayDeque<>();

            public BrowserHistory(String homepage) {
                back.push(homepage);
            }

            public void visit(String url) {
                forward.clear();
                back.push(url);
            }

            public String back(int steps) {
                steps = Math.min(steps, back.size() - 1);
                for (int i = 0; i < steps; i++) {
                    forward.push(back.pop());
                }
                return back.peek();
            }

            public String forward(int steps) {
                steps = Math.min(steps, forward.size());
                for (int i = 0; i < steps; i++) {
                    back.push(forward.pop());
                }
                return back.peek();
            }

        }

        /**
         * Your BrowserHistory object will be instantiated and called as such:
         * BrowserHistory obj = new BrowserHistory(homepage);
         * obj.visit(url);
         * String param_2 = obj.back(steps);
         * String param_3 = obj.forward(steps);
         */

    }

}
