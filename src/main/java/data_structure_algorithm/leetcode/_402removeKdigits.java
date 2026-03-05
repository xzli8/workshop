package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class _402removeKdigits {

    /**
     ref:https://leetcode.cn/problems/remove-k-digits/solutions/290203/yi-zhao-chi-bian-li-kou-si-dao-ti-ma-ma-zai-ye-b-5/
     */


    public static class Solution1 {

        /**
         贪心 + 单调栈
         Note: 两个相同位数的数字大小关系取决于第一个不同的数的大小，从前往后遍历，如果当前数字大于前一个数字，则丢弃当前数字
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public String removeKdigits(String num, int k) {
            // 用单调(递增)栈删除前k个逆序对
            Deque<Character> dq = new ArrayDeque<>();
            for (char c : num.toCharArray()) {
                while (!dq.isEmpty() && k > 0 && c < dq.peekLast()) {
                    dq.pollLast();
                    k--;
                }
                dq.offerLast(c);
            }

            // 如果不够k个(说明删除后时递增序列)，从后往前删除直到够k个
            for (int i = 0; i < k; i++) {
                dq.pollLast();
            }

            // 组装结果并返回(注意考虑"前导0"和"结果为0"的情况)
            StringBuilder sb = new StringBuilder();
            boolean leadingZero = true;
            while (!dq.isEmpty()) {
                char digit = dq.pollFirst();    // Deque，这里可以直接pollFirst，也可以pollLast(poll)，然后reverse
                if (leadingZero && digit == '0') {
                    continue;
                }
                leadingZero = false;
                sb.append(digit);
            }
            return sb.length() == 0 ? "0" : sb.toString();
        }

    }

}
