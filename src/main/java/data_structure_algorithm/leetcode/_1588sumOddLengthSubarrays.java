package data_structure_algorithm.leetcode;

public class _1588sumOddLengthSubarrays {

    public static class Solution1 {

        /**
         PrefixSum
         TC: O(N^2)
         SC: O(N)
         */
         public int sumOddLengthSubarrays(int[] arr) {
             int n = arr.length;
             int[] preSum = new int[n + 1];
             for (int i = 1; i <= n; i++) {
                 preSum[i] = preSum[i - 1] + arr[i - 1];
             }

             int sum = 0;
             for (int size = 1; size <= n; size += 2) {
                 for (int start = 0; start + size <= n; start++) {
                     int end = start + size;
                     sum += preSum[end] - preSum[start];
                 }
             }
             return sum;
         }

    }



    public static class Solution2 {

        /**
         Reference: https://leetcode.cn/problems/sum-of-all-odd-length-subarrays/solutions/420433/cong-on3-dao-on-de-jie-fa-by-liuyubobobo/
         TC: O(N)
         SC: O(1)
         */
        public int sumOddLengthSubarrays(int[] arr) {
            int n = arr.length, sum = 0;
            for (int i = 0; i < n; i++) {
                int left = i + 1, right = n - i;
                int leftEven = (left + 1) / 2, leftOdd = left / 2;
                int rightEven = (right + 1) / 2, rightOdd = right / 2;
                sum += (leftEven * rightEven + leftOdd * rightOdd) * arr[i];
            }
            return sum;
        }

    }

}
