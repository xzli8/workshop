package data_structure_algorithm.leetcode;

public class _41firstMissingPositive {

    public static class Solution1 {

        /**
         原地哈希：
         分析：
         1.先通过交换将nums[i]放到nums[nums[i] - 1]的位置；(最小的正整数是1，所以下标要偏移1位)
         2.然后遍历数组，找第一个nums[i] != i + 1的元素即可.
         注意：交换前需要判断下标范围，否则会越界

         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int firstMissingPositive(int[] nums) {
            // 通过交换使各元素归位，完成原地哈希的过程
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                while (nums[i] > 0 && nums[i] <= n && nums[i] != nums[nums[i] - 1]) {
                    int tmp = nums[nums[i] - 1];
                    nums[nums[i] - 1] = nums[i];
                    nums[i] = tmp;

                    // 这样交换会越界。因为上面的判断只保证了nums[i]-1在数组下标范围内，没有保证nums[nums[i]-1]在范围内。
                    // 所以nums[i]一定要后赋值，否则nums[i]的值被覆盖了，而nums[nums[i]-1]要先赋值
                    // int tmp = nums[i];
                    // nums[i] = nums[nums[i] - 1];
                    // nums[nums[i] - 1] = tmp;
                }
            }

            // 交换完成后，下标为i的位置元素应该是i+1，否则返回i+1
            for (int i = 0; i < n; i++) {
                if (nums[i] != i + 1) {
                    return i + 1;
                }
            }

            // 前面元素都归位，那么未出现的最小正整数是n+1
            return n + 1;
        }

    }

}
