package data_structure_algorithm.leetcode;

public class _832flipAndInvertImage {

    public static class Solution1 {

        /**
         模拟
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int[][] flipAndInvertImage(int[][] image) {
            int m = image.length, n = image[0].length;
            for (int i = 0; i < m; i++) reverse(image[i]);
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (image[i][j] == 1) image[i][j] = 0;
                    else if (image[i][j] == 0) image[i][j] = 1;
                }
            }
            return image;
        }

        private void reverse(int[] nums) {
            int left = 0, right = nums.length - 1;
            while (left < right) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                left++;
                right--;
            }
        }

    }

}
