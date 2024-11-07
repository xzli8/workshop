package data_structure_algorithm.leetcode;

public class _3254resultsArray {

    public static class Solution1 {

        /**
         SlidingWindow + BruteForce
         TC: O(N * K)
         SC: O(1)
         */
        public int[] resultsArray(int[] nums, int k) {
            int n = nums.length;
            int[] res = new int[n - k + 1];
            for (int start = 0; start <= n - k; start++) {
                int i = start + 1;
                while (i < start + k) {
                    if (nums[i] != nums[i - 1] + 1) {
                        break;
                    }
                    i++;
                }
                res[start] = i == start + k ? nums[start + k - 1] : -1;
            }
            return res;
        }

    }



    public static class Solution2 {

        /**
         Count
         TC: O(N)
         SC: O(1)
         */
        public int[] resultsArray(int[] nums, int k) {
            int n = nums.length, cnt = 1;
            for (int i = 1; i < k - 1; i++) {
                cnt = nums[i] == nums[i - 1] + 1 ? cnt + 1 : 1;
            }
            int[] res = new int[n - k + 1];
            for (int i = k - 1; i < n; i++) {
                cnt = i == 0 || nums[i] == nums[i - 1] + 1 ? cnt + 1 : 1;
                res[i - k + 1] = cnt >= k ? nums[i] : -1;
            }
            return res;
        }

    }

}
