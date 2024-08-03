package data_structure_algorithm.leetcode;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class _3143maxPointsInsideSquare {

    public static class Solution1 {

        /**
         BinarySearch + Hash
         */
        public int maxPointsInsideSquare(int[][] points, String s) {
            // init and sort
            int n = points.length;
            Pair<int[], Character>[] pairs = new Pair[n];
            for (int i = 0; i < n; i++) {
                pairs[i] = new Pair<>(points[i], s.charAt(i));
            }
            Arrays.sort(pairs, (p1, p2) -> maxEdgeLength(p1.getKey()) - maxEdgeLength(p2.getKey()));

            // binary search
            int left = 0, right = maxEdgeLength(pairs[n - 1].getKey());
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                Pair<Boolean, Integer> checkResult = check(pairs, mid);
                if (checkResult.getKey()) {
                    if (mid == right || !check(pairs, mid + 1).getKey()) {
                        return checkResult.getValue();
                    } else {
                        left = mid + 1;
                    }
                } else {
                    right = mid - 1;
                }
            }
            return -1;
        }

        private int maxEdgeLength(int[] point) {
            return Math.max(Math.abs(point[0]), Math.abs(point[1]));
        }

        private Pair<Boolean, Integer> check(Pair<int[], Character>[] pairs, int edgeLength) {
            Set<Character> set = new HashSet<>();
            for (int i = 0; i < pairs.length && maxEdgeLength(pairs[i].getKey()) <= edgeLength; i++) {
                if (set.contains(pairs[i].getValue())) return new Pair<>(false, 0);
                set.add(pairs[i].getValue());
            }
            return new Pair<>(true, set.size());
        }

    }

}
