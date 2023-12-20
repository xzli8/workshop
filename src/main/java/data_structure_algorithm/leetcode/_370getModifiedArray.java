package data_structure_algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

public class _370getModifiedArray {

    /**
     * 题目描述：假设你有一个长度为 n 的数组，初始情况下所有的数字均为 0，你将会被给出k个更新的操作。
     * 其中，每个操作会被表示为一个三元组：[startIndex, endIndex, inc]，你需要将子数组 A[startIndex ... endIndex]（包括 startIndex 和 endIndex）增加 inc。
     * 请你返回 k 次操作后的数组。
     *
     *
     * 示例:
     * 输入: length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
     * 输出: [-2,0,3,5,3]
     *
     * 解释:
     * 初始状态:
     * [0,0,0,0,0]
     *
     * 进行了操作 [1,3,2] 后的状态:
     * [0,2,2,2,0]
     *
     * 进行了操作 [2,4,3] 后的状态:
     * [0,2,5,5,3]
     *
     * 进行了操作 [0,2,-2] 后的状态:
     * [-2,0,3,5,3]
     *
     */

    public static class Solution1 {

        @Test
        public void test() {
            int[] expected = new int[] {-2,0,3,5,3};
            int[] res = getModifiedArray(5, new int[][]{{1,3,2}, {2,4,3}, {0,2,-2}});
            for (int i = 0; i < expected.length; i++) {
                Assert.assertEquals(expected[i], res[i]);
            }
        }

        /**
         * 差分数组：区间更新，单点查询
         *  时间复杂度：O(N)，N为操作次数
         *  空间复杂度：O(M)，M为数组长度
         */
        public int[] getModifiedArray(int length, int[][] updates) {
            Diff diff = new Diff(new int[length]);
            for (int[] update : updates) {
                diff.update(update[0], update[1], update[2]);
            }
            return diff.result();
        }

        public class Diff {

            int[] diffs;

            public Diff(int[] nums) {
                diffs = new int[nums.length];
                diffs[0] = nums[0];
                for (int i = 1; i < nums.length; i++) {
                    diffs[i] = nums[i] - nums[i - 1];
                }
            }

            public void update(int start, int end, int val) {
                diffs[start] += val;
                if (end + 1 < diffs.length) {
                    diffs[end + 1] -= val;
                }
            }

            public int[] result() {
                int[] nums = new int[diffs.length];
                nums[0] = diffs[0];
                for (int i = 1; i < diffs.length; i++) {
                    nums[i] = diffs[i] + nums[i - 1];
                }
                return nums;
            }

        }

    }

}
