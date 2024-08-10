package data_structure_algorithm.leetcode;

public class _1854maximumPopulation {

    public static class Solution1 {

        /**
         Difference
         TimeComplexity: O(N)
         SpaceComplexity: O(M)
         */
        public int maximumPopulation(int[][] logs) {
            // normalize
            int n = logs.length, base = 1950;
            for (int i = 0; i < n; i++) {
                logs[i][0] -= base;
                logs[i][1] -= base;
            }

            // define difference array
            int maxYearGap = 101;
            int[] counts = new int[maxYearGap];
            Difference diff = new Difference(counts);

            // update while traversing
            for (int i = 0; i < n; i++) {
                diff.update(logs[i][0], logs[i][1] - 1, 1);
            }
            counts = diff.result();

            // find the earliest year with maximum count
            int maxCountAlive = 0, yearOfMaxAlive = 0;
            for (int i = 0; i < maxYearGap; i++) {
                if (counts[i] > maxCountAlive) {
                    maxCountAlive = counts[i];
                    yearOfMaxAlive = i;
                }
            }
            return yearOfMaxAlive + base;
        }

        public class Difference {
            private int[] diff;

            public Difference(int[] nums) {
                int n = nums.length;
                diff = new int[n];
                diff[0] = nums[0];
                for (int i = 1; i < n; i++) {
                    diff[i] = nums[i] - nums[i - 1];
                }
            }

            public void update(int start, int end, int num) {
                diff[start] += num;
                if (end + 1 < diff.length) {
                    diff[end + 1] -= num;
                }
            }

            public int[] result() {
                int[] res = new int[diff.length];
                res[0] = diff[0];
                for (int i = 1; i < diff.length; i++) {
                    res[i] = res[i - 1] + diff[i];
                }
                return res;
            }

        }

    }

}
