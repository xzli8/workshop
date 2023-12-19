package data_structure_algorithm.leetcode;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

public class Buchongti_24StackSort {

    /**
     * 题目链接：https://mp.weixin.qq.com/s/g_AqwsSEUwlRSevnStPkEA
     */

    @Test
    public void test() {
        Deque<Integer> s = new ArrayDeque<>();
        s.push(4);
        s.push(2);
        s.push(1);
        s.push(3);
        System.out.println(stackSort(s));
    }

    /**
     * 双栈排序：用一个辅助栈来倒腾，保证倒腾的时候辅助栈中的元素单调递增
     */
    public Deque<Integer> stackSort(Deque<Integer> s) {

        // 辅助栈
        Deque<Integer> help = new ArrayDeque<>(s.size());

        // 当主栈不为空时，需要一直倒腾
        while (!s.isEmpty()) {
            // 先将主栈栈顶元素拿出来，判断是否能加入到辅助栈中
            int tmp = s.pop();

            // 当辅助栈不为空时，若辅助栈栈顶元素大于主栈栈顶元素，那么主栈栈顶元素不能直接入栈，需要先将辅助栈栈顶元素弹出，加入主栈中
            while (!help.isEmpty() && help.peek() > tmp) {
                s.push(help.pop());
            }

            // 主栈栈顶元素压入辅助栈
            help.push(tmp);
        }

        // 倒腾到主栈为空后，辅助栈中就是单调递增的结果
        return help;
    }

}
