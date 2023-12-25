package data_structure_algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class _666pathSum {

    /**
     *  参考题解：https://zhuanlan.zhihu.com/p/441264789?utm_id=0
     */

    public static class Solution1 {

        @Test
        public void test() {
            Assert.assertEquals(12, pathSum(new int[] {113, 215, 221}));
        }

        /**
         *  构建满二叉树 + DFS
         *      时间复杂度：O(N)
         *      空间复杂度：O(N)
         */
        public int pathSum(int[] nums) {
            // 构建满二叉树
            int[] tree = new int[15];
            Arrays.fill(tree, -1);
            for (int num : nums) {
                int depth = num / 100, pos = num % 100 / 10, val = num % 10;
                tree[((1 << (depth - 1)) - 1) + pos - 1] = val;
            }

            // dfs遍历求路径和
            dfs(tree, 0, 0);
            return totalSum;
        }

        private int totalSum = 0;
        private void dfs(int[] tree, int i, int pathSum) {
            if (tree[i] == -1) return;
            pathSum += tree[i];
            if (i >= 7 || (tree[2 * i + 1] == -1 && tree[2 * i + 2] == -1)) {
                totalSum += pathSum;
                return;
            }
            dfs(tree, 2 * i + 1, pathSum);
            dfs(tree, 2 * i + 2, pathSum);
        }

    }


}
