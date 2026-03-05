package data_structure_algorithm.leetcode;

import java.util.PriorityQueue;

public class _1642furthestBuilding {

    public static class Solution1 {

        /**
         * 贪心+优先队列: O(NlogL), O(logL)，其中N为建筑物数量，L为梯子数量
         * Note: 梯子相当于一次性的无限量砖块，如果我们有l架梯子，那么我们会在Δh最大的那l次使用梯子，而在剩余的情况下使用砖块。
         * 所以我们使用优先队列实时维护不超过l个最大的Δh，这些就是使用梯子的地方。对于剩余的Δh，我们需要使用砖块，因此需要对它们进行累加。
         *
         * 总之，就是有砖块用砖块，没砖块用梯子，梯子可以换下来砖块，换下来的砖块可以继续使用。
         */
        public int furthestBuilding(int[] heights, int bricks, int ladders) {
            // 用于保存已经使用的砖块
            PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
            int n = heights.length;
            for (int i = 1; i < n; i++) {
                if (heights[i] <= heights[i - 1])
                    continue;
                int gap = heights[i] - heights[i - 1];
                // 贪心策略 优先使用砖块补充，先别管有没有砖块，先用，没有了再换砖块
                q.add(gap);
                bricks -= gap;
                if (bricks >= 0)
                    continue; // 还有砖块
                // 这个时候没砖块，要用梯子换，换一定要换最多的砖块
                if (ladders > 0) {
                    ladders--;
                    bricks += q.poll(); // 这里不用担心q为空，因为前面有对应的add动作
                } else {
                    // 没砖块也没梯子，只能返回前一个位置了
                    return i - 1;
                }
            }
            return n - 1;
        }

    }

}
