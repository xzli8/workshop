package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Mianshiti_16_16subSort {

    public static class Solution1 {

        /**
         贪心: O(N), O(1)
         */
        public int[] subSort(int[] array) {
            int n = array.length, left = -1, right = -1;

            // 从左往右遍历，将当前元素与遍历过的最大值比较，确定right
            for (int i = 0, max = Integer.MIN_VALUE; i < n; i++) {
                if (array[i] < max) {
                    right = i;
                } else {
                    max = array[i];
                }
            }

            // 从右往左遍历，将当前元素与遍历过的最小值比较，确定left
            for (int i = n - 1, min = Integer.MAX_VALUE; i >= 0; i--) {
                if (array[i] > min) {
                    left = i;
                } else {
                    min = array[i];
                }
            }
            return new int[] {left, right};
        }

    }


}
