package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Mianshiti_16subSort {

    public static class Solution1 {

        public int[] subSort(int[] array) {
            Deque<Integer> s = new ArrayDeque<>();
            int n = array.length, max = Integer.MIN_VALUE, left = n, right = -1;
            for (int i = 0; i < n; i++) {
                while (!s.isEmpty() && array[i] < array[s.peek()]) {
                    int unorderIdx = s.pop();
                    max = Math.max(max, array[unorderIdx]);
                    left = Math.min(left, unorderIdx);
                }
                s.push(i);
                if (array[i] < max) right = i;
            }
            return new int[] {left == n ? -1 : left, right};
        }

    }

}
