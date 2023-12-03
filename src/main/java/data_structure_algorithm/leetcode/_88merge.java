package data_structure_algorithm.leetcode;

public class _88merge {

    public static class Solution1 {

        /**
         归并(从后往前可以避免开辟新的数组空间)
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int i = m - 1, j = n - 1, k = m + n - 1;
            while (i >= 0 && j >= 0) {
                if (nums1[i] > nums2[j]) nums1[k--] = nums1[i--];
                else nums1[k--] = nums2[j--];
            }
            while (i >= 0) {
                nums1[k--] = nums1[i--];
            }
            while (j >= 0) {
                nums1[k--] = nums2[j--];
            }
        }

    }

}
