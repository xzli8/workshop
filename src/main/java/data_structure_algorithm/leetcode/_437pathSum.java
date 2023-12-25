package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class _437pathSum {

    public class Solution1 {

        /**
         DFS：以二叉树上的每个节点为根节点，向下遍历，计算路径和
         分析：如果有需要的话，可以遍历求出所有路径，但该方法时间复杂度较高。
         时间复杂度：O(N^2)
         空间复杂度：O(N)
         */
         public int pathSum(TreeNode root, int targetSum) {
             if (root == null) return 0;
             return pathSum(root.left, targetSum) + pathSum(root.right, targetSum) + rootSum(root, targetSum);
         }

         // 计算以root为根节点的二叉树中和为target的数量(这里入参target要用long，否则会溢出)
         private int rootSum(TreeNode root, long targetSum) {
             if (root == null) return 0;
             targetSum -= root.val;
             return rootSum(root.left, targetSum) + rootSum(root.right, targetSum) + (targetSum == 0 ? 1 : 0);
         }

    }



    public class Solution2 {

        /**
         DFS + 前缀和：只需要计算个数，不需要计算具体路径，所以可以用前缀和简化
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int pathSum(TreeNode root, int targetSum) {
            // key是从根节点到当前节点的前缀和，value为前缀和对应的数量
            Map<Long, Integer> preSum2Count = new HashMap<>();

            // 前缀和为0，只有一条路径(初始化条件)
            preSum2Count.put(0L, 1);

            // 遍历以root为根节点的树
            return dfs(root, 0, targetSum, preSum2Count);
        }

        // 计算当前节点的前缀和及其对应数量
        private int dfs(TreeNode cur, long preSum, int targetSum, Map<Long, Integer> preSum2Count) {
            // 递归终止条件
            if (cur == null) return 0;

            // 计算当前节点的前缀和
            preSum += cur.val;

            // 计算截止当前节点满足targetSum的数量
            int count = preSum2Count.getOrDefault(preSum - targetSum, 0);

            // 更新前缀和的数量
            preSum2Count.put(preSum, preSum2Count.getOrDefault(preSum, 0) + 1);

            // 进入下一层
            count += dfs(cur.left, preSum, targetSum, preSum2Count);
            count += dfs(cur.right, preSum, targetSum, preSum2Count);

            // 回到本层，恢复状态，去除当前节点对应的前缀和数量(回溯)
            preSum2Count.put(preSum, preSum2Count.get(preSum) - 1);

            // 返回结果
            return count;
        }

    }

}
