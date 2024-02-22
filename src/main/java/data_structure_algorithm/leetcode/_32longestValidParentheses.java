package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class _32longestValidParentheses {

    public static class Solution1 {

        /**
         栈：用栈遍历一遍，将无法匹配的括号的位置标记为false，转换为寻找最长连续true的长度
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public int longestValidParentheses(String s) {
             // 初始化标记数组
             int n = s.length();
             boolean[] mark = new boolean[n];
             Arrays.fill(mark, true);

             // 用栈遍历字符串，并更新标记数组
             Deque<Integer> st = new LinkedList<>();
             for (int i = 0; i < s.length(); i++) {
                 if (s.charAt(i) == '(') {
                     st.push(i);
                 } else {
                     // 多余的右括号不需要，标记为false
                     if (st.isEmpty()) mark[i] = false;
                     else st.pop();
                 }
             }
             // 未匹配的左括号不需要，标记为false
             while (!st.isEmpty()) mark[st.pop()] = false;

             // 寻找最长连续true的长度
             int len = 0, maxLen = 0;
             for (int i = 0; i < n; i++) {
                 if (!mark[i]) {
                     len = 0;
                     continue;
                 }
                 len++;
                 maxLen = Math.max(maxLen, len);
             }
             return maxLen;
         }

    }



    public static class Solution2 {

        /**
         栈：保持栈底元素为当前已遍历过的元素中最后一个没有被匹配的右括号的下标
         ref:https://leetcode.cn/problems/longest-valid-parentheses/solutions/314683/zui-chang-you-xiao-gua-hao-by-leetcode-solution/
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public int longestValidParentheses(String s) {
             int maxLen = 0;
             Deque<Integer> stack = new ArrayDeque<>();
             stack.push(-1);  //如果一开始栈为空，第一个字符为左括号的时候我们会将其放入栈中，这样就不满足提及的「最后一个没有被匹配的右括号的下标」，为了保持统一，我们在一开始的时候往栈中放入一个值为−1的元素。
             for (int i = 0; i < s.length(); i++) {
                 if (s.charAt(i) == '(') stack.push(i);
                 else {
                     stack.pop();
                     if (stack.isEmpty()) stack.push(i);
                     else maxLen = Math.max(maxLen, i - stack.peek());
                 }
             }
             return maxLen;
         }

    }



    public static class Solution3 {

        /**
         动态规划
         ref:https://leetcode.cn/problems/longest-valid-parentheses/solutions/206995/dong-tai-gui-hua-si-lu-xiang-jie-c-by-zhanganan042/
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int longestValidParentheses(String s) {
            // 定义状态：dp[i]表示以s[i]结尾的字符串的最长有效括号
            int n = s.length();
            int[] dp = new int[n];

            // 初始状态：默认值0即可

            // 状态转移
            int maxLen = 0;
            for (int i = 1; i < n; i++) {
                if (s.charAt(i) == ')') {
                    if (s.charAt(i - 1) == '(') {
                        dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                    }
                    else if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                        dp[i] = (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0) + 2 + dp[i - 1];
                    }
                    maxLen = Math.max(maxLen, dp[i]);
                }
            }
            return maxLen;
        }

    }



    public static class Solution4 {

        /**
         贪心：从左往右计数 + 从右往左计数
         ref：https://leetcode.cn/problems/longest-valid-parentheses/solutions/314683/zui-chang-you-xiao-gua-hao-by-leetcode-solution/
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
         public int longestValidParentheses(String s) {

             // 从左到右遍历
             int left = 0, right = 0, maxLen = 0;
             for (int i = 0; i < s.length(); i++) {
                 if (s.charAt(i) == '(') {
                     left++;
                 } else {
                     right++;
                 }

                 if (left == right) {
                     maxLen = Math.max(maxLen, 2 * left);
                 } else if (right > left) {
                     left = right = 0;
                 }
             }

             // 从右到左遍历
             left = right = 0;
             for (int i = s.length() - 1; i >= 0; i--) {
                 if (s.charAt(i) == '(') {
                     left++;
                 } else {
                     right++;
                 }

                 if (left == right) {
                     maxLen = Math.max(maxLen, 2 * left);
                 } else if (left > right) {
                     left = right = 0;
                 }
             }
             return maxLen;
         }

    }

}
