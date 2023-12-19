package data_structure_algorithm.leetcode;

public class _1109corpFlightBookings {

    public static class Solution1 {

        /**
         差分数组（多次修改某个区间，求最终结果）
         */
        public int[] corpFlightBookings(int[][] bookings, int n) {
            int[] nums = new int[n];
            Difference diff = new Difference(nums);
            for (int[] booking : bookings) {
                diff.update(booking[0]-1, booking[1]-1, booking[2]);
            }
            return diff.result();
        }

        public class Difference {

            private int[] diff;

            Difference(int[] nums) {
                int n = nums.length;
                diff = new int[n];
                diff[0] = nums[0];
                for (int i = 1; i < n; i++) {
                    diff[i] = nums[i] - nums[i-1];
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
                    res[i] = res[i-1] + diff[i];
                }
                return res;
            }

        }

    }

}
