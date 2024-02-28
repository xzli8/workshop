package data_structure_algorithm.leetcode;

public class _2673minIncrements {

    public static class Solution1 {

        /**
         贪心：从叶子节点开始考虑，然后把叶子节点剪掉，直到根节点为止
         ref:https://leetcode.cn/problems/make-costs-of-paths-equal-in-a-binary-tree/solutions/2656293/shi-er-cha-shu-suo-you-lu-jing-zhi-xiang-65hk/?envType=daily-question&envId=2024-02-28
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int minIncrements(int n, int[] cost) {
            int res = 0;
            for (int i = n - 2; i > 0; i -= 2) {
                res += Math.abs(cost[i] - cost[i + 1]);     // 计算当前叶子节点与其兄弟节点的差值
                cost[i / 2] += Math.max(cost[i], cost[i + 1]);  // 将叶子节点的值增加到父节点
            }
            return res;
        }

    }

}
