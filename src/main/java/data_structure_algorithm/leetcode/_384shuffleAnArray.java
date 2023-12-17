package data_structure_algorithm.leetcode;

import java.util.Random;

public class _384shuffleAnArray {

    /**
     *  拓展题：编写程序，随机1000万次，把1-1000万全部随机出来，不能重复。要求程序执行时间不超过700ms
     *  解法：如果允许O(N)的内存，大概80M，就可以先弄一个1-1000万的数组，每次随机取一个，把这个数和数组尾巴交换，然后数组长度减1.
     */


    /**
     * Your Solution object will be instantiated and called as such:
     * Solution obj = new Solution(nums);
     * int[] param_1 = obj.reset();
     * int[] param_2 = obj.shuffle();
     */
    public class Solution {

        /**
         洗牌算法：每次随机选择一个元素将其交换到数组尾部，不断缩小随机数生成范围
         */
        int[] nums;
        int n;
        Random random = new Random();

        public Solution(int[] nums) {
            this.nums = nums;
            n = nums.length;
        }

        /**
         时间复杂度：O(1)
         空间复杂度：O(1)
         */
        public int[] reset() {
            return nums;
        }

        /**
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int[] shuffle() {
            int[] res = nums.clone();
            for (int i = n - 1; i >= 0; i--) {
                swap(res, i, random.nextInt(i + 1));
            }
            return res;
        }

        private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }

    }

}
