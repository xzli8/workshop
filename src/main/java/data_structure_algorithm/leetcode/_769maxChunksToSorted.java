package data_structure_algorithm.leetcode;

public class _769maxChunksToSorted {

    public static class Solution1 {

        /**
         贪心 + 原地哈希
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int maxChunksToSorted(int[] arr) {
            int max = 0, count = 0;
            for (int i = 0; i < arr.length; i++) {
                max = Math.max(max, arr[i]);
                if (max == i) count++;
            }
            return count;
        }

    }

}
