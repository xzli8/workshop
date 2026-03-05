package data_structure_algorithm.leetcode;

import org.junit.Test;

import java.util.*;

public class _1762findBuildings {

    /**
     * ref: https://leetcode.doocs.org/lc/1762/
     */

    public static class Solution1 {

        /**
         * 单调栈: O(N), O(N)
         */
        public int[] findBuildings(int[] heights) {
            List<Integer> res = new ArrayList<>();
            Deque<Integer> s = new ArrayDeque<>();
            for (int i = heights.length - 1; i >= 0; i--) {
                while (!s.isEmpty() && heights[s.peek()] < heights[i]) {
                    s.pop();
                }
                if (s.isEmpty()) {
                    res.add(i);
                }
                s.push(i);
            }
            Collections.reverse(res);
            return res.stream().mapToInt(Integer::intValue).toArray();
        }

        @Test
        public void test() {
            System.out.println(Arrays.toString(findBuildings(new int[]{4,2,3,1})));
            System.out.println(Arrays.toString(findBuildings(new int[]{4,3,2,1})));
            System.out.println(Arrays.toString(findBuildings(new int[]{1,3,2,4})));
            System.out.println(Arrays.toString(findBuildings(new int[]{2,2,2,2})));
        }

    }

    public static class Solution2 {

        /**
         * 逆序遍历求右侧最大值: O(N), O(1)
         */
        public int[] findBuildings(int[] heights) {
            List<Integer> res = new ArrayList<>();
            for (int i = heights.length - 1, max = 0; i >= 0; i--) {
                if (heights[i] > max) {
                    res.add(i);
                    max = heights[i];
                }
            }
            Collections.reverse(res);
            return res.stream().mapToInt(Integer::intValue).toArray();
        }

    }

}
