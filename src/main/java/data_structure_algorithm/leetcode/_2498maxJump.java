package data_structure_algorithm.leetcode;

public class _2498maxJump {

    public static class Solution1 {

        /**
         Greedy:
         等价于两只青蛙从0开始跳，跳到最后一块石头，且两只青蛙跳的路径没有交集（0和最后一块石头除外）
         那么一只青蛙跳1、3、5、7...，另一只青蛙跳2、4、6、8...就好了，这样代价一定最小

         TC: O(N)
         SC: O(1)
         */
        public int maxJump(int[] stones) {
            int n = stones.length, max = stones[1] - stones[0];
            for (int i = 2; i < n; i++) {
                max = Math.max(max, stones[i] - stones[i - 2]);
            }
            max = Math.max(max, stones[n - 1] - stones[n - 2]);
            return max;
        }

    }

}
