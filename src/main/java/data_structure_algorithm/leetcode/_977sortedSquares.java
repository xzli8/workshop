package data_structure_algorithm.leetcode;

public class _977sortedSquares {

    public static class Solution1 {

        /**
         双指针：两个指针分别指向数组首尾两端，根据平方大小移动指针（类似于归并排序）
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int[] sortedSquares(int[] nums) {
            int n = nums.length;
            int[] res = new int[n];
            int i = 0, j = n - 1, k = n - 1;
            while (i <= j) {
                int ii = nums[i] * nums[i], jj = nums[j] * nums[j];
                if (ii >= jj) {
                    res[k--] = ii;
                    i++;
                } else {
                    res[k--] = jj;
                    j--;
                }
            }
            return res;
        }

    }

}
