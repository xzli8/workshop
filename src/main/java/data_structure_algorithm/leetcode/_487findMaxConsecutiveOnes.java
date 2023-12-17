package data_structure_algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

public class _487findMaxConsecutiveOnes {

    /**
     * 题目描述:给定一个二进制数组nums，如果最多可以翻转一个0，则返回数组中连续1的最大个数。
     * 示例 1：
     * 输入：[1,0,1,1,0]
     * 输出：4
     * 解释：翻转第一个 0 可以得到最长的连续 1。
     *      当翻转以后，最大连续 1 的个数为 4。
     *
     * 注：
     * 输入数组只包含 0 和 1.
     * 输入数组的长度为正整数，且不超过 10,000
     *
     * 进阶： 如果输入的数字是作为 无限流 逐个输入如何处理？ 换句话说，内存不能存储下所有从流中输入的数字。您可以有效地解决吗？
     */

    public static class Solution1 {

        /**
         *  滑动窗口：如果数字是无限流逐个输入，仍然可以处理
         *      时间复杂度：O(N)
         *      空间复杂度：O(1)
         */
        public int findMaxConsecutiveOnes(int[] nums) {
            int n = nums.length, left = 0, right = 0, count0 = 0, maxLen = 0;
            while (right < n) {
                if (nums[right++] == 0) count0++;
                while (count0 > 1) {
                    if (nums[left++] == 0) count0--;
                }
                maxLen = Math.max(maxLen, right - left);
            }
            return maxLen;
        }

        @Test
        public void test() {
            Assert.assertEquals(4, findMaxConsecutiveOnes(new int[] {1,0,1,1,0}));
            Assert.assertEquals(4, findMaxConsecutiveOnes(new int[] {1,0,1,1,0,1}));
        }

    }

}
