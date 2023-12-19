package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class _946validateStackSequences {

    public static class Solution1 {

        /**
         辅助栈：用辅助栈记录入栈顺序，每入栈一个元素后，判断栈顶元素是否与出栈序列当前元素相同，如果相同就出栈
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public boolean validateStackSequences(int[] pushed, int[] popped) {
            int n = pushed.length;
            Deque<Integer> s = new ArrayDeque<>(n);

            // 遍历入栈序列，将入栈序列的每个元素依次入栈
            for (int i = 0, j = 0; i < n; i++) {

                // 先入栈
                s.push(pushed[i]);

                // 当栈顶元素等于出栈队列的当前元素时，出栈。循环直到条件不满足为止
                while (!s.isEmpty() && s.peek() == popped[j]) {
                    s.pop();
                    j++;
                }
            }

            // 入栈队列的每个元素都依次入栈了，如果出栈队列合法，那么每个元素都会被pop出去，栈为空
            return s.isEmpty();
        }


    }

}
