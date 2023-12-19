package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class _437pathSum {

    public static class Solution1 {

        /**
         前缀和：最开始的思路是构建一颗新的树，每个节点有指向父节点的指针和根节点到当前节点的路径和，然后在这棵树上递归。
         上面的做法能找出具体的路径，但题目只要求找出路径的数目，所以不需要记录这么多信息。
         */
        public int pathSum(TreeNode root, int targetSum) {
            // key是从根节点到当前节点的前缀和，value为前缀和对应的数量
            Map<Long, Integer> preSumCount = new HashMap<>();

            // 前缀和为0，只有一条路径(初始化条件)
            preSumCount.put(0L, 1);

            // 递归遍历
            return backtrace(root, 0, targetSum, preSumCount);
        }

        /**
         回溯：cur表示当前节点，curSum表示当前节点的前缀和
         */
        private int backtrace(TreeNode cur, long curSum, int targetSum, Map<Long, Integer> preSumCount) {
            // 递归终止条件
            if (cur == null) {
                return 0;
            }

            // 计算当前节点的前缀和
            curSum += cur.val;

            // 计算截止当前节点满足targetSum的数量
            int count = preSumCount.getOrDefault(curSum - targetSum, 0);

            // 更新前缀和的数量
            preSumCount.put(curSum, preSumCount.getOrDefault(curSum, 0) + 1);

            // 进入下一层
            count += backtrace(cur.left, curSum, targetSum, preSumCount);
            count += backtrace(cur.right, curSum, targetSum, preSumCount);

            // 回到本层，恢复状态，去除当前节点对应的前缀和数量
            preSumCount.put(curSum, preSumCount.get(curSum) - 1);

            // 返回结果
            return count;
        }


    }

}
