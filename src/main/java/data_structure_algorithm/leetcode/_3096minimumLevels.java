package data_structure_algorithm.leetcode;

public class _3096minimumLevels {

    public static class Solution1 {

        /**
         PrefixSum
         TimeComplexity: O(N)
         SpaceComplexity: O(N)
         */
        public int minimumLevels(int[] possible) {
            // build
            int n = possible.length;
            int[] preSum = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                preSum[i] = preSum[i - 1] + (possible[i - 1] == 0 ? -1 : 1);
            }

            // search
            // two sets both need to be non-empty
            // so i starts from 1 rather 0 and 'i < n' rather than 'i <= n'
            for (int i = 1; i < n; i++) {
                if (preSum[i] > preSum[n] - preSum[i]) return i;
            }
            return -1;
        }

    }

}
