package data_structure_algorithm.leetcode;

import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Deque;

public class _901StockSpanner {

    /**
     * Your StockSpanner object will be instantiated and called as such:
     * StockSpanner obj = new StockSpanner();
     * int param_1 = obj.next(price);
     */

    public static class Solution1 {

        class StockSpanner {

            /**
             单调栈（从栈底到栈顶元素依次递减）
             key: price
             value: 小于等于price的连续天数
             */
            private Deque<Pair<Integer, Integer>> dq = new ArrayDeque<>();

            public StockSpanner() {
            }

            public int next(int price) {
                int days = 1;
                while (!dq.isEmpty() && dq.peek().getKey() <= price) {
                    days += dq.peek().getValue();
                    dq.pop();
                }
                dq.push(new Pair<>(price, days));   // 这里需要将累加天数放回栈中保存，因为数据流会丢失过去的数据
                return days;
            }

        }

    }

}
