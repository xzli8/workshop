package data_structure_algorithm.leetcode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class _496nextGreaterElement {

    public static class Solution1 {

        /**
         单调栈 + 哈希表
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {

            // 用HashMap记录元素到其下一个更大元素的映射关系
            int n2 = nums2.length;
            Map<Integer, Integer> map = new HashMap<>(n2);

            // 从后往前遍历数组元素，找下一个更大值
            Deque<Integer> s = new LinkedList<>();
            for (int i = n2 - 1; i >= 0; i--) {
                int num = nums2[i];
                while (!s.isEmpty() && s.peek() <= num) {
                    s.pop();
                }
                map.put(num, s.isEmpty() ? -1 : s.peek());
                s.push(num);
            }

            // 组装返回
            int n1 = nums1.length;
            int[] res = new int[n1];
            for (int i = 0; i < n1; i++) {
                res[i] = map.get(nums1[i]);
            }
            return res;
        }


    }

}
