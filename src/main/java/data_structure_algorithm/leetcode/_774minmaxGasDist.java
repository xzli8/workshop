package data_structure_algorithm.leetcode;

public class _774minmaxGasDist {

    /**
     * 题目描述(https://leetcode.doocs.org/lc/774/)
     *
     * 整数数组 stations 表示 水平数轴 上各个加油站的位置。给你一个整数 k 。
     * 请你在数轴上增设 k 个加油站，新增加油站可以位于 水平数轴 上的任意位置，而不必放在整数位置上。
     * 设 penalty() 是：增设 k 个新加油站后，相邻 两个加油站间的最大距离。
     * 请你返回 penalty() 可能的最小值。与实际答案误差在 10-6 范围内的答案将被视作正确答案。
     */

    public static class Solution1 {

        /**
         *  值域二分: O(NlogM), O(1)
         *  Note: 我们二分枚举相邻两个加油站间的距离，找到最小的距离，使得加油站的数量不超过k。
         */
        public double minmaxGasDist(int[] stations, int k) {
            return 0;
        }

    }

}
