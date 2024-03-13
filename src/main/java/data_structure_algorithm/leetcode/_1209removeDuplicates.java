package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class _1209removeDuplicates {

    public static class Solution1 {

        /**
         栈:将元素依次入栈并统计元素数量。每次入栈判断是否和栈顶元素相同：如果与栈顶元素相同，那么将栈顶元素的数量加 1；如果栈顶元素数量达到 3，则将栈顶元素出栈；如果待入栈元素与栈顶元素不同，那么直接入栈并将该元素个数置为 1。遍历完字符串之后，将栈中剩余元素拼接即为答案。
         ref:https://leetcode.cn/problems/remove-all-adjacent-duplicates-in-string-ii/solutions/31528/zhan-python3-by-smoon1989/
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public String removeDuplicates(String s, int k) {
            Deque<int[]> stack = new ArrayDeque<>();    // 存储元素及其出现次数
            for (char c : s.toCharArray()) {
                int num = c - 'a';
                if (stack.isEmpty()) {
                    stack.push(new int[] {num , 1});
                } else {
                    if (stack.peek()[0] != num) {
                        stack.push(new int[] {num, 1});
                    } else if (stack.peek()[1] + 1 < k) {
                        stack.peek()[1] += 1;
                    } else {
                        stack.pop();
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) {
                int[] pair = stack.pop();
                for (int i = 0; i < pair[1]; i++) {
                    sb.append((char) (pair[0] + 'a'));
                }
            }
            return sb.reverse().toString();
        }

    }

}
