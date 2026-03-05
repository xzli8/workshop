package data_structure_algorithm.leetcode;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class _2282seePeople {

    /**
     * ref: https://leetcode.doocs.org/lc/2282/
     */

    public static class Solution1 {

        /**
         * Monotonic Stack: O(N), O(N)
         */
        public int[][] seePeople(int[][] heights) {
            int m = heights.length, n = heights[0].length;
            int[][] res = new int[m][n];
            for (int i = 0; i < m; i++) {
                res[i] = help(heights[i]);
            }
            for (int j = 0; j < n; j++) {
                int[] nums = new int[m];
                for (int i = 0; i < m; i++) {
                    nums[i] = heights[i][j];
                }
                int[] col = help(nums);
                for (int i = 0; i < m; i++) {
                    res[i][j] += col[i];
                }
            }
            return res;
        }

        private int[] help(int[] nums) {
            int n = nums.length;
            int[] res = new int[n];
            Deque<Integer> s = new ArrayDeque<>();
            for (int i = n - 1; i >= 0; i--) {
                while (!s.isEmpty() && nums[s.peek()] <= nums[i]) {
                    s.pop();
                    res[i]++;
                }
                if (!s.isEmpty()) res[i]++;
                s.push(i);
            }
            return res;
        }

        @Test
        public void test() {
            System.out.println(Arrays.deepToString(seePeople(new int[][]{{3,1,4,2,5}})));
            System.out.println(Arrays.deepToString(seePeople(new int[][]{{5,1}, {3,1}, {4,1}})));
        }

    }

}
