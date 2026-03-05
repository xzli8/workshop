package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class _1673mostCompetitive {

    public static class Solution1 {

        /**
         单调栈：O(N), O(N)
         */
        public int[] mostCompetitive(int[] nums, int k) {
            // 单调栈删除(单调栈只能控制什么时候删除，不能控制最后剩几个，所以需要先计算待删除个数)
            int n = nums.length, drop = n - k;
            Deque<Integer> s = new ArrayDeque<>();
            for (int num : nums) {
                while (!s.isEmpty() && s.peek() > num && drop > 0) {
                    s.pop();
                    drop--;
                }
                s.push(num);
            }
            // 遍历完了都没删够drop个，继续从栈顶pop直至够数
            for (int i = 0; i < drop; i++) {
                s.pop();
            }

            // 组装结果返回
            int[] res = new int[k];
            for (int i = k - 1; i >= 0; i--) {
                res[i] = s.pop();
            }
            return res;
        }

    }

}
