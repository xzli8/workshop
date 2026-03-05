package data_structure_algorithm.leetcode;

public class _694numberofDistinctIslands {

    /**
     * Ref: https://leetcode.doocs.org/lc/694/
     * Lintcode:https://www.lintcode.com/problem/860
     *
     * 题目描述
     * 给定一个非空 01 二维数组表示的网格，一个岛屿由四连通（上、下、左、右四个方向）的 1 组成，你可以认为网格的四周被海水包围。
     * 请你计算这个网格中共有多少个形状不同的岛屿。两个岛屿被认为是相同的，当且仅当一个岛屿可以通过平移变换（不可以旋转、翻转）和另一个岛屿重合。
     */

    public static class Solution1 {

        /**
         * DFS:
         * Note: 我们遍历网格中的每个位置(i, j)，如果该位置为1，则以其为起始节点开始进行深度优先搜索，过程中将1修改为2，
         *  并且将搜索的方向记录下来，等搜索结束后将方向序列加入哈希表中，最后返回哈希表中不同方向序列的数量即可。
         */
        public int numDistinctIslands(int[][] grid) {
            return 0;
        }

    }

}
