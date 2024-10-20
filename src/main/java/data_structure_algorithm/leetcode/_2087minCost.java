package data_structure_algorithm.leetcode;

public class _2087minCost {

    public static class Solution1 {

        /**
         Greedy
         */
        public int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
            int minX = Math.min(startPos[0], homePos[0]), maxX = Math.max(startPos[0], homePos[0]);
            int minY = Math.min(startPos[1], homePos[1]), maxY = Math.max(startPos[1], homePos[1]);
            int res = -rowCosts[startPos[0]] - colCosts[startPos[1]];
            for (int i = minX; i <= maxX; i++) res += rowCosts[i];
            for (int j = minY; j <= maxY; j++) res += colCosts[j];
            return res;
        }

    }

}
