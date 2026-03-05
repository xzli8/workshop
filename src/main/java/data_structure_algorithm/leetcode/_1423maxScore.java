package data_structure_algorithm.leetcode;

public class _1423maxScore {

    public static class Solution1 {

        // fixed sliding window: O(N), O(1)
        public Integer maxScore(int[] cards, Integer k) {
            int n = cards.length, sum = 0;
            for (int i = n - k; i < n; i++) {
                sum += cards[i];
            }
            int max = sum;
            for (int i = 0; i < k; i++) {
                sum = sum - cards[n - k + i] + cards[i];
                max = Math.max(max, sum);
            }
            return max;
        }

    }

}
