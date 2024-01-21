package data_structure_algorithm.leetcode;

public class _189rotate {

    public static class Solution1 {

        /**
         使用额外数组
         思路：将原数组下标为i的元素放到新数组下标为(i+k)%n的位置
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public void rotate(int[] nums, int k) {
             int n = nums.length;
             int[] tmp = new int[n];
             for (int i = 0; i < n; i++) {
                 tmp[(i + k) % n] = nums[i];
             }
             System.arraycopy(tmp, 0, nums, 0, n);
         }

    }



    public static class Solution2 {

        /**
         数组翻转
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public void rotate(int[] nums, int k) {
            int n = nums.length;
            k %= n;
            reverse(nums, 0, n-1);
            reverse(nums, 0, k-1);
            reverse(nums, k, n-1);
        }

        private void reverse(int[] nums, int start, int end) {
            while (start < end) {
                int tmp = nums[start];
                nums[start] = nums[end];
                nums[end] = tmp;
                start++;
                end--;
            }
        }

    }

}
